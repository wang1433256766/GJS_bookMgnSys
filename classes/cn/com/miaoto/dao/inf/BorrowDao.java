package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.borrow.model.GetBorrowInfoReq;
import cn.com.miaoto.pojo.Borrow;

import java.util.List;

public interface BorrowDao {

    int insert(Borrow borrow) throws DBException;

    List<Borrow> selectBorrowedByUser(int uid) throws DBException;

    int backBook(long barCode, String realback, int backUid) throws DBException;

    Borrow selectBorrowedByBook(long barCode) throws DBException;

    int updateBorrow(Borrow borrow) throws DBException;

    List<Borrow> selectReminder() throws DBException;

    List<Borrow> selectReserveFail(int day) throws DBException;

    List<Borrow> selectAll(GetBorrowInfoReq req) throws DBException;

    int count(int uid) throws DBException;
}
