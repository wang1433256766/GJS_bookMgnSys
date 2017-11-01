package cn.com.miaoto.modules.feedback.impl;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.PurchaseDao;
import cn.com.miaoto.modules.feedback.inf.PurchaseStatsService;
import cn.com.miaoto.modules.feedback.model.PurchaseStatsReq;
import cn.com.miaoto.modules.feedback.model.PurchaseStatsResp;
import cn.com.miaoto.pojo.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hx on 2017/8/24.
 */
@Service
public class PurchaseStatsServiceImpl extends AbstractService<PurchaseStatsReq, PurchaseStatsResp> implements PurchaseStatsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PurchaseStatsServiceImpl.class);

    @Resource
    PurchaseDao purchaseDao;

    @Override
    public PurchaseStatsResp purchaseStats(PurchaseStatsReq reqBean, PurchaseStatsResp respBean) {
        return (PurchaseStatsResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(PurchaseStatsReq reqBean) {
        return true;
    }

    @Override
    protected void handle(PurchaseStatsReq reqBean, PurchaseStatsResp respBean) throws Exception {
        Purchase purchase1 = new Purchase();
        Purchase purchase2 = new Purchase();
        Purchase purchase3 = new Purchase();

        purchase1.setStatus(SiomConstants.PURCHASE_WAIT);
        purchase2.setStatus(SiomConstants.PURCHASE_RESOLVED);
        purchase3.setStatus(SiomConstants.PURCHASE_FINISHED);

        int wait = purchaseDao.count(purchase1);
        int resolve = purchaseDao.count(purchase2);
        int finish = purchaseDao.count(purchase3);
        
        int all = wait + resolve + finish;
        Map<String, Integer> map = new HashMap<>();
        map.put("wait", wait);
        map.put("resolve", resolve);
        map.put("finish", finish);
        map.put("all", all);
        respBean.setMap(map);
        respBean.setResultCode(1);
    }
}