package cn.com.miaoto.modules.notify.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Notify;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/8/14.
 */
public class GetAllNotifyReq extends RequestInfo {

    private SearchFilter searchFilter;

    private Notify notify;

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }

    public GetAllNotifyReq(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }
}
