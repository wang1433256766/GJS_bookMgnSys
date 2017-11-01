package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.AddFeedbackReq;
import cn.com.miaoto.modules.feedback.model.AddFeedbackResp;

/**
 * Created by hx on 2017/7/27.
 */
public interface AddFeedbackService {
    AddFeedbackResp addFeedback(AddFeedbackReq reqBean, AddFeedbackResp respBean);
}
