package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Purchase;

import java.util.List;

/**
 * Created by hx on 2017/7/28.
 */
public class GetAllPurchaseResp extends ResponseInfo {

    private List<Purchase> purchaseList;

    private int pages;

    private int allRows;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }
}
