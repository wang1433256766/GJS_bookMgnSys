package cn.com.miaoto.modules.common.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.common.inf.SendNotiService;
import cn.com.miaoto.modules.common.model.SendNotiReq;
import cn.com.miaoto.modules.common.model.SendNotiResp;
import cn.com.miaoto.pojo.Notify;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/24.
 */
@Service
public class SendNotiServiceImpl extends AbstractService<SendNotiReq, SendNotiResp> implements SendNotiService {

    @Resource
    private NotifyDao notifyDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    SendMailServiceImpl sendMailServiceImpl;

    @Override
    public SendNotiResp sendNotiRecord(SendNotiReq reqBean, SendNotiResp respBean) {
        return (SendNotiResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(SendNotiReq reqBean) {
        return true;
    }

    @Override
    protected void handle(SendNotiReq reqBean, SendNotiResp respBean) throws Exception {
        boolean isSuccess = true;
        // 站内信
        Notify notify = new Notify();
        notify.setTitle(reqBean.getTitle());
        notify.setContent(reqBean.getContext());
        notify.setType(0);
        notify.setTo(1);
        notify.setFrom(1);
        int effected = notifyDao.insert(notify);
        if (effected == 0) {
            LOGGER.error("send web noti failed");
            isSuccess = false;
        }

        // 邮件
        UserInfo queryUser = new UserInfo();
        queryUser.setStatus(0);
        List<UserInfo> userInfoList = userInfoDao.selectAva(queryUser);
        if (userInfoList == null || userInfoList.size() == 0) {
            LOGGER.error("query available user failed");
            isSuccess = false;
            return;
        }
        for (UserInfo user : userInfoList) {
            if (StringUtil.isEmpty(user.getEmail())) {
                LOGGER.warn("user's email is null, uid = {}", user.getUid());
                continue;
            }
            sendMailServiceImpl.SEND_SUBMIT_EMAIL(user.getEmail(), reqBean.getTitle(), reqBean.getContext());
        }
        if (isSuccess) {
            respBean.setResultCode(1);
        } else {
            respBean.setResultCode(0);
        }
    }
}
