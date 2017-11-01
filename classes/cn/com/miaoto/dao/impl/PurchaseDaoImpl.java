package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.PurchaseDao;
import cn.com.miaoto.modules.feedback.model.GetUserPurchaseReq;
import cn.com.miaoto.pojo.Purchase;
import cn.com.miaoto.pojo.common.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * purchaseDao
 * Created by hx on 2017/7/28.
 */
@Repository
public class PurchaseDaoImpl extends BaseDao implements PurchaseDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(PurchaseDaoImpl.class);

    @Override
    public int addPurchase(Purchase purchase) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("Purchase.insert", purchase);
        } catch (Exception e) {
            throw new DBException("PurchaseDaoImpl addPurchase() error...", e);
        }
        return effected;
    }


    @Override
    public List<Purchase> getAllPurchase(Purchase purchase, SearchFilter searchFilter) throws DBException {
        if (purchase == null) {
            purchase = new Purchase();
        }
        List<Purchase> purchaseList;
        try {
            searchFilter.setBegin((searchFilter.getPage() - 1) * searchFilter.getRows());
            searchFilter.setEnd((searchFilter.getPage()) * searchFilter.getRows());
            Map<String, Object> map = new HashMap<>();
            map.put("purchase", purchase);
            map.put("searchFilter", searchFilter);
            purchaseList = getSqlSession().selectList("Purchase.selectAll", map);
        } catch (Exception e) {
            throw new DBException("PurchaseDaoImpl getAllPurchase() error...", e);
        }
        return purchaseList;
    }

    @Override
    public int updatePurchase(Purchase purchase) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("Purchase.update", purchase);
        } catch (Exception e) {
            throw new DBException("PurchaseDaoImpl updatePurchase() error...", e);
        }
        return effected;
    }

    @Override
    public int count(Purchase purchase) throws DBException {
        int effected;
        try {
            Map<String, Object> map = new HashMap<>();
            if (purchase.getStatus() != null) {
                map.put("status", purchase.getStatus());
            }
            if (purchase.getUid() != null) {
                map.put("uid", purchase.getUid());
            }
            effected = getSqlSession().selectOne("Purchase.count", map);
        } catch (Exception e) {
            throw new DBException("PurchaseDaoImpl count(status) error...", e);
        }
        return effected;
    }

    @Override
    public List<Purchase> selectOther(GetUserPurchaseReq getUserPurchaseReq) throws DBException {
        List<Purchase> purchaseList;
        try {
            getUserPurchaseReq.getSearchFilter().setBegin((getUserPurchaseReq.getSearchFilter().getPage() - 1) * getUserPurchaseReq.getSearchFilter().getRows());
            getUserPurchaseReq.getSearchFilter().setEnd((getUserPurchaseReq.getSearchFilter().getPage()) * getUserPurchaseReq.getSearchFilter().getRows());
            purchaseList = getSqlSession().selectList("Purchase.selectOther", getUserPurchaseReq);
        } catch (Exception e) {
            throw new DBException("PurchaseDaoImpl selectOther() error...", e);
        }
        return purchaseList;
    }

    @Override
    public int countOther(int uid) throws DBException {
        int effected;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", uid);
            effected = getSqlSession().selectOne("Purchase.countOther", map);
        } catch (Exception e) {
            throw new DBException("PurchaseDaoImpl countOther(uid) error...", e);
        }
        return effected;
    }
}
