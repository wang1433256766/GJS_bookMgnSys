package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.GetUserPurchaseReq;
import cn.com.miaoto.modules.feedback.model.GetUserPurchaseResp;

/**
 * Created by hx on 2017/8/25.
 */
public interface GetUserPurchaseService {

    GetUserPurchaseResp getUserPurchase(GetUserPurchaseReq reqBean, GetUserPurchaseResp respBean);
    
}
