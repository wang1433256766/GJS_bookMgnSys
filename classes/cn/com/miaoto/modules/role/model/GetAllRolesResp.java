package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Role;

import java.util.List;

/**
 * Created by hx on 2017/7/31.
 */
public class GetAllRolesResp extends ResponseInfo {
    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
