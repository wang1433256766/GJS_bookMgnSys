package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Feedback;

/**
 * Created by hx on 2017/8/30.
 */
public class GetFeedbackReq extends RequestInfo {

    private Feedback feedback;

    public GetFeedbackReq(Feedback feedback) {
        this.feedback = feedback;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
