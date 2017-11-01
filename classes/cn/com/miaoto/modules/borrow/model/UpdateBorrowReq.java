package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Borrow;

public class UpdateBorrowReq extends RequestInfo {
	private Borrow borrow;

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}

	public UpdateBorrowReq(Borrow borrow) {
		super();
		this.borrow = borrow;
	}

}
