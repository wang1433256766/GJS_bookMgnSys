package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.FeedbackDao;
import cn.com.miaoto.modules.feedback.inf.GetFeedbackService;
import cn.com.miaoto.modules.feedback.model.GetFeedbackReq;
import cn.com.miaoto.modules.feedback.model.GetFeedbackResp;
import cn.com.miaoto.pojo.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/8/30.
 */
@Service
public class GetFeedbackServiceImpl extends AbstractService<GetFeedbackReq, GetFeedbackResp> implements GetFeedbackService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetFeedbackServiceImpl.class);

    @Resource
    private FeedbackDao feedbackDao;

    @Override
    public GetFeedbackResp getFeedback(GetFeedbackReq reqBean, GetFeedbackResp respBean) {
        return (GetFeedbackResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetFeedbackReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetFeedbackReq reqBean, GetFeedbackResp respBean) throws Exception {
        Feedback feedback = feedbackDao.selectFeedback(reqBean.getFeedback());
        if (feedback == null) {
            LOGGER.error("get feedback by fid failed");
            return;
        }
        respBean.setFeedback(feedback);
        respBean.setResultCode(1);
    }
}
