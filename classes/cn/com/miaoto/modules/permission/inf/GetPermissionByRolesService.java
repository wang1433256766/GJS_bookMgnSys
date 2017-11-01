package cn.com.miaoto.modules.permission.inf;

import cn.com.miaoto.modules.permission.model.GetPermissionByRolesReq;
import cn.com.miaoto.modules.permission.model.GetPermissionByRolesResp;

public interface GetPermissionByRolesService {

	GetPermissionByRolesResp getPermissionByRoles(GetPermissionByRolesReq reqBean, GetPermissionByRolesResp respBean);

}
