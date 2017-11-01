package cn.com.miaoto.modules.role.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.UserRoleDao;
import cn.com.miaoto.modules.role.inf.AddRoles4UserService;
import cn.com.miaoto.modules.role.model.AddRoles4UserReq;
import cn.com.miaoto.modules.role.model.AddRoles4UserResp;
import cn.com.miaoto.pojo.UserRole;
import cn.com.miaoto.util.StringUtil;
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
public class AddRoles4UserServiceImpl extends AbstractService<AddRoles4UserReq, AddRoles4UserResp> implements AddRoles4UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddRoles4UserServiceImpl.class);

    @Resource
    public UserRoleDao userRoleDao;

    @Override
    public AddRoles4UserResp addRole4User(AddRoles4UserReq reqBean, AddRoles4UserResp respBean) {
        return (AddRoles4UserResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddRoles4UserReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddRoles4UserReq reqBean, AddRoles4UserResp respBean) throws Exception {
        // delete
        UserRole userRole = new UserRole();
        userRole.setUrUid(reqBean.getUid());
        userRoleDao.deleteUserRole(userRole);

        // insert
        String[] rids = reqBean.getRids().split(",");
        List<Integer> ridList = new ArrayList<>();
        for (String rid : rids) {
            if (StringUtil.isEmpty(rid)) {
                continue;
            }
            ridList.add(Integer.parseInt(rid));
        }
        if (ridList.size() == 0) {
            respBean.setResultCode(1);
            return;
        }
        reqBean.setRidList(ridList);
        int effeccted = userRoleDao.addRoles4User(reqBean);
        if (effeccted == 0) {
            LOGGER.error("userRole add failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
