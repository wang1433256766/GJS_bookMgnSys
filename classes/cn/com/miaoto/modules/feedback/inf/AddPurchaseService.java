package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.AddPurchaseReq;
import cn.com.miaoto.modules.feedback.model.AddPurchaseResp;

/**
 * Created by hx on 2017/8/24.
 */
public interface AddPurchaseService {
    AddPurchaseResp addPurchase(AddPurchaseReq reqBean, AddPurchaseResp respBean);
}
