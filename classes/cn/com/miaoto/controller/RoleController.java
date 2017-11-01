package cn.com.miaoto.controller;

import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.permission.inf.UpdatePermissionOfRoleService;
import cn.com.miaoto.modules.permission.model.UpdatePermissionOfRoleReq;
import cn.com.miaoto.modules.permission.model.UpdatePermissionOfRoleResp;
import cn.com.miaoto.modules.role.inf.*;
import cn.com.miaoto.modules.role.model.*;
import cn.com.miaoto.pojo.Role;
import cn.com.miaoto.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/24.
 */
@Controller
@RequestMapping("/")
public class RoleController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Resource
    private GetRolesByUserService getRolesByUserService;

    @Resource
    private DeleteRoleService deleteRoleService;

    @Resource
    private AddRoleService addRoleService;

    @Resource
    private AddRoles4UserService addRoles4UserService;

    @Resource
    private AddUsers2RoleService addUsers2RoleService;

    @Resource
    private AddRolePermissionService addRolePermissionService;

    @Resource
    private GetAllRolesService getAllRolesSevice;

    @Resource
    private UpdatePermissionOfRoleService updatePermissionOfRoleService;

    @Resource
    GetRolesOfUserService getRolesOfUserService;

    @RequestMapping(value = "getRole", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getRole(UserInfo user) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetRolesByUserReq reqBean = new GetRolesByUserReq(user);
            GetRolesByUserResp respBean = new GetRolesByUserResp();

            respBean = getRolesByUserService.getRolesByUser(reqBean, respBean);
            if (respBean.getRoleList() == null || respBean.getRoleList().size() == 0) {
                msg.setMsg(1, "get user role info failed", null);
            }
            msg.setMsg(0, "get user role info success", respBean.getRoleList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("RoleController.getRole() catch exception ", e);
            msg.setMsg(1, "get user role info failed", null);
            return msg;
        }
    }


    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addRole(Role role, String permitionIds) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AddRoleReq reqBean = new AddRoleReq(role, permitionIds);
            AddRoleResp respBean = new AddRoleResp();
            addRoleService.addRole(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController addRole failed");
                msg.setMsg(1, "add role failed", null);
                return msg;
            }
            msg.setMsg(0, "add role success", null);
        } catch (Exception e) {
            LOGGER.error("RoleController.addRole() catch exception ", e);
            msg.setMsg(1, "add  role failed", null);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg deleteRole(Role role) {
        ReturnMsg msg = new ReturnMsg();
        try {
            DeleteRoleReq reqBean = new DeleteRoleReq(role);
            DeleteRoleResp respBean = new DeleteRoleResp();
            deleteRoleService.deleteRole(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController deleteRole failed");
                msg.setMsg(1, "delete  role failed", null);
                return msg;
            }
            msg.setMsg(0, "delete role success", null);
        } catch (Exception e) {
            LOGGER.error("RoleController.deleteRole() catch exception ", e);
            msg.setMsg(1, "delete  role failed", null);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "addRoles4User", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addRoles4User(String rids, int uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AddRoles4UserReq reqBean = new AddRoles4UserReq(rids, uid);
            AddRoles4UserResp respBean = new AddRoles4UserResp();
            addRoles4UserService.addRole4User(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController addRoles4User failed");
                msg.setMsg(1, "addRoles4User failed", null);
                return msg;
            }
            msg.setMsg(0, "addRoles4User success", null);
        } catch (Exception e) {
            LOGGER.error("RoleController.addRoles4User() catch exception ", e);
            msg.setMsg(1, "addRoles4User failed", null);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "addUsers2Role", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addUsers2Role(String uids, int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AddUsers2RoleReq reqBean = new AddUsers2RoleReq(uids, rid);
            AddUsers2RoleResp respBean = new AddUsers2RoleResp();
            addUsers2RoleService.addUsers2Role(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController addUsers2Role failed");
                msg.setMsg(1, "addUsers2Role failed", null);
                return msg;
            }
            msg.setMsg(0, "addUsers2Role success", null);
        } catch (Exception e) {
            LOGGER.error("RoleController.addUsers2Role() catch exception ", e);
            msg.setMsg(1, "addUsers2Role failed", null);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "addRolePermission", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addRolePermission(String pids, int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AddRolePermissionReq reqBean = new AddRolePermissionReq(rid, pids);
            AddRolePermissionResp respBean = new AddRolePermissionResp();
            addRolePermissionService.addRolePermission(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController addRolePermission failed");
                msg.setMsg(1, "addRolePermission failed", null);
                return msg;
            }
            msg.setMsg(0, "addRolePermission success", null);
        } catch (Exception e) {
            LOGGER.error("RoleController.addRolePermission() catch exception ", e);
            msg.setMsg(1, "addRolePermission failed", null);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "getAllRoles", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getAllRoles() {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetAllRolesReq reqBean = new GetAllRolesReq();
            GetAllRolesResp respBean = new GetAllRolesResp();
            getAllRolesSevice.getAllRoles(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController getAllRoles() failed");
                msg.setMsg(1, "getAllRoles failed", null);
                return msg;
            }
            msg.setMsg(0, "getAllRoles success", respBean.getRoleList());
        } catch (Exception e) {
            LOGGER.error("RoleController.getAllRoles() catch exception ", e);
            msg.setMsg(1, "getAllRoles failed", null);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "getRolesOfUser", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getRolesOfUser(Role role) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetRolesOfUserReq reqBean = new GetRolesOfUserReq(role);
            GetRolesOfUserResp respBean = new GetRolesOfUserResp();
            getRolesOfUserService.getRolesByUser(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController getRolesOfUser() failed");
                msg.setMsg(1, "getRolesOfUser failed", null);
                return msg;
            }
            msg.setMsg(0, "getRolesOfUser success", respBean.getRoleList());
        } catch (Exception e) {
            LOGGER.error("RoleController.getRolesOfUser() catch exception ", e);
            msg.setMsg(1, "getRolesOfUser failed", null);
            return msg;
        }
        return msg;
    }

    @RequestMapping(value = "changeRoleOfPmtion", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg changeRoleOfPmtion(int rid, String pids) {
        ReturnMsg msg = new ReturnMsg();
        try {
            UpdatePermissionOfRoleReq reqBean = new UpdatePermissionOfRoleReq(rid, pids);
            UpdatePermissionOfRoleResp respBean = new UpdatePermissionOfRoleResp();
            updatePermissionOfRoleService.updatePermission(reqBean, respBean);
            if (respBean.getResultCode() != 1) {
                LOGGER.error("RoleController changeRoleOfPmtion() failed");
                msg.setMsg(1, "changeRoleOfPmtion failed", null);
                return msg;
            }
            msg.setMsg(0, "changeRoleOfPmtion success", null);
        } catch (Exception e) {
            LOGGER.error("RoleController.changeRoleOfPmtion() catch exception ", e);
            msg.setMsg(1, "changeRoleOfPmtion failed", null);
            return msg;
        }
        return msg;
    }
}
