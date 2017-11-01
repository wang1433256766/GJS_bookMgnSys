package cn.com.miaoto.modules.feedback.inf;

import cn.com.miaoto.modules.feedback.model.PurchaseStatsReq;
import cn.com.miaoto.modules.feedback.model.PurchaseStatsResp;

/**
 * Created by hx on 2017/8/24.
 */
public interface PurchaseStatsService {

    PurchaseStatsResp purchaseStats(PurchaseStatsReq reqBean, PurchaseStatsResp respBean);

}
