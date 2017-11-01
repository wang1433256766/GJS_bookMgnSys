package cn.com.miaoto.common.realm;

import cn.com.miaoto.common.exception.UserNotPermitException;
import cn.com.miaoto.modules.permission.inf.GetPermissionByRolesService;
import cn.com.miaoto.modules.permission.model.GetPermissionByRolesReq;
import cn.com.miaoto.modules.permission.model.GetPermissionByRolesResp;
import cn.com.miaoto.modules.role.inf.GetRolesByUserService;
import cn.com.miaoto.modules.role.model.GetRolesByUserReq;
import cn.com.miaoto.modules.role.model.GetRolesByUserResp;
import cn.com.miaoto.modules.userInfo.inf.GetUserService;
import cn.com.miaoto.modules.userInfo.model.GetUserInfoReq;
import cn.com.miaoto.modules.userInfo.model.GetUserInfoResp;
import cn.com.miaoto.pojo.Permission;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class CardRealm extends AuthorizingRealm {
    public static final Logger LOGGER = LoggerFactory.getLogger(CardRealm.class);

    @Resource
    GetUserService getUserService;

    @Resource
    GetRolesByUserService getRolesByUserService;

    @Resource
    GetPermissionByRolesService getPermissionByRolesService;

    /**
     * shiro框架查询当前登录用户是否有角色或权限时调用此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        // role
        String idcard = (String) super.getAvailablePrincipal(principals);
        UserInfo queryUser = new UserInfo();
        queryUser.setIdcard(idcard);
        GetRolesByUserReq reqBean = new GetRolesByUserReq(queryUser);
        GetRolesByUserResp respBean = new GetRolesByUserResp();
        UserInfo user = new UserInfo();
        if (idcard != null) {
            try {
                user.setIdcard(idcard);
                reqBean.setUser(user);
                respBean = getRolesByUserService.getRolesByUser(reqBean, respBean);
                if (respBean != null && respBean.getRoleList() != null) {
                    for (Role role : respBean.getRoleList()) {
                        simpleAuthorInfo.addRole(role.getRname());
                    }
                }
            } catch (Exception e) {
                LOGGER.error("error", e);
                return null;
            }
        }

        // permission
        GetPermissionByRolesReq reqBean2 = new GetPermissionByRolesReq();
        GetPermissionByRolesResp respBean2 = new GetPermissionByRolesResp();
        reqBean2.setRoleList(respBean.getRoleList());
        respBean2 = getPermissionByRolesService.getPermissionByRoles(reqBean2, respBean2);
        if (respBean2 != null && respBean2.getPermissionList() != null) {
            for (Permission permisssion : respBean2.getPermissionList()) {
                simpleAuthorInfo.addStringPermission(permisssion.getPname());
            }
        }
        return simpleAuthorInfo;
    }

    /**
     * 用户登录是调用此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        GetUserInfoReq reqBean = new GetUserInfoReq();
        GetUserInfoResp respBean = new GetUserInfoResp();
        if (token.getUsername() != null) {
            try {
                UserInfo user = new UserInfo();
                user.setIdcard(token.getUsername());
                reqBean.setUser(user);
                respBean = getUserService.getUserInfo(reqBean, respBean);
            } catch (Exception e) {
                LOGGER.error("MyRealm.doGetAuthenticationInfo() catch exception : ", e);
                return null;
            }
            if (respBean == null) {
                throw new UnknownAccountException();
            }
            if (respBean.getUser().getStatus() > 0) {
                throw new UserNotPermitException();
            }
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(respBean.getUser().getIdcard(), respBean.getUser().getPwd(),
                    ByteSource.Util.bytes(respBean.getUser().getSalt()), this.getName());
            authcInfo.getCredentials();
            this.setSession("card", respBean.getUser().getCard());
            this.setSession("uid", respBean.getUser().getUid());
            this.setSession("name", respBean.getUser().getUname());
            // this.setSession("role", respBean.getUser().getRole());
            return authcInfo;
        }
        return null;
    }

    /**
     * 设置session
     *
     * @param key   session的key
     * @param value session的value
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
