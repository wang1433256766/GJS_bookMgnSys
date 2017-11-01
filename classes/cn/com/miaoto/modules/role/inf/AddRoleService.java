package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.AddRoleReq;
import cn.com.miaoto.modules.role.model.AddRoleResp;

/**
 * Created by hx on 2017/7/26.
 */
public interface AddRoleService {
    public AddRoleResp addRole(AddRoleReq reqBean, AddRoleResp respBean);
}
