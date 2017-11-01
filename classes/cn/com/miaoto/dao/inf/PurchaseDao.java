package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.feedback.model.GetUserPurchaseReq;
import cn.com.miaoto.pojo.Purchase;
import cn.com.miaoto.pojo.common.SearchFilter;

import java.util.List;

/**
 * Created by hx on 2017/7/28.
 */
public interface PurchaseDao {

    int addPurchase(Purchase purchase) throws DBException;

    List<Purchase> getAllPurchase(Purchase purchase, SearchFilter searchFilter) throws DBException;

    int updatePurchase(Purchase purchase) throws DBException;

    int count(Purchase purchase) throws DBException;

    List<Purchase> selectOther(GetUserPurchaseReq getUserPurchaseReq) throws DBException;

    int countOther(int uid) throws DBException;
}
