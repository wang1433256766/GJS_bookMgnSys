package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PurchaseDao;
import cn.com.miaoto.modules.feedback.inf.GetAllPurchaseService;
import cn.com.miaoto.modules.feedback.model.GetAllPurchaseReq;
import cn.com.miaoto.modules.feedback.model.GetAllPurchaseResp;
import cn.com.miaoto.pojo.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/7/28.
 */
@Service
public class GetAllPurchaseServiceImpl extends AbstractService<GetAllPurchaseReq, GetAllPurchaseResp> implements GetAllPurchaseService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllPurchaseServiceImpl.class);

    @Resource
    private PurchaseDao purchaseDao;

    @Override
    public GetAllPurchaseResp getAllPurchase(GetAllPurchaseReq reqBean, GetAllPurchaseResp respBean) {
        return (GetAllPurchaseResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetAllPurchaseReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetAllPurchaseReq reqBean, GetAllPurchaseResp respBean) throws Exception {
        List<Purchase> purchaseList = purchaseDao.getAllPurchase(reqBean.getPurchase(), reqBean.getSearchFilter());
//        if (purchaseList == null) {
//            LOGGER.error("get all purchase failed");
//            return;
//        }
//        if (purchaseList.size() == 0) {
//            LOGGER.warn("get all purchase size = 0");
//        }

        //get all rows
        int rows = purchaseDao.count(reqBean.getPurchase());
        
        if (rows == 0) {
            LOGGER.warn("get purchase rows num is 0");
        }
        respBean.setAllRows(rows);

        respBean.setPurchaseList(purchaseList);
        respBean.setResultCode(1);
    }
}
