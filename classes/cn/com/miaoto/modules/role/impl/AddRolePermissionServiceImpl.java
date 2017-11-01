package cn.com.miaoto.modules.role.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.RolePermissionDao;
import cn.com.miaoto.modules.role.inf.AddRolePermissionService;
import cn.com.miaoto.modules.role.model.AddRolePermissionReq;
import cn.com.miaoto.modules.role.model.AddRolePermissionResp;
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
public class AddRolePermissionServiceImpl extends AbstractService<AddRolePermissionReq, AddRolePermissionResp> implements AddRolePermissionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AddRoles4UserServiceImpl.class);

    @Resource
    public RolePermissionDao rolePermissionDao;

    @Override
    public AddRolePermissionResp addRolePermission(AddRolePermissionReq reqBean, AddRolePermissionResp respBean) {
        return (AddRolePermissionResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddRolePermissionReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddRolePermissionReq reqBean, AddRolePermissionResp respBean) throws Exception {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRpRid(reqBean.getRid());
        int effected = rolePermissionDao.delete(rolePermission);
        if(effected == 0) {
            LOGGER.info("AddRolePermissionServiceImpl addRolePermission() delete record result is 0");
        }

        String[] pids = reqBean.getPids().split(",");
        List<Integer> pidList = new ArrayList<>();
        for (String pid : pids) {
            pidList.add(Integer.parseInt(pid));
        }
        reqBean.setPidList(pidList);
        effected = rolePermissionDao.addRolePermission2(reqBean);
        if(effected == 0) {
            LOGGER.error("AddRolePermissionServiceImpl addRolePermission() add record failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
