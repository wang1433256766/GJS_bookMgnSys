package cn.com.miaoto.modules.userInfo.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.RoleDao;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.userInfo.inf.GetAllUsersService;
import cn.com.miaoto.modules.userInfo.model.GetAllUserInfoReq;
import cn.com.miaoto.modules.userInfo.model.GetAllUserInfoResp;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetAllUsersServiceImpl extends AbstractService<GetAllUserInfoReq, GetAllUserInfoResp> implements GetAllUsersService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private RoleDao roleDao;

    @Override
    public GetAllUserInfoResp getAllUsers(GetAllUserInfoReq reqBean, GetAllUserInfoResp respBean) {
        return (GetAllUserInfoResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetAllUserInfoReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetAllUserInfoReq reqBean, GetAllUserInfoResp respBean) throws Exception {
        if (reqBean.getSearchFilter().getPage() < 1) {
            LOGGER.error("page is smaller than 1");
            return;
        }
        int page = reqBean.getSearchFilter().getPage();
        int rows = reqBean.getSearchFilter().getRows();
        reqBean.getSearchFilter().setBegin((page - 1) * rows);
        reqBean.getSearchFilter().setEnd((page) * rows);

        // query user
        List<UserInfo> userInfoList = userInfoDao.queryAllUsers(reqBean);

        int count = userInfoDao.count(reqBean.getUserInfo());
        respBean.setAllRows(count);

        // query role
        for (int i = 0; i < userInfoList.size(); i++) {
            UserInfo user = userInfoList.get(i);
            UserInfo queryUser = new UserInfo();
            queryUser.setCard(user.getCard());
            List<Role> rolesList = roleDao.queryByUser(queryUser);
            String roleStr = "";
            for (Role role : rolesList) {
                roleStr += role.getRname() + ", ";
            }
            userInfoList.get(i).setRoleStr(roleStr);
        }

        userInfoList = StringUtil.clearSensitive(userInfoList);
        
        respBean.setUserInfoList(userInfoList);
        respBean.setPage(page);
        respBean.setResultCode(1);
    }

}