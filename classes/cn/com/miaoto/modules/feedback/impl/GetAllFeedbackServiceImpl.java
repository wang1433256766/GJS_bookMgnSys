package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.FeedbackDao;
import cn.com.miaoto.modules.feedback.inf.GetAllFeedbackService;
import cn.com.miaoto.modules.feedback.model.GetAllFeedbackReq;
import cn.com.miaoto.modules.feedback.model.GetAllFeedbackResp;
import cn.com.miaoto.pojo.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/7/28.
 */
@Service
public class GetAllFeedbackServiceImpl extends AbstractService<GetAllFeedbackReq, GetAllFeedbackResp> implements GetAllFeedbackService {
    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllFeedbackServiceImpl.class);

    @Resource
    FeedbackDao feedbackDao;

    @Override
    public GetAllFeedbackResp getAllFeedback(GetAllFeedbackReq reqBean, GetAllFeedbackResp respBean) {
        return (GetAllFeedbackResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetAllFeedbackReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetAllFeedbackReq reqBean, GetAllFeedbackResp respBean) throws Exception {
        List<Feedback> feedbackList;
        if (reqBean.getUid() == 0) {
            Feedback feedback = new Feedback();
            feedbackList = feedbackDao.selectAllFeedback(reqBean.getSearchFilter(), feedback);
        } else {
            Feedback feedback = new Feedback();
            feedback.setFdUid(reqBean.getUid());
            feedbackList = feedbackDao.selectAllFeedback(reqBean.getSearchFilter(), feedback);
        }
        if (feedbackList == null) {
            LOGGER.error("get all feedback failed");
            return;
        }

        Feedback feedback = new Feedback();
        if (reqBean.getUid() != 0) {
            feedback.setFdUid(reqBean.getUid());
        }
        long allRows = feedbackDao.selectFeedbackCount(feedback);
        if (allRows == 0) {
            LOGGER.info("feedback count is 0");
        }
        respBean.setAllrows(allRows);

        respBean.setResultCode(1);
        respBean.setFeedbackList(feedbackList);
    }
}
