package cn.com.miaoto.modules.borrow.model;

import java.util.List;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Borrow;

public class QueryBorrowedNumResp extends ResponseInfo {
	private List<Borrow> borrowList;

	public List<Borrow> getBorrowList() {
		return borrowList;
	}

	public void setBorrowList(List<Borrow> borrowList) {
		this.borrowList = borrowList;
	}

}
