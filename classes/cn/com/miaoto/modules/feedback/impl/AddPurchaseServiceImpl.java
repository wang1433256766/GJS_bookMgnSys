package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PurchaseDao;
import cn.com.miaoto.modules.feedback.inf.AddPurchaseService;
import cn.com.miaoto.modules.feedback.model.AddPurchaseReq;
import cn.com.miaoto.modules.feedback.model.AddPurchaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/8/24.
 */
@Service
public class AddPurchaseServiceImpl extends AbstractService<AddPurchaseReq, AddPurchaseResp> implements AddPurchaseService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddPurchaseServiceImpl.class);

    @Resource
    PurchaseDao purchaseDao;

    @Override
    public AddPurchaseResp addPurchase(AddPurchaseReq reqBean, AddPurchaseResp respBean) {
        return (AddPurchaseResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddPurchaseReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddPurchaseReq reqBean, AddPurchaseResp respBean) throws Exception {
        int effected = purchaseDao.addPurchase(reqBean.getPurchase());
        if (effected == 0) {
            LOGGER.error("add purchase failed");
            return;
        }
        respBean.setResultCode(1);
    }
}