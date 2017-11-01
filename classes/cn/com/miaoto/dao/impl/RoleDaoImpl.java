package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.RoleDao;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * roleDao
 */
@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public List<Role> queryByUser(UserInfo user) throws DBException {
        List<Role> roleList;
        try {
            roleList = getSqlSession().selectList("Role.select", user);
        } catch (Exception e) {
            throw new DBException("RoleDaoImpl queryByUser() error...", e);
        }
        return roleList;
    }


    @Override
    public int addRole(Role role) throws DBException {
        int rid;
        try {
            rid = getSqlSession().insert("Role.insert", role);
            if (role.getRid() != 0 && role.getRid() != 1) {
                rid = role.getRid();
            }
        } catch (Exception e) {
            throw new DBException("RoleDaoImpl addRole() error...", e);
        }
        return rid;
    }

    @Override
    public int delete(Role role) throws DBException {
        int effected;
        try {
            effected = getSqlSession().delete("Role.delete", role);
        } catch (Exception e) {
            throw new DBException("RoleDaoImpl delete() error...", e);
        }
        return effected;
    }

    @Override
    public List<Role> selectAll() throws DBException {
        List<Role> roleList;
        try {
            roleList = getSqlSession().selectList("Role.selectAll", null);
        } catch (Exception e) {
            throw new DBException("RoleDaoImpl selectAll() error...", e);
        }
        return roleList;
    }

    @Override
    public List<Role> selectRolesOfUser(Role role) throws DBException {
        List<Role> roleList;
        try {
            roleList = getSqlSession().selectList("Role.selectRolesWithUser", role);
        } catch (Exception e) {
            throw new DBException("RoleDaoImpl selectRolesOfUser() error...", e);
        }
        return roleList;
    }
}
