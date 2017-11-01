package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Role;

/**
 * Created by hx on 2017/8/1.
 */
public class GetRolesOfUserReq extends RequestInfo {
    private Role role;

    public GetRolesOfUserReq(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
