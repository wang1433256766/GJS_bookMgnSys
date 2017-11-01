package cn.com.miaoto.controller;

import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.follow.inf.AddFollowService;
import cn.com.miaoto.modules.follow.inf.GetFollowService;
import cn.com.miaoto.modules.follow.inf.UpdateFollowService;
import cn.com.miaoto.modules.follow.model.*;
import cn.com.miaoto.pojo.Follow;
import cn.com.miaoto.pojo.common.SearchFilter;
import cn.com.miaoto.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/8/18.
 */
@Controller
@RequestMapping("/")
public class FollowController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(FollowController.class);

    @Resource
    private AddFollowService addFollowService;

    @Resource
    private GetFollowService getFoolowService;

    @Resource
    private UpdateFollowService updateFollowService;

    @RequestMapping(value = "addFollow", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addFollow(Follow follow) {
        ReturnMsg msg = new ReturnMsg();
        try {
            //获取用户id
            int uid = (int) SessionUtil.getSessionValue("uid");
            follow.setFoUID(uid);
            //设置status
            follow.setFoStatus(SiomConstants.FOLLOW_STATUS_FOLLOWED);

            AddFollowReq reqBean = new AddFollowReq(follow);
            AddFollowResp respBean = new AddFollowResp();
            respBean = addFollowService.addFollow(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("add follow failed");
                msg.setMsg(1, "add follow failed", null);
                return msg;
            }
            msg.setMsg(0, "add follow success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FollowController addFollow", e);
            msg.setMsg(1, "add follow failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getFollow", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg getFollow(SearchFilter searchFilter) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int uid = (int) SessionUtil.getSessionValue("uid");
            if (uid == 0) {
                LOGGER.error("get uid failed");
                msg.setMsg(1, "get uid failed", null);
                return msg;
            }
            Follow queryFollow = new Follow();
            queryFollow.setFoUID(uid);

            GetFollowReq reqBean = new GetFollowReq(searchFilter, queryFollow);
            GetFollowResp respBean = new GetFollowResp();
            respBean = getFoolowService.getFollow(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get user's follow failed");
                msg.setMsg(1, "get user's follow failed", null);
                return msg;
            }
            msg.setMsg(0, "get user's follow success", respBean.getFollowList());
            return msg;
        } catch (Exception e) {
            LOGGER.error("FollowController getFollow", e);
            msg.setMsg(1, "get follow failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "updateFollow", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg updateFollow(int foid, int status) {
        ReturnMsg msg = new ReturnMsg();
        try {
            UpdateFollowReq reqBean = new UpdateFollowReq(foid, status);
            UpdateFollowResp respBean = new UpdateFollowResp();
            respBean = updateFollowService.updateFollow(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("update follow status failed");
                msg.setMsg(1, "update follow status failed", null);
                return msg;
            }
            msg.setMsg(0, "update follow status success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("update follow status failed", e);
            msg.setMsg(1, "update follow status failed", null);
            return msg;
        }
    }
}
