package cn.com.miaoto.modules.permission.model;

import java.util.List;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Permission;

public class GetPermissionByRolesResp extends ResponseInfo {
	private List<Permission> permissionList;

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

}
