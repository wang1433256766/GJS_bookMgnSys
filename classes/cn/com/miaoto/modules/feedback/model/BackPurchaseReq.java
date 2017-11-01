package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Purchase;

/**
 * Created by hx on 2017/8/3.
 */
public class BackPurchaseReq extends RequestInfo {

    private Purchase purchase;

    public BackPurchaseReq(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
