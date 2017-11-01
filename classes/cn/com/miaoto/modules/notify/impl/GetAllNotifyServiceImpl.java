package cn.com.miaoto.modules.notify.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.modules.notify.inf.GetAllNotifyService;
import cn.com.miaoto.modules.notify.model.GetAllNotifyReq;
import cn.com.miaoto.modules.notify.model.GetAllNotifyResp;
import cn.com.miaoto.pojo.Notify;
import cn.com.miaoto.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/14.
 */
@Service
public class GetAllNotifyServiceImpl extends AbstractService<GetAllNotifyReq, GetAllNotifyResp> implements GetAllNotifyService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllNotifyServiceImpl.class);

    @Resource
    NotifyDao notifyDao;

    @Override
    public GetAllNotifyResp getAllNotify(GetAllNotifyReq reqBean, GetAllNotifyResp respBean) {
        return (GetAllNotifyResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetAllNotifyReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetAllNotifyReq reqBean, GetAllNotifyResp respBean) throws Exception {
        int uid = (int) SessionUtil.getSessionValue("uid");
        if (uid == 0) {
            LOGGER.error("get uid failed");
            return;
        }

        Notify queryNotify = new Notify();
        queryNotify.setTo(uid);
        reqBean.setNotify(queryNotify);

        List<Notify> notifyList = notifyDao.selectAll(reqBean);
        if (notifyList == null) {
            LOGGER.error("get user notifys failed");
            return;
        }

        int allrows = notifyDao.selectCount(queryNotify);
        respBean.setAllrows(allrows);

        respBean.setResultCode(1);
        respBean.setNotifyList(notifyList);
    }
}
