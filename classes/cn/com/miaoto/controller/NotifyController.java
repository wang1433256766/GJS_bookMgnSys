package cn.com.miaoto.controller;

import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.notify.inf.GetAllNotifyService;
import cn.com.miaoto.modules.notify.inf.GetNotifyService;
import cn.com.miaoto.modules.notify.inf.ReadNotifyService;
import cn.com.miaoto.modules.notify.model.*;
import cn.com.miaoto.pojo.Notify;
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
 * Created by hx on 2017/8/14.
 */

@Controller
@RequestMapping("/")
public class NotifyController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(NotifyController.class);

    @Resource
    private GetAllNotifyService getAllNotifyService;

    @Resource
    private ReadNotifyService readNotifyService;

    @Resource
    private GetNotifyService getNotifyService;

    @RequestMapping(value = "getAllNotify", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getAllNotify(SearchFilter searchFilter) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetAllNotifyReq reqBean = new GetAllNotifyReq(searchFilter);
            GetAllNotifyResp respBean = new GetAllNotifyResp();
            respBean = getAllNotifyService.getAllNotify(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                msg.setMsg(1, "get all notify failed", null);
                return msg;
            }

            respBean.setPage(searchFilter.getPage());

            msg.setMsg(0, "get all notify success", null);
            msg.setData(respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("NotifyController getAllNotify", e);
            msg.setMsg(1, "get all notify failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "readNotify", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg readNotify(String nids) {
        ReturnMsg msg = new ReturnMsg();
        try {
            ReadNotifyReq reqBean = new ReadNotifyReq();
            ReadNotifyResp respBean = new ReadNotifyResp();
            reqBean.setNids(nids);
            respBean = readNotifyService.readNotify(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                msg.setMsg(1, "read notify failed", null);
                return msg;
            }
            msg.setMsg(0, "read notify success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("NotifyController readNotify", e);
            msg.setMsg(1, "read notify failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getNotify", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getNotify(Notify notify) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetNotifyReq reqBean = new GetNotifyReq(notify);
            GetNotifyResp respBean = new GetNotifyResp();
            respBean = getNotifyService.getNotify(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                msg.setMsg(1, "get notify failed", null);
                return msg;
            }
            if (respBean.getNotify().getTo() != (int) SessionUtil.getSessionValue("uid") && respBean.getNotify().getTo() != 1) {
                msg.setMsg(1, "permission deny", null);
                return msg;
            }
            msg.setMsg(0, "get notify success", respBean.getNotify());
            return msg;
        } catch (Exception e) {
            LOGGER.error("NotifyController getNotify", e);
            msg.setMsg(1, "get notify failed", null);
            return msg;
        }
    }
}
