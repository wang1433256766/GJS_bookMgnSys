package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Purchase;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/8/25.
 */
public class GetUserPurchaseReq extends RequestInfo {

    private SearchFilter searchFilter;

    private Purchase purchase;

    public GetUserPurchaseReq(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
