package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.modules.book.model.BookSearchReq;
import cn.com.miaoto.modules.book.model.FuzzySearchReq;
import cn.com.miaoto.pojo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bookDao
 */
@Repository
public class BookDaoImpl extends BaseDao implements BookDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookDaoImpl.class);

    @Override
    public Book queryBookById(int bid) throws DBException {
        Book book;
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("bid", bid);
            book = getSqlSession().selectOne("Book.selectOne", map);
        } catch (Exception e) {
            throw new DBException("BookDaoImpl queryBookByBar(id) error...", e);
        }
        return book;
    }

    @Override
    public List<Book> searchBook(BookSearchReq bookSearchReq) throws DBException {
        List<Book> bookList;
        try {
            bookList = getSqlSession().selectList("Book.search", bookSearchReq);
        } catch (Exception e) {
            throw new DBException("BookDaoImpl searchBook(bookSearchReq) error...", e);
        }
        return bookList;
    }

    @Override
    public int searchBookCount(BookSearchReq bookSearchReq) throws DBException {
        int bookListCount;
        try {
            bookListCount = getSqlSession().selectOne("Book.searchCount", bookSearchReq);
        } catch (Exception e) {
            throw new DBException("BookDaoImpl searchBookCount(bookSearchReq) error...", e);
        }
        return bookListCount;
    }

    @Override
    public int insertBook(Book book) throws DBException {
        int bid;
        try {
            bid = getSqlSession().insert("Book.insert", book);
        } catch (Exception e) {
            throw new DBException("BookDaoImpl insertBook(book) error...", e);
        }
        return bid;
    }

    @Override
    public int selectMaxCategory(String category) throws DBException {
        Integer typeNum;
        try {
            Map<String, String> map = new HashMap<>();
            map.put("category", category);
            typeNum = getSqlSession().selectOne("Book.queryTypeNum", map);
            if (typeNum == null) {
                typeNum = 0;
            }
        } catch (Exception e) {
            throw new DBException("BookDaoImpl selectMaxCategory(category) error...", e);
        }
        return typeNum;
    }

    @Override
    public int updateBook(Book book) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("Book.update", book);
            return effected;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("BookDaoImpl updateBook(book) error...", e);
        }
    }

    @Override
    public List<Book> queryHotBook() throws DBException {
        List<Book> bookList;
        try {
            bookList = getSqlSession().selectList("Book.selectHot", null);
            return bookList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("BookDaoImpl queryHotBook() error...", e);
        }
    }

    @Override
    public List<Book> queryHotBorrow() throws DBException {
        List<Book> bookList;
        try {
            bookList = getSqlSession().selectList("Book.selectHotBorrow", null);
            return bookList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("BookDaoImpl queryHotBorrow() error...", e);
        }
    }

    @Override
    public int updateBookStats(Book book) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("Book.updateStats", book);
            return effected;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException("BookDaoImpl updateStats(book) error...", e);
        }
    }

    @Override
    public List<Book> fuzzySearchBook(FuzzySearchReq fuzzySearchReq) throws DBException {
        List<Book> bookList;
        try {
            bookList = getSqlSession().selectList("Book.fuzzySearch", fuzzySearchReq);
        } catch (Exception e) {
            throw new DBException("BookDaoImpl fuzzySearchBook(fuzzySearchReq) error...", e);
        }
        return bookList;
    }

    @Override
    public int fuzzyCount(FuzzySearchReq fuzzySearchReq) throws DBException {
        int count;
        try {
            count = getSqlSession().selectOne("Book.fuzzyCount", fuzzySearchReq);
        } catch (Exception e) {
            throw new DBException("BookDaoImpl fuzzyCount(fuzzySearchReq) error...", e);
        }
        return count;
    }
}
