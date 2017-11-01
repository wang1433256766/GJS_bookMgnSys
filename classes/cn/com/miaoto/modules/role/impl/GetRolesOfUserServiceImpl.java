package cn.com.miaoto.modules.role.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.RoleDao;
import cn.com.miaoto.modules.role.inf.GetRolesOfUserService;
import cn.com.miaoto.modules.role.model.GetRolesOfUserReq;
import cn.com.miaoto.modules.role.model.GetRolesOfUserResp;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hx on 2017/8/1.
 */
@Service
public class GetRolesOfUserServiceImpl extends AbstractService<GetRolesOfUserReq, GetRolesOfUserResp> implements GetRolesOfUserService {
    @Resource
    RoleDao roleDao;

    @Override
    public GetRolesOfUserResp getRolesByUser(GetRolesOfUserReq reqBean, GetRolesOfUserResp respBean) {
        return (GetRolesOfUserResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetRolesOfUserReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetRolesOfUserReq reqBean, GetRolesOfUserResp respBean) throws Exception {
        List<Role> roleList = roleDao.selectRolesOfUser(reqBean.getRole());
        Map<Role, List<UserInfo>> map = new HashMap<>();
        for (Role role : roleList) {
            if (map.containsKey(role)) {
                if (role.getUserInfoList().size() > 0) {
                    List<UserInfo> userInfoList = map.get(role);
                    userInfoList.add(role.getUserInfoList().get(0));
                    map.put(role, userInfoList);
                }
            } else {
                map.put(role, role.getUserInfoList());
            }
            role.setUserInfoList((List<UserInfo>) map.get(role));
        }
        List<Role> roleListRes = new ArrayList<>();
        roleListRes.addAll(map.keySet());
        respBean.setRoleList(roleListRes);
        respBean.setResultCode(1);
    }
}
