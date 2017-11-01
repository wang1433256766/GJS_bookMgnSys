package cn.com.miaoto.modules.permission.model;

import java.util.List;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Role;

public class GetPermissionByRolesReq extends RequestInfo {
	private List<Role> roleList;

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}
