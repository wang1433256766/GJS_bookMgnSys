package cn.com.miaoto.modules.role.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.UserRoleDao;
import cn.com.miaoto.modules.role.inf.AddUsers2RoleService;
import cn.com.miaoto.modules.role.model.AddUsers2RoleReq;
import cn.com.miaoto.modules.role.model.AddUsers2RoleResp;
import cn.com.miaoto.pojo.UserRole;
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
public class AddUsers2RoleServiceImpl extends AbstractService<AddUsers2RoleReq, AddUsers2RoleResp> implements AddUsers2RoleService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AddUsers2RoleServiceImpl.class);

    @Resource
    public UserRoleDao userRoleDao;

    @Override
    public AddUsers2RoleResp addUsers2Role(AddUsers2RoleReq reqBean, AddUsers2RoleResp respBean) {
        return (AddUsers2RoleResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddUsers2RoleReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddUsers2RoleReq reqBean, AddUsers2RoleResp respBean) throws Exception {
        // delete
        UserRole userRole = new UserRole();
        userRole.setUrRid(reqBean.getRid());
        userRoleDao.deleteUserRole(userRole);

        // insert
        String[] uids = reqBean.getUids().split(",");
        List<Integer> uidList = new ArrayList<>();
        for(String uid :uids) {
            uidList.add(Integer.parseInt(uid));
        }
        reqBean.setUidList(uidList);
        int effected = userRoleDao.addUsers2Role(reqBean);
        if(effected == 0) {
            LOGGER.error("AddUsers2RoleServiceImpl addUsers2Role failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
