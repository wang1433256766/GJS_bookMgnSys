package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.GetAllPurchaseReq;
import cn.com.miaoto.modules.feedback.model.GetAllPurchaseResp;

/**
 * Created by hx on 2017/7/28.
 */
public interface GetAllPurchaseService {
    GetAllPurchaseResp getAllPurchase(GetAllPurchaseReq reqBean, GetAllPurchaseResp respBean);
}
