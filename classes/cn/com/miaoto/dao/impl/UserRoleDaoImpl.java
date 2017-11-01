package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.UserRoleDao;
import cn.com.miaoto.modules.role.model.AddRoles4UserReq;
import cn.com.miaoto.modules.role.model.AddUsers2RoleReq;
import cn.com.miaoto.pojo.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * userRoleDao
 * Created by hx on 2017/7/31.
 */
@Repository
public class UserRoleDaoImpl extends BaseDao implements UserRoleDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserRoleDaoImpl.class);

    @Override
    public int addRoles4User(AddRoles4UserReq reqBean) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("UserRole.insertRids", reqBean);
        } catch (Exception e) {
            throw new DBException("RolePermissionDaoImpl addRoles4User() error...", e);
        }
        return effected;
    }

    @Override
    public int addUsers2Role(AddUsers2RoleReq reqBean) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("UserRole.insertUids", reqBean);
        } catch (Exception e) {
            throw new DBException("RolePermissionDaoImpl addUsers2Role() error...", e);
        }
        return effected;
    }

    @Override
    public int deleteUserRole(UserRole userRole) throws DBException {
        int effected;
        try {
            effected = getSqlSession().delete("UserRole.delete", userRole);
        } catch (Exception e) {
            throw new DBException("RolePermissionDaoImpl deleteUserRole() error...", e);
        }
        return effected;
    }
}
