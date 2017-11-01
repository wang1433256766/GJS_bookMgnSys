package cn.com.miaoto.dao.inf;

import java.util.List;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.UserInfo;

public interface RoleDao {

	List<Role> queryByUser(UserInfo user) throws DBException;

	int addRole(Role role) throws DBException;

	int delete(Role role) throws DBException;

	List<Role> selectAll() throws DBException;

	List<Role> selectRolesOfUser(Role role) throws DBException;
}
