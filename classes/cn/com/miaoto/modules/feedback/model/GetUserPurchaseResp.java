package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Purchase;

import java.util.List;

/**
 * Created by hx on 2017/8/25.
 */
public class GetUserPurchaseResp extends ResponseInfo {

    private List<Purchase> purchaseList;

    private int Page;

    private int allRows;

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public int getPage() {
        return Page;
    }

    public void setPage(int page) {
        Page = page;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }
}
