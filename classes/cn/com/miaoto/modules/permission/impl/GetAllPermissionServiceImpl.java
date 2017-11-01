package cn.com.miaoto.modules.permission.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PermissionDao;
import cn.com.miaoto.modules.permission.inf.GetAllPermissionService;
import cn.com.miaoto.modules.permission.model.GetAllPermissionReq;
import cn.com.miaoto.modules.permission.model.GetAllPermissionResp;
import cn.com.miaoto.pojo.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/1.
 */
@Service
public class GetAllPermissionServiceImpl extends AbstractService<GetAllPermissionReq, GetAllPermissionResp> implements GetAllPermissionService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllPermissionServiceImpl.class);

    @Resource
    PermissionDao permissionDao;

    @Override
    public GetAllPermissionResp getAllPermisssion(GetAllPermissionReq reqBean, GetAllPermissionResp respBean) {
        return (GetAllPermissionResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetAllPermissionReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetAllPermissionReq reqBean, GetAllPermissionResp respBean) throws Exception {
        List<Permission> permissionList = permissionDao.queryAll();
        if(permissionList == null || permissionList.size() == 0) {
            LOGGER.error("GetAllPermissionServiceImpl get permissionList failed");
            return;
        }
        respBean.setPermissionList(permissionList);
        respBean.setResultCode(1);
    }
}
