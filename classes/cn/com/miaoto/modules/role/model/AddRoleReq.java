package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Role;

/**
 * Created by hx on 2017/7/26.
 */
public class AddRoleReq extends RequestInfo {
    private Role role;
    private String permissionIDs;

    public AddRoleReq(Role role, String permissionIDs) {
        this.role = role;
        this.permissionIDs = permissionIDs;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPermissionIDs() {
        return permissionIDs;
    }

    public void setPermissionIDs(String permissionIDs) {
        this.permissionIDs = permissionIDs;
    }
}
