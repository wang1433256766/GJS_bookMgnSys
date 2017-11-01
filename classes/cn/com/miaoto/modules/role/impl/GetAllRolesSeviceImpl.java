package cn.com.miaoto.modules.role.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PermissionDao;
import cn.com.miaoto.dao.inf.RoleDao;
import cn.com.miaoto.dao.inf.RolePermissionDao;
import cn.com.miaoto.modules.role.inf.GetAllRolesService;
import cn.com.miaoto.modules.role.model.GetAllRolesReq;
import cn.com.miaoto.modules.role.model.GetAllRolesResp;
import cn.com.miaoto.pojo.Permission;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.RolePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2017/7/31.
 */
@Service
public class GetAllRolesSeviceImpl extends AbstractService<GetAllRolesReq, GetAllRolesResp> implements GetAllRolesService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllRolesSeviceImpl.class);

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Override
    public GetAllRolesResp getAllRoles(GetAllRolesReq reqBean, GetAllRolesResp respBean) {
        return (GetAllRolesResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetAllRolesReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetAllRolesReq reqBean, GetAllRolesResp respBean) throws Exception {
        List<Role> roleList = roleDao.selectAll();
        if(roleList == null || roleList.size() == 0) {
            LOGGER.error("GetAllRolesSeviceImpl getAllRoles() failed");
            return;
        }
        for(int i = 0; i < roleList.size(); i++) {
            List<Permission> permissionsList = permissionDao.queryByRole(roleList.get(i));
            roleList.get(i).setPermissionList(permissionsList);
        }
        respBean.setRoleList(roleList);
        respBean.setResultCode(1);
    }
}
