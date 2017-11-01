package cn.com.miaoto.controller;

import cn.com.miaoto.common.ReturnMsg;
import cn.com.miaoto.common.mvcBean.BaseController;
import cn.com.miaoto.modules.BatchInfo.inf.AddbatchInfoService;
import cn.com.miaoto.modules.BatchInfo.inf.GetAllBatchInfoService;
import cn.com.miaoto.modules.BatchInfo.model.AddbatchInfoReq;
import cn.com.miaoto.modules.BatchInfo.model.AddbatchInfoResp;
import cn.com.miaoto.modules.BatchInfo.model.GetAllBatchInfoReq;
import cn.com.miaoto.modules.BatchInfo.model.GetAllBatchInfoResp;
import cn.com.miaoto.pojo.BatchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 批次相关controller
 */
@Controller
@RequestMapping("/")
public class BatchInfoController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BatchInfoController.class);

    @Resource
    private GetAllBatchInfoService getAllBatchInfoService;

    @Resource
    private AddbatchInfoService addbatchInfoService;

    /**
     * 获取所有批次信息
     *
     * @return ReturnMsg
     */
    @RequestMapping(value = "getAllBatchInfo", method = RequestMethod.GET)
    @ResponseBody
    public ReturnMsg getAllBatchInfo() {
        ReturnMsg msg = new ReturnMsg();
        try {
            GetAllBatchInfoReq reqBean = new GetAllBatchInfoReq();
            GetAllBatchInfoResp respBean = new GetAllBatchInfoResp();
            respBean = getAllBatchInfoService.getAllBatchInfo(reqBean, respBean);
            if (respBean.getBatchInfoList() != null) {
                msg.setMsg(0, "get all batchInfo success", respBean.getBatchInfoList());
                return msg;
            }
            msg.setMsg(1, "get batchInfo failed", null);
            return msg;
        } catch (Exception e) {
            LOGGER.error("getAllBatchInfo() catch exception : ", e);
            msg.setMsg(1, "get batchInfo failed", null);
            return msg;
        }
    }

    /**
     * 添加批次
     *
     * @param batchInfo 批次
     * @return ReturnMsg
     */
    @RequestMapping(value = "addBatchInfo", method = RequestMethod.POST)
    @ResponseBody
    public ReturnMsg addBatchInfo(BatchInfo batchInfo) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AddbatchInfoReq reqBean = new AddbatchInfoReq(batchInfo);
            AddbatchInfoResp respBean = new AddbatchInfoResp();
            respBean = addbatchInfoService.insertBatchInfo(reqBean, respBean);
            if (respBean.getResult() == 0) {
                msg.setMsg(1, "add batchInfo failed", null);
                return msg;
            } else {
                msg.setMsg(0, "add batchInfo success", null);
                return msg;
            }
        } catch (Exception e) {
            LOGGER.error("addBatchInfo() catch exception : ", e);
            msg.setMsg(1, "add batchInfo failed", null);
            return msg;
        }
    }
}
