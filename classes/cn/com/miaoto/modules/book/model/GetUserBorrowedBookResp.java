package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.Borrow;

import java.util.List;

/**
 * Created by hx on 2017/7/22.
 */
public class GetUserBorrowedBookResp extends ResponseInfo {

    Book backBook;

    List<Borrow> borrows;

    public Book getBackBook() {
        return backBook;
    }

    public void setBackBook(Book backBook) {
        this.backBook = backBook;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}
