package cn.com.miaoto.modules.userInfo.inf;

import cn.com.miaoto.modules.userInfo.model.GetAllUserInfoReq;
import cn.com.miaoto.modules.userInfo.model.GetAllUserInfoResp;

public interface GetAllUsersService {

    GetAllUserInfoResp getAllUsers(GetAllUserInfoReq reqBean, GetAllUserInfoResp respBean);

}
