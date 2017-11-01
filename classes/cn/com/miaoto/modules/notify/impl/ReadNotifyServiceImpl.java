package cn.com.miaoto.modules.notify.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.NotifyUserDao;
import cn.com.miaoto.modules.notify.inf.ReadNotifyService;
import cn.com.miaoto.modules.notify.model.ReadNotifyReq;
import cn.com.miaoto.modules.notify.model.ReadNotifyResp;
import cn.com.miaoto.pojo.NotifyUser;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2017/8/14.
 */
@Service
public class ReadNotifyServiceImpl extends AbstractService<ReadNotifyReq, ReadNotifyResp> implements ReadNotifyService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllNotifyServiceImpl.class);

    @Resource
    NotifyUserDao notifyUserDao;

    @Override
    public ReadNotifyResp readNotify(ReadNotifyReq reqBean, ReadNotifyResp respBean) {
        return (ReadNotifyResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(ReadNotifyReq reqBean) {
        return true;
    }

    @Override
    protected void handle(ReadNotifyReq reqBean, ReadNotifyResp respBean) throws Exception {
        int uid = (int) SessionUtil.getSessionValue("uid");
        if (uid == 0) {
            LOGGER.error("get uid failed");
            return;
        }
        if (StringUtil.isEmpty(reqBean.getNids())) {
            LOGGER.error("nids is null");
            return;
        }
        String[] nidArr = reqBean.getNids().split(",");
        String nid = nidArr[0];
        List<NotifyUser> notifyUserList = new ArrayList<>();
        notifyUserList.add(new NotifyUser(uid, Integer.parseInt(nid)));
//        for (String nid : nidArr) {
//            notifyUserList.add(new NotifyUser(uid, Integer.parseInt(nid)));
//        }


        int isInserted = notifyUserDao.queryReadrecord(new NotifyUser(uid, Integer.parseInt(nid)));
        if (isInserted != 0) {
            respBean.setResultCode(1);
            return;
        }
        int effected = notifyUserDao.addReadrecord(notifyUserList);
        if (effected == 0) {
            LOGGER.error("add read record failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
