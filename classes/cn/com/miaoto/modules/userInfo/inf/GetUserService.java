package cn.com.miaoto.modules.userInfo.inf;

import cn.com.miaoto.modules.userInfo.model.GetUserInfoReq;
import cn.com.miaoto.modules.userInfo.model.GetUserInfoResp;

public interface GetUserService {

	GetUserInfoResp getUserInfo(GetUserInfoReq reqBean, GetUserInfoResp respBean);

}
