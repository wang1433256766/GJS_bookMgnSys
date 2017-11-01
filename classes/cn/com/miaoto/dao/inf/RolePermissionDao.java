package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.role.model.AddRolePermissionReq;
import cn.com.miaoto.pojo.RolePermission;

import java.util.List;

/**
 * Created by hx on 2017/7/26.
 */
public interface RolePermissionDao {

    public int addRolePermission(List<RolePermission> rolePermissionList) throws DBException;

    int addRolePermission2(AddRolePermissionReq reqBean) throws DBException;

    int delete(RolePermission rolePermission) throws DBException;
}
