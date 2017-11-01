package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.AddUsers2RoleReq;
import cn.com.miaoto.modules.role.model.AddUsers2RoleResp;

/**
 * Created by hx on 2017/7/31.
 */
public interface AddUsers2RoleService {

    AddUsers2RoleResp addUsers2Role(AddUsers2RoleReq reqBean, AddUsers2RoleResp respBean);
}
