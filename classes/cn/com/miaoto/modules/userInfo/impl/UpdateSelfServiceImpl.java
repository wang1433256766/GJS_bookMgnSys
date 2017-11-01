package cn.com.miaoto.modules.userInfo.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.userInfo.inf.UpdateSelfService;
import cn.com.miaoto.modules.userInfo.model.UpdateSelfReq;
import cn.com.miaoto.modules.userInfo.model.UpdateSelfResp;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.util.SessionUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/9/5.
 */
@Service
public class UpdateSelfServiceImpl extends AbstractService<UpdateSelfReq, UpdateSelfResp> implements UpdateSelfService {
    @Resource
    UserInfoDao userInfoDao;

    @Override
    public UpdateSelfResp updateSelf(UpdateSelfReq reqBean, UpdateSelfResp respBean) {
        return (UpdateSelfResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UpdateSelfReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UpdateSelfReq reqBean, UpdateSelfResp respBean) throws Exception {
        UserInfo updateUser = reqBean.getUserInfo();
        Object obj = SessionUtil.getSessionValue("uid");
        if (obj == null) {
            LOGGER.error("get uid failed");
            return;
        }
        updateUser.setUid((int) obj);
        int effected = userInfoDao.update(updateUser);
        if (effected == 0) {
            LOGGER.error("update user info failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
