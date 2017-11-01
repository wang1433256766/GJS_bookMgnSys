package cn.com.miaoto.modules.userInfo.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.userInfo.inf.UpdateUserService;
import cn.com.miaoto.modules.userInfo.model.UpdateUserReq;
import cn.com.miaoto.modules.userInfo.model.UpdateUserResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/27.
 */
@Service
public class UpdateUserServiceImpl  extends AbstractService<UpdateUserReq, UpdateUserResp> implements UpdateUserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UpdateUserServiceImpl.class);

    @Resource
    UserInfoDao userInfoDao;

    @Override
    public UpdateUserResp update(UpdateUserReq reqBean, UpdateUserResp respBean) {
        return (UpdateUserResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UpdateUserReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UpdateUserReq reqBean, UpdateUserResp respBean) throws Exception {
        int effected = userInfoDao.update(reqBean.getUser());
        if(effected == 0 ) {
            LOGGER.error("update user failed");
            return;
        }
        respBean.setResultCode(1);
    }

}
