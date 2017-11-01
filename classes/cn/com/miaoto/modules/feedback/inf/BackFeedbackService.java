package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.BackFeedbackReq;
import cn.com.miaoto.modules.feedback.model.BackFeedbackResp;

/**
 * Created by hx on 2017/7/27.
 */
public interface BackFeedbackService {
    BackFeedbackResp backFeedback(BackFeedbackReq reqBean, BackFeedbackResp respBean);
}
