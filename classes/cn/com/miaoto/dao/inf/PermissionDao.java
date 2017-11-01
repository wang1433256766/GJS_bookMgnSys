package cn.com.miaoto.dao.inf;

import java.util.List;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.pojo.Permission;
import cn.com.miaoto.pojo.Role;

public interface PermissionDao {

	List<Permission> queryByRole(List<Role> roleList) throws DBException;

	List<Permission> queryByRole(Role role) throws DBException;

	List<Permission> queryAll() throws DBException;
}
