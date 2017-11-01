package cn.com.miaoto.modules.role.model;

import java.util.List;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Role;

public class GetRolesByUserResp extends ResponseInfo {
	private List<Role> roleList;

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
