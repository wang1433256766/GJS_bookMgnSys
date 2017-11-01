package cn.com.miaoto.modules.permission.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PermissionDao;
import cn.com.miaoto.modules.permission.inf.GetPermissionByRolesService;
import cn.com.miaoto.modules.permission.model.GetPermissionByRolesReq;
import cn.com.miaoto.modules.permission.model.GetPermissionByRolesResp;
import cn.com.miaoto.pojo.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GetPermissionByRolesServiceImpl extends AbstractService<GetPermissionByRolesReq, GetPermissionByRolesResp> implements GetPermissionByRolesService {
    @Resource
    PermissionDao permissionDao;

    @Override
    public GetPermissionByRolesResp getPermissionByRoles(GetPermissionByRolesReq reqBean, GetPermissionByRolesResp respBean) {
        return (GetPermissionByRolesResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetPermissionByRolesReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetPermissionByRolesReq reqBean, GetPermissionByRolesResp respBean) throws Exception {
        List<Permission> permissionList = permissionDao.queryByRole(reqBean.getRoleList());
        respBean.setPermissionList(permissionList);
        respBean.setResultCode(1);
    }
}
