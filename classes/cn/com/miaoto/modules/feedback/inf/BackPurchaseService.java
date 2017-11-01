package cn.com.miaoto.modules.feedback.inf;


import cn.com.miaoto.modules.feedback.model.BackPurchaseReq;
import cn.com.miaoto.modules.feedback.model.BackPurchaseResp;

/**
 * Created by hx on 2017/8/3.
 */
public interface BackPurchaseService {
    BackPurchaseResp backPurchase(BackPurchaseReq reqBean, BackPurchaseResp respBean);
}
