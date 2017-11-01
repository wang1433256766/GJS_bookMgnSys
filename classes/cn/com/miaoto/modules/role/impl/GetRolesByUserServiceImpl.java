package cn.com.miaoto.modules.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.RoleDao;
import cn.com.miaoto.modules.role.inf.GetRolesByUserService;
import cn.com.miaoto.modules.role.model.GetRolesByUserReq;
import cn.com.miaoto.modules.role.model.GetRolesByUserResp;
import cn.com.miaoto.pojo.Role;

@Service
public class GetRolesByUserServiceImpl extends AbstractService<GetRolesByUserReq, GetRolesByUserResp> implements GetRolesByUserService {
	@Resource
	RoleDao roleDao;

	@Override
	public GetRolesByUserResp getRolesByUser(GetRolesByUserReq reqBean, GetRolesByUserResp respBean) {
		return (GetRolesByUserResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(GetRolesByUserReq reqBean) {
		return true;
	}

	@Override
	protected void handle(GetRolesByUserReq reqBean, GetRolesByUserResp respBean) throws Exception {
		List<Role> roleList = roleDao.queryByUser(reqBean.getUser());
		respBean.setRoleList(roleList);
	}
}
