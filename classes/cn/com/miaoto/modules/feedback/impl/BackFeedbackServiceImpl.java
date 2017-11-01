package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.FeedbackDao;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.common.impl.SendMailServiceImpl;
import cn.com.miaoto.modules.feedback.inf.BackFeedbackService;
import cn.com.miaoto.modules.feedback.model.BackFeedbackReq;
import cn.com.miaoto.modules.feedback.model.BackFeedbackResp;
import cn.com.miaoto.pojo.Notify;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.util.SessionUtil;
import cn.com.miaoto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/27.
 */
@Service
public class BackFeedbackServiceImpl extends AbstractService<BackFeedbackReq, BackFeedbackResp> implements BackFeedbackService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddFeedbackServiceImpl.class);

    @Resource
    FeedbackDao feedbackDao;

    @Resource
    NotifyDao notifyDao;

    @Resource
    SendMailServiceImpl sendMailService;

    @Resource
    UserInfoDao userInfoDao;

    @Override
    public BackFeedbackResp backFeedback(BackFeedbackReq reqBean, BackFeedbackResp respBean) {
        return (BackFeedbackResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(BackFeedbackReq reqBean) {
        return true;
    }

    @Override
    protected void handle(BackFeedbackReq reqBean, BackFeedbackResp respBean) throws Exception {
        // set admin uid
        reqBean.getFeedback().setAdminUid((int) SessionUtil.getSessionValue("uid"));
        // update table feedback
        int effected = feedbackDao.update(reqBean.getFeedback());
        if (effected == 0) {
            LOGGER.error("update feedback failed");
            return;
        }

        // insert bable notify
        Notify notity = new Notify();
        notity.setTitle(SystemSetting.getValue("feedbackTitle", String.class));
        notity.setContent(reqBean.getFeedback().getAdminContent());
        // uid = 1 is sys
        notity.setFrom(1);
        notity.setTo(reqBean.getFeedback().getFdUid());
        // default is 0, no mean
        notity.setType(0);
        effected = notifyDao.insert(notity);
        if (effected == 0) {
            LOGGER.error("add Notify record failed");
            return;
        }
        respBean.setResultCode(1);

        // send email
        if (reqBean.getFeedback().getIsEmail() == 1) {
            UserInfo queryUser = new UserInfo();
            queryUser.setUid(reqBean.getFeedback().getFdUid());
            UserInfo user = userInfoDao.queryUserInfo(queryUser);
            if (StringUtil.isEmpty(user.getEmail())) {
                LOGGER.info("user's email is null");
                return;
            }
            sendMailService.SEND_SUBMIT_EMAIL(user.getEmail(), StringUtil.encodeSubject(SystemSetting.getValue("feedbackTitle", String.class)), reqBean.getFeedback().getAdminContent());
        }
    }
}
