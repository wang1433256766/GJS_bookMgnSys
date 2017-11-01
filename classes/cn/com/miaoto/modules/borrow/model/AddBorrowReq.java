package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Borrow;

public class AddBorrowReq extends RequestInfo {
	private Borrow borrow;
	
	private int bookStatus;

	public int getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}

	public AddBorrowReq() {
		super();
	}
	
	public AddBorrowReq(Borrow borrow) {
		super();
		this.borrow = borrow;
	}

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}

	@Override
	public String toString() {
		return "AddBorrowReq [borrow=" + borrow + "]";
	}

}
