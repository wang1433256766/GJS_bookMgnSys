package cn.com.miaoto.modules.permission.inf;

import cn.com.miaoto.modules.permission.model.UpdatePermissionOfRoleReq;
import cn.com.miaoto.modules.permission.model.UpdatePermissionOfRoleResp;

/**
 * Created by hx on 2017/8/1.
 */
public interface UpdatePermissionOfRoleService {
    UpdatePermissionOfRoleResp updatePermission(UpdatePermissionOfRoleReq reqBean, UpdatePermissionOfRoleResp respBean);
}
