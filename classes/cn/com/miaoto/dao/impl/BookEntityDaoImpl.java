package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.model.GetAllBookEntityReq;
import cn.com.miaoto.pojo.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookEntityDaoImpl extends BaseDao implements BookEntityDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookEntityDaoImpl.class);

    @Override
    public List<BookEntity> queryBookEntityByBar(BookEntity bookEntity) throws DBException {
        List<BookEntity> bookEntityList;
        try {
            Map<String, String> map = new HashMap<>();
            if (bookEntity.getBarcode() != null) {
                map.put("barCode", bookEntity.getBarcode() + "");
            }
            if (bookEntity.getBid() != null) {
                map.put("bid", bookEntity.getBid() + "");
            }
            if (bookEntity.getBeid() != null) {
                map.put("beid", bookEntity.getBeid() + "");
            }
            if (bookEntity.getBatchId() != null) {
                map.put("batchId", bookEntity.getBatchId() + "");
            }
            if (bookEntity.getQueryUID() != null) {
                map.put("queryUid", bookEntity.getQueryUID() + "");
            }
            bookEntityList = getSqlSession().selectList("BookEntity.selectOne", map);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl queryBookEntityByBar(barCode) error...", e);
        }
        return bookEntityList;
    }

    @Override
    public List<BookEntity> queryBookEntity(BookEntity bookEntity) throws DBException {
        List<BookEntity> bookEntityList;
        try {
            Map<String, String> map = new HashMap<>();
            if (bookEntity.getBarcode() != null) {
                map.put("barCode", bookEntity.getBarcode() + "");
            }
            if (bookEntity.getBid() != null) {
                map.put("bid", bookEntity.getBid() + "");
            }
            if (bookEntity.getBeid() != null) {
                map.put("beid", bookEntity.getBeid() + "");
            }
            if (bookEntity.getBatchId() != null) {
                map.put("batchId", bookEntity.getBatchId() + "");
            }
            bookEntityList = getSqlSession().selectList("BookEntity.select", map);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl queryBookEntity(barCode) error...", e);
        }
        return bookEntityList;
    }

    @Override
    public int updateBookStatus(long barCode, int status) throws DBException {
        int effected;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("barCode", barCode);
            map.put("status", status);
            effected = getSqlSession().update("BookEntity.updateBookStatus", map);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl updateBookStatus(barCode, status) error...", e);
        }
        return effected;
    }

    @Override
    public int queryBookEntityCount(int bid, Integer status) throws DBException {
        int count;
        try {
            Map<String, Object> map = new HashMap<>();
            if (bid != 0) {
                map.put("bid", bid);
                map.put("status", status);
            }
            count = getSqlSession().selectOne("BookEntity.selectCount", map);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl queryBookEntityCount(bid, status) error...", e);
        }
        return count;
    }

    @Override
    public int insertBookEntity(BookEntity bookEntity) throws DBException {
        int count;
        try {
            count = getSqlSession().insert("BookEntity.insert", bookEntity);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl insertBookEntity(bookEntity) error...", e);
        }
        return count;
    }

    @Override
    public long selectMaxBarcode2(boolean foreign, Long start) throws DBException {
        long barCode;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("foreign", foreign);
            map.put("start", start);
            barCode = getSqlSession().selectOne("BookEntity.queryMaxBarCode", map);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl selectMaxBarcode() error...", e);
        }
        return barCode;
    }

    @Override
    public List<BookEntity> selectUncheck(int batchId, int status) throws DBException {
        List<BookEntity> bookEntityList;
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("batchId", batchId);
            map.put("status", status);
            bookEntityList = getSqlSession().selectList("BookEntity.queryUncheck", map);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl selectUncheck(batchId, status) error...", e);
        }
        return bookEntityList;
    }

    @Override
    public int updateBookEntity(BookEntity bookEntity) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("BookEntity.update", bookEntity);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl updateBookEntity(bookEntity) error...", e);
        }
        return effected;
    }

    @Override
    public List<BookEntity> selectall(GetAllBookEntityReq reqBean) throws DBException {
        List<BookEntity> bookEntityList;
        try {
            bookEntityList = getSqlSession().selectList("BookEntity.selectAll", reqBean);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl selectall(searchFilter) error...", e);
        }
        return bookEntityList;
    }

    @Override
    public List<BookEntity> selectNew() throws DBException {
        List<BookEntity> bookEntityList;
        try {
            bookEntityList = getSqlSession().selectList("BookEntity.selectNew", null);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl selectNew() error...", e);
        }
        return bookEntityList;
    }

    @Override
    public int querySearchCount(GetAllBookEntityReq reqBean) throws DBException {
        int count;
        try {
            count = getSqlSession().selectOne("BookEntity.selectSearchCount", reqBean);
        } catch (Exception e) {
            throw new DBException("BookEntityDaoImpl querySearchCount(gtAllBookEntityReq) error...", e);
        }
        return count;
    }
}
