package cn.com.miaoto.common;

import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.dao.inf.SynUserDao;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.pojo.SynUserInfo;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.util.StringUtil;
import cn.com.miaoto.util.TimeUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * 用户信息同步
 * Created by hx on 2017/9/8.
 */
@Component
public class UserInfoSynchronize {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserInfoSynchronize.class);

    private HashSet<String> idCardSet = new HashSet<>();

    @Resource
    private BorrowDao borrowDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private SynUserDao synUserDao;

    @Resource
    private SynUserReturn synUserReturn;

    @Async
    public void excute() {

        LOGGER.debug("syn userinfo start");
        // clear synUserReturn
        LOGGER.info("last synUserReturn = {}", synUserReturn.toString());
        idCardSet.clear();
        synUserReturn.clear();

        try {
            int count = synUserDao.count();
            if (count == 0) {
                LOGGER.error("select count of synUser failed");
                return;
            }
            // 遍历syn表
            for (int i = 1; i <= count; i++) {

                // 是否要更新
                boolean isSyn = false;

                SynUserInfo user = synUserDao.queryOneUser(i);
                if (user == null) {
                    LOGGER.error("query Syn user failed, row={}", i);
                    continue;
                }
                // 已扫描的进set
                idCardSet.add(user.getIdcard());
                // 查询本地表
                UserInfo queryUser = new UserInfo();
                queryUser.setIdcard(user.getIdcard());
                UserInfo localUser = userInfoDao.queryUserInfo(queryUser);
                // 判断是否新增
                if (localUser == null || localUser.getUid() == null) {
                    UserInfo newUser = new UserInfo();
                    newUser.setUname(user.getSynUserName());
                    newUser.setCard(user.getCard());
                    newUser.setStatus(0);
                    newUser.setCreateTime(TimeUtil.getCurrentTimeStr());
                    newUser.setUpdateTime(TimeUtil.getCurrentTimeStr());
                    newUser.setNumber(user.getWorkNum());

                    String pwd = user.getIdcard().substring(12, 18);
                    String salt = StringUtil.createRandomCharData(32);
                    String encrypt = new Md5Hash(pwd, salt, 5).toString();

                    newUser.setSalt(salt);
                    newUser.setPwd(encrypt);
                    newUser.setType(0);
                    newUser.setIdcard(user.getIdcard());
                    newUser.setDepartment(user.getDptName());

                    int effeted = userInfoDao.addUser(newUser);
                    synUserReturn.addAddUser(newUser);
                    if (effeted == 0) {
                        LOGGER.error("inset new user failed");
                    }
                    continue;
                }
                // 判断是否要更新
                UserInfo updateUser = new UserInfo();
                updateUser.setIdcard(user.getIdcard());
                // 检查用户名
                if ((localUser.getUname() != null && user.getSynUserName() == null) || (localUser.getUname() == null && user.getSynUserName() != null) || !localUser.getUname().equals(user.getSynUserName())) {
                    LOGGER.info("syn username: idcard = {}, name={}", user.getIdcard(), user.getSynUserName());
                    updateUser.setUname(user.getSynUserName());
                    isSyn = true;
                }
                // 检查卡号
                if ((localUser.getCard() == null && user.getCard() != null) || (localUser.getCard() != null && user.getCard() == null) || !(localUser.getCard().equals(user.getCard()))) {
                    LOGGER.info("syn card: idcard = {}, card={}", user.getIdcard(), user.getCard());
                    updateUser.setCard(user.getCard());
                    isSyn = true;
                }
                // 检查部门
                if ((localUser.getDepartment() == null && user.getDptName() != null) || (localUser.getDepartment() != null && user.getDptName() == null) || !localUser.getDepartment().equals(user.getDptName())) {
                    LOGGER.info("syn department: idcard = {}, department={}", user.getIdcard(), user.getDptName());
                    updateUser.setDepartment(user.getDptName());
                    isSyn = true;
                }
                // 检查工号
                if ((localUser.getNumber() == null && user.getWorkNum() != null) || (localUser.getNumber() != null && user.getWorkNum() == null) || !localUser.getNumber().equals(user.getWorkNum())) {
                    LOGGER.info("syn workNum: idcard = {}, workNum={}", user.getIdcard(), user.getWorkNum());
                    updateUser.setNumber(user.getWorkNum());
                    isSyn = true;
                }
                if (isSyn) {
                    int effected = userInfoDao.updateByIDCard(updateUser);
                    synUserReturn.addUpdateUser(updateUser);
                    if (effected == 0) {
                        LOGGER.error("update userinfo failed");
                    }
                }
            }

            // 再扫描本地表
            UserInfo u = new UserInfo();
            int localCount = userInfoDao.count(u);
            for (int i = 1; i <= localCount; i++) {
                UserInfo louser = userInfoDao.selectNext(i);
                if (louser == null) {
                    LOGGER.error("query userinfo failed, row = {}", i);
                    continue;
                }
                // 已删除
                if (louser.getStatus() == 0 && !idCardSet.contains(louser.getIdcard())) {
                    // 查询借阅记录
                    List<Borrow> borrowList = borrowDao.selectBorrowedByUser(louser.getUid());

                    synUserReturn.addDeleteUser(louser);

                    // 存在借阅记录
                    if (borrowList != null && borrowList.size() > 0) {
                        LOGGER.warn("deleted user has borrow record, uid={}", louser.getUid());
                        synUserReturn.addBorrowList(borrowList);

                    } else {
                        // 更新用户状态
                        UserInfo updateUser = new UserInfo();
                        updateUser.setUid(louser.getUid());
                        updateUser.setStatus(SiomConstants.USER_STATUS_OTHER);
                        int effected = userInfoDao.update(updateUser);
                        if (effected == 0) {
                            LOGGER.error("update user status failed");
                        }
                        synUserReturn.addUpdateUser(louser);
                    }
                }
            }
            synUserReturn.setSynStatus(1);
            LOGGER.debug("syn userinfo end");
        } catch (Exception e) {
            LOGGER.error("UserInfoSynchronize catch exception : ", e);
        }
    }

}
