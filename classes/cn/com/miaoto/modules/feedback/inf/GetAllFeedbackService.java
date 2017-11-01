package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.GetAllFeedbackReq;
import cn.com.miaoto.modules.feedback.model.GetAllFeedbackResp;

/**
 * Created by hx on 2017/7/28.
 */
public interface GetAllFeedbackService {
    GetAllFeedbackResp getAllFeedback(GetAllFeedbackReq reqBean, GetAllFeedbackResp respBean);
}
