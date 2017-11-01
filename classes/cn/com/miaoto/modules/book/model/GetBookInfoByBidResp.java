package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;

import java.util.List;

/**
 * Created by hx on 2017/7/21.
 */
public class GetBookInfoByBidResp extends ResponseInfo {

    private Book book;

    private List<BookEntity> bookEntityList;

    private int canFollow;

    public int getCanFollow() {
        return canFollow;
    }

    public void setCanFollow(int canFollow) {
        this.canFollow = canFollow;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }
}
