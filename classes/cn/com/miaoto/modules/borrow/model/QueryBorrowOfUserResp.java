package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Borrow;

public class QueryBorrowOfUserResp extends ResponseInfo {
	private Borrow borrow;

	public Borrow getBorrow() {
		return borrow;
	}

	public void setBorrow(Borrow borrow) {
		this.borrow = borrow;
	}

}
