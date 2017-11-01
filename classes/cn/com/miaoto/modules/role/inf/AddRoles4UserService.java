package cn.com.miaoto.modules.role.inf;

import cn.com.miaoto.modules.role.model.AddRoles4UserReq;
import cn.com.miaoto.modules.role.model.AddRoles4UserResp;

/**
 * Created by hx on 2017/7/31.
 */
public interface AddRoles4UserService {
    AddRoles4UserResp addRole4User(AddRoles4UserReq reqBean, AddRoles4UserResp respBean);
}
