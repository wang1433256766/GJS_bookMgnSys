package cn.com.miaoto.modules.permission.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PermissionDao;
import cn.com.miaoto.dao.inf.RolePermissionDao;
import cn.com.miaoto.modules.permission.inf.UpdatePermissionOfRoleService;
import cn.com.miaoto.modules.permission.model.UpdatePermissionOfRoleReq;
import cn.com.miaoto.modules.permission.model.UpdatePermissionOfRoleResp;
import cn.com.miaoto.pojo.RolePermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2017/8/1.
 */
@Service
public class UpdatePermissionOfRoleServiceImpl extends AbstractService<UpdatePermissionOfRoleReq, UpdatePermissionOfRoleResp> implements UpdatePermissionOfRoleService {
    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllPermissionServiceImpl.class);

    @Resource
    PermissionDao permissionDao;

    @Resource
    RolePermissionDao rolePermissionDao;

    @Override
    public UpdatePermissionOfRoleResp updatePermission(UpdatePermissionOfRoleReq reqBean, UpdatePermissionOfRoleResp respBean) {
        return (UpdatePermissionOfRoleResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UpdatePermissionOfRoleReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UpdatePermissionOfRoleReq reqBean, UpdatePermissionOfRoleResp respBean) throws Exception {
        RolePermission query = new RolePermission();
        query.setRpRid(reqBean.getRid());
        int effected = rolePermissionDao.delete(query);
        if(effected == 0) {
            LOGGER.info("delete role-permission failed");
        }

        List<RolePermission> rolePermissionList = new ArrayList<>();
        String[] ids = reqBean.getPids().split(",");
        for(String id : ids){
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRpPid(Integer.valueOf(id.trim()));
            rolePermission.setRpRid(reqBean.getRid());
            rolePermissionList.add(rolePermission);
        }
        effected  = rolePermissionDao.addRolePermission(rolePermissionList);
        if(effected ==0) {
            LOGGER.error("batch insert RolePermission failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
