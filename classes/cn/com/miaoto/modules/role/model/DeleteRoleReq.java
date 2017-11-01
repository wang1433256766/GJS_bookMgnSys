package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Role;

/**
 * Created by hx on 2017/7/31.
 */
public class DeleteRoleReq extends RequestInfo {
    private Role role;

    public DeleteRoleReq(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
