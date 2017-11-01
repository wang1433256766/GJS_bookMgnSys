package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.borrow.model.GetBorrowInfoReq;
import cn.com.miaoto.pojo.Borrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * borrowDao
 */
@Repository
public class BorrowDaoImpl extends BaseDao implements BorrowDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(BorrowDaoImpl.class);

    @Override
    public int insert(Borrow borrow) throws DBException {
        int id;
        try {
            id = getSqlSession().insert("Borrow.insert", borrow);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl insert(borrow) error...", e);
        }
        if (id != 1) {
            return 0;
        }
        return borrow.getBoid();

    }

    @Override
    public List<Borrow> selectBorrowedByUser(int uid) throws DBException {
        List<Borrow> borrowList;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", uid);
            borrowList = getSqlSession().selectList("Borrow.selectBorrowedByUser", map);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl selectBorrowedByUser(uid) error...", e);
        }
        return borrowList;
    }

    @Override
    public int backBook(long barCode, String realback, int backUid) throws DBException {
        int effected;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("beid", barCode);
            map.put("realback", realback);
            map.put("backUid", backUid);
            effected = getSqlSession().update("Borrow.backBook", map);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl backBook(barCode) error...", e);
        }
        return effected;
    }

    @Override
    public Borrow selectBorrowedByBook(long barCode) throws DBException {
        Borrow borrow;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("barCode", barCode);
            borrow = getSqlSession().selectOne("Borrow.selectBorrowedByBook", map);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl selectBorrowedByBook(barCode) error...", e);
        }
        return borrow;
    }

    @Override
    public int updateBorrow(Borrow borrow) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("Borrow.update", borrow);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl updateBorrow(borrow) error...", e);
        }
        return effected;
    }

    @Override
    public List<Borrow> selectReminder() throws DBException {
        List<Borrow> borrowList;
        try {
            borrowList = getSqlSession().selectList("Borrow.selectReminder", null);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl selectReminder() error...", e);
        }
        return borrowList;
    }

    @Override
    public List<Borrow> selectReserveFail(int day) throws DBException {
        List<Borrow> borrowList;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("day", day);
            borrowList = getSqlSession().selectList("Borrow.selectReserveFail", map);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl selectReserveFail() error...", e);
        }
        return borrowList;
    }

    @Override
    public List<Borrow> selectAll(GetBorrowInfoReq reqBean) throws DBException {
        List<Borrow> borrowList;
        try {
            reqBean.getSearchFilter().setBegin((reqBean.getSearchFilter().getPage() - 1) * reqBean.getSearchFilter().getRows());
            reqBean.getSearchFilter().setEnd((reqBean.getSearchFilter().getPage()) * reqBean.getSearchFilter().getRows());
            borrowList = getSqlSession().selectList("Borrow.selectAll", reqBean);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl selectAll(GetBorrowInfoReq) error...", e);
        }
        return borrowList;
    }

    @Override
    public int count(int uid) throws DBException {
        int rows;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("uid", uid);
            rows = getSqlSession().selectOne("Borrow.count", map);
        } catch (Exception e) {
            throw new DBException("BorrowDaoImpl count(uid) error...", e);
        }
        return rows;
    }
}
