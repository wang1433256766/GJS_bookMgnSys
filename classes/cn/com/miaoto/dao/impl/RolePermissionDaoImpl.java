package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.RolePermissionDao;
import cn.com.miaoto.modules.role.model.AddRolePermissionReq;
import cn.com.miaoto.pojo.RolePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * rolePermissionDao
 * Created by hx on 2017/7/26.
 */
@Repository
public class RolePermissionDaoImpl extends BaseDao implements RolePermissionDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(RolePermissionDaoImpl.class);

    @Override
    public int addRolePermission(List<RolePermission> rolePermissionList) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("RolePermission.insert", rolePermissionList);
        } catch (Exception e) {
            throw new DBException("RolePermissionDaoImpl addRolePermission() error...", e);
        }
        return effected;
    }

    @Override
    public int addRolePermission2(AddRolePermissionReq reqBean) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("RolePermission.banchInsert", reqBean);
        } catch (Exception e) {
            throw new DBException("RolePermissionDaoImpl addRolePermission2() error...", e);
        }
        return effected;
    }

    @Override
    public int delete(RolePermission rolePermission) throws DBException {
        int effected;
        try {
            effected = getSqlSession().delete("RolePermission.delete", rolePermission);
        } catch (Exception e) {
            throw new DBException("RolePermissionDaoImpl delete() error...", e);
        }
        return effected;
    }
}
