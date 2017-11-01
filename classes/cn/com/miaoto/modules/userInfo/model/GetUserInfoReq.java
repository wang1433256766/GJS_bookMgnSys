package cn.com.miaoto.modules.userInfo.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.UserInfo;

public class GetUserInfoReq extends RequestInfo {
	private UserInfo user;

	public GetUserInfoReq() {
		super();
	}

	public GetUserInfoReq(UserInfo user) {
		super();
		this.user = user;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

}
