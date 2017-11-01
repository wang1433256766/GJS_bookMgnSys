package cn.com.miaoto.modules.feedback.impl;


import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.FeedbackDao;
import cn.com.miaoto.modules.feedback.inf.AddFeedbackService;
import cn.com.miaoto.modules.feedback.model.AddFeedbackReq;
import cn.com.miaoto.modules.feedback.model.AddFeedbackResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/27.
 */
@Service
public class AddFeedbackServiceImpl extends AbstractService<AddFeedbackReq, AddFeedbackResp> implements AddFeedbackService {
    public static final Logger LOGGER = LoggerFactory.getLogger(AddFeedbackServiceImpl.class);

    @Resource
    FeedbackDao feedbackDao;

    @Override
    public AddFeedbackResp addFeedback(AddFeedbackReq reqBean, AddFeedbackResp respBean) {
        return (AddFeedbackResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddFeedbackReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddFeedbackReq reqBean, AddFeedbackResp respBean) throws Exception {
        int effected = feedbackDao.insert(reqBean.getFeedback());
        if(effected == 0) {
            LOGGER.error("add feedback failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
