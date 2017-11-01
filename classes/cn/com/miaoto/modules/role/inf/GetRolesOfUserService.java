package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.GetRolesOfUserReq;
import cn.com.miaoto.modules.role.model.GetRolesOfUserResp;

/**
 * Created by hx on 2017/8/1.
 */
public interface GetRolesOfUserService {
    GetRolesOfUserResp getRolesByUser(GetRolesOfUserReq reqBean, GetRolesOfUserResp respBean);
}
