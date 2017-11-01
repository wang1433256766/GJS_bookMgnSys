package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.book.model.BookSearchReq;
import cn.com.miaoto.modules.book.model.FuzzySearchReq;
import cn.com.miaoto.pojo.Book;

import java.util.List;

public interface BookDao {

    Book queryBookById(int id) throws DBException;

    List<Book> searchBook(BookSearchReq bookSearchReq) throws DBException;

    int insertBook(Book book) throws DBException;

    int selectMaxCategory(String category) throws DBException;

    int updateBook(Book book) throws DBException;

    int searchBookCount(BookSearchReq bookSearchReq) throws DBException;

    List<Book> queryHotBook() throws DBException;

    List<Book> queryHotBorrow() throws DBException;

    int updateBookStats(Book book) throws DBException;

    List<Book> fuzzySearchBook(FuzzySearchReq fuzzySearchReq) throws DBException;

    int fuzzyCount(FuzzySearchReq fuzzySearchReq) throws DBException;
}
