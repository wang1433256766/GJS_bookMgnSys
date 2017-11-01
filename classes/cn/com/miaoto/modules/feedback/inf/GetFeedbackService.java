package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.GetFeedbackReq;
import cn.com.miaoto.modules.feedback.model.GetFeedbackResp;

/**
 * Created by hx on 2017/8/30.
 */
public interface GetFeedbackService {
    GetFeedbackResp getFeedback(GetFeedbackReq reqBean, GetFeedbackResp respBean);
}
