package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.common.SearchFilter;

public class BookSearchReq extends RequestInfo {

    private SearchFilter searchFilter;

    private Book book;

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookSearchReq(SearchFilter searchFilter, Book book) {
        super();
        this.searchFilter = searchFilter;
        this.book = book;
    }

}
