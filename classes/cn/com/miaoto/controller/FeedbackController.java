package cn.com.miaoto.controller;

import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.feedback.inf.*;
import cn.com.miaoto.modules.feedback.model.*;
import cn.com.miaoto.pojo.Feedback;
import cn.com.miaoto.pojo.Purchase;
import cn.com.miaoto.pojo.common.SearchFilter;
import cn.com.miaoto.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/27.
 */
@Controller
@RequestMapping("/")
public class FeedbackController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);

    @Resource
    private AddFeedbackService addFeedbackService;

    @Resource
    private GetAllPurchaseService getAllPurchaseService;


    @Resource
    private GetAllFeedbackService getAllFeedbackService;

    @Resource
    private BackFeedbackService backFeedbackService;

    @Resource
    private BackPurchaseService backPurchaseService;

    @Resource
    private AddPurchaseService addPurchaseService;

    @Resource
    private PurchaseStatsService purchaseStatsService;

    @Resource
    private GetUserPurchaseService getUserPurchaseService;

    @Resource
    private GetFeedbackService getFeedbackService;

    @RequestMapping(value = "addFeedback", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addFeedback(Feedback feedback) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Object obj = SessionUtil.getSessionValue("uid");
            if (obj == null) {
                LOGGER.warn("add feedback, uid is null");
            } else {
                feedback.setFdUid((int) obj);
            }
            AddFeedbackReq reqBean = new AddFeedbackReq(feedback);
            AddFeedbackResp respBean = new AddFeedbackResp();
            respBean = addFeedbackService.addFeedback(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                msg.setMsg(1, "add feedback failed", null);
                return msg;
            }
            msg.setMsg(0, "add feedback success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController addFeedback", e);
            msg.setMsg(1, "add feedback failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "addPurchase", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addPurchase(Purchase purchase) {
        ReturnMsg msg = new ReturnMsg();

        // 设置荐购状态0,未审核
        purchase.setStatus(SiomConstants.PURCHASE_WAIT);

        try {
            Object obj = SessionUtil.getSessionValue("uid");
            if (obj == null) {
                LOGGER.warn("add purchase, uid is null");
            } else {
                purchase.setUid((int) obj);
            }

            AddPurchaseReq reqBean = new AddPurchaseReq(purchase);
            AddPurchaseResp respBean = new AddPurchaseResp();
            respBean = addPurchaseService.addPurchase(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                msg.setMsg(1, "add purchase failed", null);
                return msg;
            }
            msg.setMsg(0, "add purchase success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController purchase() catch exception : ", e);
            msg.setMsg(1, "add purchase failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getAllFeedback", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getAllFeedback(SearchFilter searchFilter, @RequestParam(required = false) Integer uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetAllFeedbackReq reqBean = new GetAllFeedbackReq(searchFilter);
            if (uid != null) {
                reqBean.setUid(uid);
            }
            GetAllFeedbackResp respBean = new GetAllFeedbackResp();
            respBean = getAllFeedbackService.getAllFeedback(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get all feedback failed");
                msg.setMsg(1, "get all feedback failed", null);
                return msg;
            }

            // 设置page
            respBean.setPage(searchFilter.getPage());

            msg.setMsg(0, "get all feedback success", respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController getAllFeedback", e);
            msg.setMsg(1, "get all feedback failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getAllPurchase", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getAllPurchase(SearchFilter searchFilter, Purchase purchase) {
        ReturnMsg msg = new ReturnMsg();
        try {
            //添加
            GetAllPurchaseReq reqBean = new GetAllPurchaseReq(purchase, searchFilter);
            GetAllPurchaseResp respBean = new GetAllPurchaseResp();
            respBean = getAllPurchaseService.getAllPurchase(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get all Purchase failed");
                msg.setMsg(1, "get all Purchase failed", null);
                return msg;
            }
            respBean.setPages(searchFilter.getPage());
            msg.setMsg(0, "get all Purchase success", respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController getAllPurchase", e);
            msg.setMsg(1, "get all Purchase failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "backFeedback", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg backFeedback(Feedback feedback) {
        ReturnMsg msg = new ReturnMsg();
        try {
            BackFeedbackReq reqBean = new BackFeedbackReq(feedback);
            BackFeedbackResp respBean = new BackFeedbackResp();
            respBean = backFeedbackService.backFeedback(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get all Purchase failed");
                msg.setMsg(1, "backFeedback failed", null);
                return msg;
            }
            msg.setMsg(0, "backFeedback success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController backFeedback", e);
            msg.setMsg(1, "backFeedback failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "backPurchase", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg backPurchase(Purchase purchase) {
        ReturnMsg msg = new ReturnMsg();
        try {
            BackPurchaseReq reqBean = new BackPurchaseReq(purchase);
            BackPurchaseResp respBean = new BackPurchaseResp();
            respBean = backPurchaseService.backPurchase(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("back Purchase failed");
                msg.setMsg(1, "backPurchase failed", null);
                return msg;
            }
            msg.setMsg(0, "backPurchase success", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController backPurcahse():", e);
            msg.setMsg(1, "backPurchase failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "purchaseStats", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg purchaseStats() {
        ReturnMsg msg = new ReturnMsg();
        try {
            PurchaseStatsReq reqBean = new PurchaseStatsReq();
            PurchaseStatsResp respBean = new PurchaseStatsResp();
            respBean = purchaseStatsService.purchaseStats(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get purchase stats failed");
                msg.setMsg(1, "get purchase stats failed", null);
                return msg;
            }
            msg.setMsg(0, "get purchase stats success", respBean.getMap());
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController purchaseStats():", e);
            msg.setMsg(1, "get purchase stats failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getUserPurchase", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getUserPurchase(SearchFilter searchFilter) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetUserPurchaseReq reqBean = new GetUserPurchaseReq(searchFilter);
            GetUserPurchaseResp respBean = new GetUserPurchaseResp();
            respBean = getUserPurchaseService.getUserPurchase(reqBean, respBean);
            respBean.setPage(searchFilter.getPage());
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get other's purchase failed");
                msg.setMsg(1, "get other's purchase failed", null);
                return msg;
            }
            msg.setMsg(0, "get other's purchase success", respBean);
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController getUserPurchase():", e);
            msg.setMsg(1, "get other's purchase failed", null);
            return msg;
        }
    }

    @RequestMapping(value = "getFeedback", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getFeedback(Feedback feedback) {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetFeedbackReq reqBean = new GetFeedbackReq(feedback);
            GetFeedbackResp respBean = new GetFeedbackResp();
            respBean = getFeedbackService.getFeedback(reqBean, respBean);
            if (respBean.getResultCode() == 0) {
                LOGGER.error("get feedback by fid failed");
                msg.setMsg(1, "get feedback by fid failed", null);
                return msg;
            }
            msg.setMsg(0, "get feedback by fid  success", respBean.getFeedback());
            return msg;
        } catch (Exception e) {
            LOGGER.error("FeedbackController getFeedback():", e);
            msg.setMsg(1, "get feedback by fid  failed", null);
            return msg;
        }
    }
}
