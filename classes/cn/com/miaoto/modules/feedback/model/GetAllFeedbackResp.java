package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Feedback;

import java.util.List;

/**
 * Created by hx on 2017/7/28.
 */
public class GetAllFeedbackResp extends ResponseInfo {

    private List<Feedback> feedbackList;

    private int page;

    private long allrows;

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getAllrows() {
        return allrows;
    }

    public void setAllrows(long allrows) {
        this.allrows = allrows;
    }
}
