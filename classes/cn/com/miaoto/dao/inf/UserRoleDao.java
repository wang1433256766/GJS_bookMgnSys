package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.role.model.AddRoles4UserReq;
import cn.com.miaoto.modules.role.model.AddUsers2RoleReq;
import cn.com.miaoto.pojo.UserRole;

/**
 * Created by hx on 2017/7/31.
 */
public interface UserRoleDao {
    int addRoles4User(AddRoles4UserReq reqBean) throws DBException;

    int addUsers2Role(AddUsers2RoleReq reqBean) throws DBException;

    int deleteUserRole(UserRole userRole) throws DBException;
}
