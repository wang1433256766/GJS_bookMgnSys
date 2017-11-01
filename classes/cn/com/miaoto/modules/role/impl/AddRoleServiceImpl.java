package cn.com.miaoto.modules.role.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.impl.BatchInfoDaoImpl;
import cn.com.miaoto.dao.inf.RoleDao;
import cn.com.miaoto.dao.inf.RolePermissionDao;
import cn.com.miaoto.modules.role.inf.AddRoleService;
import cn.com.miaoto.modules.role.inf.GetRolesByUserService;
import cn.com.miaoto.modules.role.model.AddRoleReq;
import cn.com.miaoto.modules.role.model.AddRoleResp;
import cn.com.miaoto.modules.role.model.GetRolesByUserReq;
import cn.com.miaoto.modules.role.model.GetRolesByUserResp;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.RolePermission;
import cn.com.miaoto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2017/7/26.
 */
@Service
public class AddRoleServiceImpl  extends AbstractService<AddRoleReq, AddRoleResp> implements AddRoleService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddRoleServiceImpl.class);

    @Resource
    private RoleDao roleDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public AddRoleResp addRole(AddRoleReq reqBean, AddRoleResp respBean) {
        return (AddRoleResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddRoleReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddRoleReq reqBean, AddRoleResp respBean) throws Exception {
        List<RolePermission> rolePermissionList = new ArrayList<>();
        int rid = roleDao.addRole(reqBean.getRole());

        if(rid == 0) {
            LOGGER.error("add role failed");
            return;
        }
        if(!StringUtil.isEmpty(reqBean.getPermissionIDs())) {
            String[] ids = reqBean.getPermissionIDs().split(",");
            for(String id : ids){
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRpPid(Integer.valueOf(id.trim()));
                rolePermission.setRpRid(rid);
                rolePermissionList.add(rolePermission);
            }
            int effected  = rolePermissionDao.addRolePermission(rolePermissionList);
            if(effected ==0) {
                LOGGER.error("batch insert RolePermission failed");
                return;
            }
        }
        respBean.setResultCode(1);
    }
}
