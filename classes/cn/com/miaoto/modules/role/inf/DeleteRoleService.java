package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.DeleteRoleReq;
import cn.com.miaoto.modules.role.model.DeleteRoleResp;

/**
 * Created by hx on 2017/7/31.
 */
public interface DeleteRoleService {
    DeleteRoleResp deleteRole(DeleteRoleReq reqBean, DeleteRoleResp respBean);
}
