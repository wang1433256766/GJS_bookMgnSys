package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Feedback;

/**
 * Created by hx on 2017/8/30.
 */
public class GetFeedbackResp extends ResponseInfo {

    private Feedback feedback;

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
