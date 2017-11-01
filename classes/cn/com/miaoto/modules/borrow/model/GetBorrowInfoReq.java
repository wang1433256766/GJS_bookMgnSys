package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/8/25.
 */
public class GetBorrowInfoReq extends RequestInfo {

    private SearchFilter searchFilter;

    private int uid;

    public GetBorrowInfoReq(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
