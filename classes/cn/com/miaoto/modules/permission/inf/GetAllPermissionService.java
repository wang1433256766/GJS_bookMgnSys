package cn.com.miaoto.modules.permission.inf;

import cn.com.miaoto.modules.permission.model.GetAllPermissionReq;
import cn.com.miaoto.modules.permission.model.GetAllPermissionResp;

/**
 * Created by hx on 2017/8/1.
 */
public interface GetAllPermissionService {
    GetAllPermissionResp getAllPermisssion(GetAllPermissionReq reqBean, GetAllPermissionResp respBean);
}
