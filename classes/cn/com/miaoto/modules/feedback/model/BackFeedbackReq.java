package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Feedback;

/**
 * Created by hx on 2017/7/27.
 */
public class BackFeedbackReq extends RequestInfo {

    private Feedback feedback;

    public BackFeedbackReq(Feedback feedback) {
        this.feedback = feedback;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
