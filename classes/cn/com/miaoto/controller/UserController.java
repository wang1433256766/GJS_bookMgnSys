package cn.com.miaoto.controller;


import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.exception.UserNotPermitException;
import cn.com.miaoto.common.exception.UserWaittingPermitException;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.book.inf.GetUserBorrowedBookService;
import cn.com.miaoto.modules.book.model.GetUserBorrowedBookReq;
import cn.com.miaoto.modules.book.model.GetUserBorrowedBookResp;
import cn.com.miaoto.modules.borrow.inf.GetBorrowInfoService;
import cn.com.miaoto.modules.borrow.model.GetBorrowInfoReq;
import cn.com.miaoto.modules.borrow.model.GetBorrowInfoResp;
import cn.com.miaoto.modules.role.inf.GetRolesByUserService;
import cn.com.miaoto.modules.role.model.GetRolesByUserReq;
import cn.com.miaoto.modules.role.model.GetRolesByUserResp;
import cn.com.miaoto.modules.userInfo.inf.*;
import cn.com.miaoto.modules.userInfo.model.*;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.pojo.common.SearchFilter;
import cn.com.miaoto.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    private GetUserService getUserService;

    @Resource
    private GetUserBorrowedBookService getUserBorrowedBookService;

    @Resource
    private UserRegisterService uerRegisterService;

    @Resource
    private UpdateUserService updateUserService;

    @Resource
    private GetAllUsersService getAllUsersService;

    @Resource
    private GetRolesByUserService getRolesByUserService;

    @Resource
    private GetBorrowInfoService getBorrowInfoService;

    @Resource
    private UpdateSelfService updateSelfService;

    /**
     * 登录
     */
    @RequestMapping(value = "dologin", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg doLogin(UserInfo user) {
        ReturnMsg msg = new ReturnMsg();

        String userName = user.getIdcard();
        String password = user.getPwd();

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                //
                GetRolesByUserReq reqBean = new GetRolesByUserReq(user);
                GetRolesByUserResp respBean = new GetRolesByUserResp();
                respBean = getRolesByUserService.getRolesByUser(reqBean, respBean);
                msg.setData(respBean.getRoleList());

                msg.setStatus(SiomConstants.LOGIN_SUCCESS);
                msg.setMsg(SiomConstants.LOGIN_SUCCESS_MSG);
                return msg;
            } else {
                msg.setStatus(SiomConstants.LOGIN_FAILED);
                msg.setMsg(SiomConstants.LOGIN_FAILED_MSG);
                return msg;
            }
        } catch (IncorrectCredentialsException e) {
            msg.setStatus(SiomConstants.WRONG_PASSWORD);
            msg.setMsg(SiomConstants.WRONG_PASSWORD_MSG);
            return msg;
        } catch (ExcessiveAttemptsException e) {
            msg.setStatus(SiomConstants.FAILED_TOO_MUCH);
            msg.setMsg(SiomConstants.FAILED_TOO_MUCH_MSG);
            return msg;
        } catch (LockedAccountException e) {
            msg.setStatus(SiomConstants.USER_IS_LOCKED);
            msg.setMsg(SiomConstants.USER_IS_LOCKED_MSG);
            return msg;
        } catch (DisabledAccountException e) {
            msg.setStatus(SiomConstants.USER_IS_BANNED);
            msg.setMsg(SiomConstants.USER_IS_BANNED_MSG);
            return msg;
        } catch (ExpiredCredentialsException e) {
            msg.setStatus(SiomConstants.USER_IS_TIMEOUT);
            msg.setMsg(SiomConstants.USER_IS_TIMEOUT_MSG);
            return msg;
        } catch (UnknownAccountException e) {
            msg.setStatus(SiomConstants.USER_IS_NOT_EXIST);
            msg.setMsg(SiomConstants.USER_IS_NOT_EXIST_MSG);
            return msg;
        } catch (UnauthorizedException e) {
            msg.setStatus(SiomConstants.USER_IS_NOT_AUTH);
            msg.setMsg(SiomConstants.USER_IS_NOT_AUTH_MSG);
            return msg;
        } catch (UserWaittingPermitException e) {
            msg.setStatus(SiomConstants.LOGIN_WAITTING_PERMIT);
            msg.setMsg(SiomConstants.LOGIN_WAITTING_PERMIT_MSG);
            return msg;
        } catch (UserNotPermitException e) {
            msg.setStatus(SiomConstants.LOGIN_ADMIN_NOT_PERMIT);
            msg.setMsg(SiomConstants.LOGIN_ADMIN_NOT_PERMIT_MSG);
            return msg;
        } catch (AuthenticationException e) {
            msg.setStatus(SiomConstants.LOGIN_FAILED);
            msg.setMsg(SiomConstants.LOGIN_FAILED_MSG);
            return msg;
        }
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public void logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            LOGGER.error("catch exception : ", e);
        }
    }

    /**
     * 通过卡号获取用户信息
     *
     * @param user 用户信息
     * @return ReturnMsg
     */
    @RequestMapping(value = "/getUserInfoByCard", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getUserInfo(UserInfo user) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetUserInfoReq reqBean;
            GetUserInfoResp respBean = new GetUserInfoResp();
            reqBean = new GetUserInfoReq(user);
            respBean = getUserService.getUserInfo(reqBean, respBean);
            if (respBean != null && respBean.getUser() != null) {
                respBean.clearSensitive();

                //get user borrowed  book
                GetUserBorrowedBookReq reqBean2 = new GetUserBorrowedBookReq(respBean.getUser().getUid());
                GetUserBorrowedBookResp respBean2 = new GetUserBorrowedBookResp();
                respBean2 = getUserBorrowedBookService.getBookInfoByUid(reqBean2, respBean2);
                respBean.setBorrowList(respBean2.getBorrows());
                msg.setMsg(0, "get user info success", respBean);
            } else {
                msg.setMsg(1, "failed", null);
            }
            return msg;
        } catch (Exception e) {
            LOGGER.error("UserController getUserInfo() catch exception : ");
            msg.setMsg(1, "failed", null);
            return msg;
        }
    }

    /**
     * 获取登录状态
     *
     * @return ReturnMsg
     */
    @RequestMapping(value = "/isLogin", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg isLogin() {
        ReturnMsg msg = new ReturnMsg();
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                msg.setStatus(0);
                msg.setMsg("login success");
                msg.setData("{\"card\" : \"" + SessionUtil.getSessionValue("card") + "\", \"uid\" :　\"" + SessionUtil.getSessionValue("uid")
                        + "\", \"name\" : \"" + SessionUtil.getSessionValue("name") + "\"}");
            } else {
                msg.setStatus(1);
                msg.setMsg("user is not login");
            }
        } catch (Exception e) {
            LOGGER.error("AuthController.isLogin() catch exception : ", e);
            msg.setStatus(2);
            msg.setMsg("query user login status failed");
            return msg;
        }
        return msg;
    }

    /**
     * 注册
     *
     * @param user 用户信息
     * @return ReturnMsg
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg register(UserInfo user) {
        ReturnMsg msg = new ReturnMsg();
        try {
            UserRegisterReq reqBean = new UserRegisterReq(user);
            UserRegisterResp respBean = new UserRegisterResp();
            uerRegisterService.register(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                msg.setMsg(1, "add user failed", null);
                return msg;
            }
            msg.setMsg(0, "add user success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("AuthController.register() catch exception : ", e);
            msg.setStatus(2);
            msg.setMsg("register failed");
            return msg;
        }
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return ReturnMsg
     */
    @RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg update(UserInfo user) {
        ReturnMsg msg = new ReturnMsg();
        try {
            if (user.getStatus() != null && user.getStatus() != 0) {
                // 如果用户状态是非正常状态, 检查借阅状态
                SearchFilter searchFilter = new SearchFilter("bo_id", "desc", 10, 1);
                GetBorrowInfoReq reqBean2 = new GetBorrowInfoReq(searchFilter);
                GetBorrowInfoResp respBean2 = new GetBorrowInfoResp();
                reqBean2.setUid(user.getUid());

                respBean2 = getBorrowInfoService.getBorrowInfoRecord(reqBean2, respBean2);
                if (respBean2.getResultCode() == 0) {
                    LOGGER.error("get borrow info failed");
                    msg.setMsg(1, "get borrow info failed", null);
                    return msg;
                }
                if (respBean2.getBorrowList().size() != 0) {
                    LOGGER.error("ban user failed, user has borrowed book record");
                    msg.setMsg(2, "ban user failed, user has borrowed book record", respBean2.getBorrowList());
                    return msg;
                }
            }


            // 更新用户
            UpdateUserReq reqBean = new UpdateUserReq(user);
            UpdateUserResp respBean = new UpdateUserResp();
            respBean = updateUserService.update(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("update user failed");
                msg.setMsg(1, "update user failed", null);
                return msg;
            }
            msg.setMsg(0, "update user success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("AuthController.update() catch exception : ", e);
            msg.setStatus(2);
            msg.setMsg("update failed");
            return msg;
        }
    }

    /**
     * 用户审批
     *
     * @param user 用户信息
     * @return ReturnMsg
     */
    @RequestMapping(value = "/userPermit", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg userPermit(UserInfo user) {
        ReturnMsg msg = new ReturnMsg();
        try {
            UserInfo updateUser = new UserInfo();
            updateUser.setStatus(user.getStatus());
            updateUser.setPermitUser((int) SessionUtil.getSessionValue("uid"));
            updateUser.setUid(user.getUid());
            UpdateUserReq reqBean = new UpdateUserReq(updateUser);
            UpdateUserResp respBean = new UpdateUserResp();
            respBean = updateUserService.update(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("update user failed");
                msg.setMsg(1, "update user failed", null);
                return msg;
            }
            msg.setMsg(0, "update user success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("AuthController.userPermit() catch exception : ", e);
            msg.setStatus(2);
            msg.setMsg("userPermit failed");
            return msg;
        }
    }

    /**
     * 获取所有用户
     *
     * @param user         用户信息
     * @param searchFilter 排序分页条件
     * @return ReturnMsg
     */
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getAllUser(UserInfo user, SearchFilter searchFilter) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetAllUserInfoReq reqBean = new GetAllUserInfoReq(user, searchFilter);
            GetAllUserInfoResp respBean = new GetAllUserInfoResp();
            respBean = getAllUsersService.getAllUsers(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get all user failed");
                msg.setMsg(1, "get all user failed", null);
                return msg;
            }
            msg.setMsg(0, "get all user success", respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("AuthController.getAllUser() catch exception : ", e);
            msg.setStatus(2);
            msg.setMsg("getAllUser failed");
            return msg;
        }
    }

    /**
     * @param user 用户信息
     * @return ReturnMsg
     */
    @RequestMapping(value = "/updateSelf", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg updateSelf(UserInfo user) {
        ReturnMsg msg = new ReturnMsg();
        try {
            UpdateSelfReq reqBean = new UpdateSelfReq(user);
            UpdateSelfResp respBean = new UpdateSelfResp();
            respBean = updateSelfService.updateSelf(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("update user info  failed");
                msg.setMsg(1, "update user info failed", null);
                return msg;
            }
            msg.setMsg(0, "update user info success", respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("AuthController.updateSelf() catch exception : ", e);
            msg.setStatus(2);
            msg.setMsg("updateSelf failed");
            return msg;
        }
    }
}
