package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.BookEntity;

import java.util.List;

/**
 * Created by hx on 2017/8/22.
 */
public class GetNewBookResp extends ResponseInfo {

    List<BookEntity> bookEntityList;

    public List<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }
}
