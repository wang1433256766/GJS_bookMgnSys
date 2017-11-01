package cn.com.miaoto.common;

import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hx on 2017/8/23.
 * 图书统计
 */
@Component
public class BookStats {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookStats.class);

    /**
     * 图书浏览的次数
     */
    private ConcurrentHashMap<Integer, Integer> bookView = new ConcurrentHashMap<>();
    /**
     * 图书借阅的次数
     */
    private ConcurrentHashMap<Integer, Integer> bookBorrow = new ConcurrentHashMap<>();

    /**
     * 热门借阅
     */
    private List<Book> hotBooks = new ArrayList<>();

    /**
     * 新书推荐
     */
    private List<BookEntity> newBooks = new ArrayList<>();

    /**
     * 热门借阅
     */
    private List<Book> hotBorrow = new ArrayList<>();

    public List<Book> getHotBooks() {
        return hotBooks;
    }

    public void setHotBooks(List<Book> hotBooks) {
        this.hotBooks = hotBooks;
    }

    public List<BookEntity> getNewBooks() {
        return newBooks;
    }

    public void setNewBooks(List<BookEntity> newBooks) {
        this.newBooks = newBooks;
    }

    public List<Book> getHotBorrow() {
        return hotBorrow;
    }

    public void setHotBorrow(List<Book> hotBorrow) {
        this.hotBorrow = hotBorrow;
    }

    public void addBookView(Integer bookid) {
        LOGGER.debug("add book view, bid = {}", bookid);
        if (bookView.containsKey(bookid)) {
            Integer tmp = bookView.get(bookid);
            bookView.put(bookid, tmp + 1);
        } else {
            bookView.put(bookid, 1);
        }
    }

    public void clearBookView() {
        bookView.clear();
    }

    public ConcurrentHashMap<Integer, Integer> getBookView() {
        return bookView;
    }

    public void addBookBorrow(int bookid) {
        if (bookBorrow.containsKey(bookid)) {
            Integer tmp = bookBorrow.get(bookid);
            bookBorrow.put(bookid, tmp + 1);
        } else {
            bookBorrow.put(bookid, 1);
        }
    }

    public void clearBookBorrow() {
        bookBorrow.clear();
    }

    public ConcurrentHashMap<Integer, Integer> getBookBorrow() {
        return bookBorrow;
    }
}
