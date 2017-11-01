package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

public class QueryBorrowOfUserReq extends RequestInfo {
	private long barCode;

	public long getBarCode() {
		return barCode;
	}

	public void setBarCode(long barCode) {
		this.barCode = barCode;
	}

	public QueryBorrowOfUserReq(long barCode) {
		super();
		this.barCode = barCode;
	}

}
