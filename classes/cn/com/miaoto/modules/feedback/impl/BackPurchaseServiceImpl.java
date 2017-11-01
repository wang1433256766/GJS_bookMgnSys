package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PurchaseDao;
import cn.com.miaoto.modules.feedback.inf.BackPurchaseService;
import cn.com.miaoto.modules.feedback.model.BackPurchaseReq;
import cn.com.miaoto.modules.feedback.model.BackPurchaseResp;
import cn.com.miaoto.pojo.Purchase;
import cn.com.miaoto.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/8/3.
 */
@Service
public class BackPurchaseServiceImpl extends AbstractService<BackPurchaseReq, BackPurchaseResp> implements BackPurchaseService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BackPurchaseServiceImpl.class);

    @Resource
    PurchaseDao purchaseDao;

    @Override
    public BackPurchaseResp backPurchase(BackPurchaseReq reqBean, BackPurchaseResp respBean) {
        return (BackPurchaseResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(BackPurchaseReq reqBean) {
        return true;
    }

    @Override
    protected void handle(BackPurchaseReq reqBean, BackPurchaseResp respBean) throws Exception {
        Purchase purchase = reqBean.getPurchase();
        int adminUID = (int)SessionUtil.getSessionValue("uid");
        if(adminUID == 0) {
            LOGGER.error("get adminUID failed");
            return;
        }
        purchase.setAdminID(adminUID);
        int effected = purchaseDao.updatePurchase(purchase);
        if(effected == 0) {
            LOGGER.error("back purchase failed, updated row is null");
            return;
        }
        respBean.setResultCode(1);
    }
}
