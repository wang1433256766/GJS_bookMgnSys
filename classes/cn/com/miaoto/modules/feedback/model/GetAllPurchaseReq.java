package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Purchase;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/7/28.
 */
public class GetAllPurchaseReq extends RequestInfo {

    private Purchase purchase;

    private SearchFilter searchFilter;

    public GetAllPurchaseReq(Purchase purchase, SearchFilter searchFilter) {
        this.purchase = purchase;
        this.searchFilter = searchFilter;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }
}
