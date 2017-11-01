package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.book.model.GetAllBookEntityReq;
import cn.com.miaoto.pojo.BookEntity;

import java.util.List;

public interface BookEntityDao {

    List<BookEntity> queryBookEntityByBar(BookEntity bookEntity) throws DBException;

    int updateBookStatus(long barCode, int status) throws DBException;

    int queryBookEntityCount(int bid, Integer status) throws DBException;

    int insertBookEntity(BookEntity bookEntity) throws DBException;

    long selectMaxBarcode2(boolean foreign, Long start) throws DBException;

    List<BookEntity> selectUncheck(int batchId, int status) throws DBException;

    int updateBookEntity(BookEntity bookEntity) throws DBException;

    List<BookEntity> selectall(GetAllBookEntityReq reqBean) throws DBException;

    List<BookEntity> queryBookEntity(BookEntity bookEntity) throws DBException;

    List<BookEntity> selectNew() throws DBException;

    int querySearchCount(GetAllBookEntityReq reqBean) throws DBException;

}
