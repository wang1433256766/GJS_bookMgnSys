package cn.com.miaoto.modules.userInfo.inf;

import cn.com.miaoto.modules.userInfo.model.UserRegisterReq;
import cn.com.miaoto.modules.userInfo.model.UserRegisterResp;

/**
 * Created by hx on 2017/7/27.
 */
public interface UserRegisterService {
    UserRegisterResp register(UserRegisterReq reqBean, UserRegisterResp respBean);
}
