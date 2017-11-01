package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/7/28.
 */
public class GetAllFeedbackReq extends RequestInfo {

    private SearchFilter searchFilter;

    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public GetAllFeedbackReq(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }
}
