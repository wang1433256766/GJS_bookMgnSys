package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.UserInfo;

public class GetRolesByUserReq extends RequestInfo {

	private UserInfo user;

	public GetRolesByUserReq(UserInfo user) {
		this.user = user;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

}
