package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Role;

import java.util.List;

/**
 * Created by hx on 2017/8/1.
 */
public class GetRolesOfUserResp extends ResponseInfo {
    List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
