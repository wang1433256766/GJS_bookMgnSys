package cn.com.miaoto.controller;

import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.permission.inf.GetAllPermissionService;
import cn.com.miaoto.modules.permission.inf.GetPermissionByRolesService;
import cn.com.miaoto.modules.permission.model.GetAllPermissionReq;
import cn.com.miaoto.modules.permission.model.GetAllPermissionResp;
import cn.com.miaoto.modules.permission.model.GetPermissionByRolesReq;
import cn.com.miaoto.modules.permission.model.GetPermissionByRolesResp;
import cn.com.miaoto.pojo.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2017/8/1.
 */
@Controller
@RequestMapping("/")
public class PermissionController extends BaseController {
    public static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);

    @Resource
    private GetAllPermissionService getAllPermissionService;

    @Resource
    private GetPermissionByRolesService getPermissionByRolesService;

    @RequestMapping(value = "getAllPermisssion", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getAllPermisssion() {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetAllPermissionReq reqBean = new GetAllPermissionReq();
            GetAllPermissionResp respBean = new GetAllPermissionResp();

            respBean = getAllPermissionService.getAllPermisssion(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get all permission failed");
                msg.setMsg(1, "get all permission failed", null);
            }
            msg.setMsg(0, "get all permission success", respBean.getPermissionList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("PsermisssionController.getAllPermisssion() catch exception ", e);
            msg.setMsg(1, "get all permission failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getPermisssionOfRole", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getPermisssionOfRole(Role role) {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<Role> roleList = new ArrayList<>();
            roleList.add(role);
            GetPermissionByRolesReq reqBean = new GetPermissionByRolesReq();
            reqBean.setRoleList(roleList);
            GetPermissionByRolesResp respBean = new GetPermissionByRolesResp();

            respBean = getPermissionByRolesService.getPermissionByRoles(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("getPermisssionOfRole failed");
                msg.setMsg(1, "getPermisssionOfRole failed", null);
            }
            msg.setMsg(0, "getPermisssionOfRole success", respBean.getPermissionList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("PsermisssionController.getPermisssionOfRole() catch exception ", e);
            msg.setMsg(1, "getPermisssionOfRole failed", null);
            return msg;
        }
    }
}
