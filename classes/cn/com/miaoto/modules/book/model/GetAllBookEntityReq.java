package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/7/27.
 */
public class GetAllBookEntityReq extends RequestInfo {
    private SearchFilter searchFilter;

    private Book book;

    private BookEntity bookEntity;

    public GetAllBookEntityReq(SearchFilter searchFilter, Book book, BookEntity bookEntity) {
        this.searchFilter = searchFilter;
        this.book = book;
        this.bookEntity = bookEntity;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }
}
