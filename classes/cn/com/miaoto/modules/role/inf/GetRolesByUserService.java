package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.GetRolesByUserReq;
import cn.com.miaoto.modules.role.model.GetRolesByUserResp;

public interface GetRolesByUserService {

	GetRolesByUserResp getRolesByUser(GetRolesByUserReq reqBean, GetRolesByUserResp respBean);

}
