package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.PermissionDao;
import cn.com.miaoto.pojo.Permission;
import cn.com.miaoto.pojo.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * permissionDao
 */
@Repository
public class PermissionDaoImpl extends BaseDao implements PermissionDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(PermissionDaoImpl.class);

    @Override
    public List<Permission> queryByRole(List<Role> roleList) throws DBException {
        List<Permission> permissionList;
        try {
            permissionList = getSqlSession().selectList("Permission.select", roleList);
        } catch (Exception e) {
            throw new DBException("PermissionDaoImpl queryByRole(roleList) error...", e);
        }
        return permissionList;
    }

    @Override
    public List<Permission> queryByRole(Role role) throws DBException {
        List<Permission> permissionList;
        try {
            permissionList = getSqlSession().selectList("Permission.banchSelect", role);
        } catch (Exception e) {
            throw new DBException("PermissionDaoImpl queryByRole(role) error...", e);
        }
        return permissionList;
    }

    @Override
    public List<Permission> queryAll() throws DBException {
        List<Permission> permissionList;
        try {
            permissionList = getSqlSession().selectList("Permission.selectAll", null);
        } catch (Exception e) {
            throw new DBException("PermissionDaoImpl queryAll() error...", e);
        }
        return permissionList;
    }
}
