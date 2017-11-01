package cn.com.miaoto.modules.permission.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Permission;

import java.util.List;

/**
 * Created by hx on 2017/8/1.
 */
public class GetAllPermissionResp extends ResponseInfo {
    List<Permission> permissionList;

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
