package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.BookEntity;

public class AddBookEntityReq extends RequestInfo {

    private BookEntity bookEntity;

    private boolean isForeign;

    public boolean isForeign() {
        return isForeign;
    }

    public void setForeign(boolean foreign) {
        isForeign = foreign;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public AddBookEntityReq(BookEntity bookEntity) {
        super();
        this.bookEntity = bookEntity;
    }

}
