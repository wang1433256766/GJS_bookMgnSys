package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Borrow;

import java.util.List;

/**
 * Created by hx on 2017/8/25.
 */
public class GetBorrowInfoResp extends ResponseInfo {

    private List<Borrow> borrowList;

    private int allRows;

    private int page;

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
