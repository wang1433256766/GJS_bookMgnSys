package cn.com.miaoto.modules.role.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.RoleDao;
import cn.com.miaoto.dao.inf.UserRoleDao;
import cn.com.miaoto.modules.role.inf.DeleteRoleService;
import cn.com.miaoto.modules.role.model.DeleteRoleReq;
import cn.com.miaoto.modules.role.model.DeleteRoleResp;
import cn.com.miaoto.pojo.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/31.
 */
@Service
public class DeleteRoleServiceImpl extends AbstractService<DeleteRoleReq,DeleteRoleResp> implements DeleteRoleService {

    public static final Logger LOGGER = LoggerFactory.getLogger(DeleteRoleServiceImpl.class);

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public DeleteRoleResp deleteRole(DeleteRoleReq reqBean, DeleteRoleResp respBean) {
        return (DeleteRoleResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(DeleteRoleReq reqBean) {
        return true;
    }

    @Override
    protected void handle(DeleteRoleReq reqBean, DeleteRoleResp respBean) throws Exception {
        // delete record of table:role
        int effected = roleDao.delete(reqBean.getRole());
        if(effected == 0) {
            LOGGER.error("DeleteRoleServiceImpl delete role failed");
            return;
        }

        // delete record of table:userRole
        UserRole userRole = new UserRole();
        userRole.setUrRid(reqBean.getRole().getRid());
        effected = userRoleDao.deleteUserRole(userRole);
        if(effected == 0) {
            LOGGER.info("DeleteRoleServiceImpl delete role failed");
        }

        //delete record of table:rolePermission
        //TODO

        respBean.setResultCode(1);
    }
}
