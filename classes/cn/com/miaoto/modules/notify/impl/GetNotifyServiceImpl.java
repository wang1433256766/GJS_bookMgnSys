package cn.com.miaoto.modules.notify.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.modules.notify.inf.GetNotifyService;
import cn.com.miaoto.modules.notify.model.GetNotifyReq;
import cn.com.miaoto.modules.notify.model.GetNotifyResp;
import cn.com.miaoto.pojo.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/8/30.
 */
@Service
public class GetNotifyServiceImpl extends AbstractService<GetNotifyReq, GetNotifyResp> implements GetNotifyService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetNotifyServiceImpl.class);

    @Resource
    NotifyDao notifyDao;

    @Override
    public GetNotifyResp getNotify(GetNotifyReq reqBean, GetNotifyResp respBean) {
        return (GetNotifyResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetNotifyReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetNotifyReq reqBean, GetNotifyResp respBean) throws Exception {
        Notify notify = notifyDao.select(reqBean.getNotify());
        if (notify == null) {
            LOGGER.error("query notify by id failed");
            return;
        }
        respBean.setResultCode(1);
        respBean.setNotify(notify);
    }
}
