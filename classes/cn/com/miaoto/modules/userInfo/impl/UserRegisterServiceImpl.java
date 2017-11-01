package cn.com.miaoto.modules.userInfo.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.userInfo.inf.UserRegisterService;
import cn.com.miaoto.modules.userInfo.model.UserRegisterReq;
import cn.com.miaoto.modules.userInfo.model.UserRegisterResp;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.util.StringUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/27.
 */
@Service
public class UserRegisterServiceImpl extends AbstractService<UserRegisterReq, UserRegisterResp> implements UserRegisterService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterServiceImpl.class);

    @Resource
    UserInfoDao userInfoDao;

    @Override
    public UserRegisterResp register(UserRegisterReq reqBean, UserRegisterResp respBean) {
        return (UserRegisterResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UserRegisterReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UserRegisterReq reqBean, UserRegisterResp respBean) throws Exception {
        //set status -> wait
        UserInfo user = reqBean.getUser();
        user.setStatus(999);

        //set salt and password
        String salt = StringUtil.createRandomCharData(32);
        user.setSalt(salt);
        user.setPwd(new Md5Hash(user.getPwd(), salt, 5).toString());

        //set type -> normal(0)
        user.setType(0);

        int effected = userInfoDao.addUser(user);

        if (effected == 0) {
            LOGGER.error("add user failed");
            respBean.setResultCode(0);
            return;
        }
        respBean.setResultCode(1);
    }
}
