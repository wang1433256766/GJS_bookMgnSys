package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PurchaseDao;
import cn.com.miaoto.modules.feedback.inf.GetUserPurchaseService;
import cn.com.miaoto.modules.feedback.model.GetUserPurchaseReq;
import cn.com.miaoto.modules.feedback.model.GetUserPurchaseResp;
import cn.com.miaoto.pojo.Purchase;
import cn.com.miaoto.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/25.
 */
@Service
public class GetUserPurchaseServiceImpl extends AbstractService<GetUserPurchaseReq, GetUserPurchaseResp> implements GetUserPurchaseService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetUserPurchaseServiceImpl.class);

    @Resource
    PurchaseDao purchaseDao;

    @Override
    public GetUserPurchaseResp getUserPurchase(GetUserPurchaseReq reqBean, GetUserPurchaseResp respBean) {
        return (GetUserPurchaseResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetUserPurchaseReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetUserPurchaseReq reqBean, GetUserPurchaseResp respBean) throws Exception {
        int uid = (int) SessionUtil.getSessionValue("uid");
        if (uid == 0) {
            LOGGER.error("get other's purchase failed");
            return;
        }
        Purchase purchase = new Purchase();
        purchase.setUid(uid);
        reqBean.setPurchase(purchase);
        List<Purchase> purchaseList = purchaseDao.selectOther(reqBean);
        if (purchaseList == null) {
            LOGGER.error("get other's purchase failed");
            return;
        }

        int allRows = purchaseDao.countOther(uid);

        respBean.setAllRows(allRows);
        respBean.setResultCode(1);
        respBean.setPurchaseList(purchaseList);
    }
}