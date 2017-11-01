package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.GetAllRolesReq;
import cn.com.miaoto.modules.role.model.GetAllRolesResp;

/**
 * Created by hx on 2017/7/31.
 */
public interface GetAllRolesService {
    GetAllRolesResp getAllRoles(GetAllRolesReq reqBean, GetAllRolesResp respBean);
}
