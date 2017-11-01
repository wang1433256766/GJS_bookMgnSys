package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.AddRolePermissionReq;
import cn.com.miaoto.modules.role.model.AddRolePermissionResp;

/**
 * Created by hx on 2017/7/31.
 */
public interface AddRolePermissionService {
    AddRolePermissionResp addRolePermission(AddRolePermissionReq reqBean, AddRolePermissionResp respBean);
}
