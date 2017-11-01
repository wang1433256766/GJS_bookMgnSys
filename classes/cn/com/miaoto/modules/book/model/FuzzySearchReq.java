package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/8/31.
 */
public class FuzzySearchReq extends RequestInfo {

    private SearchFilter searchFilter;

    private String bookName;

    private String bookNameSearch;

    public FuzzySearchReq(SearchFilter searchFilter, String bookName) {
        this.searchFilter = searchFilter;
        this.bookName = bookName;
    }

    public String getBookNameSearch() {
        return bookNameSearch;
    }

    public void setBookNameSearch(String bookNameSearch) {
        this.bookNameSearch = bookNameSearch;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

}
