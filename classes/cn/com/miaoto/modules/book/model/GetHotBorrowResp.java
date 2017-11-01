package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Book;

import java.util.List;

/**
 * Created by hx on 2017/8/22.
 */
public class GetHotBorrowResp extends ResponseInfo {

    List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
