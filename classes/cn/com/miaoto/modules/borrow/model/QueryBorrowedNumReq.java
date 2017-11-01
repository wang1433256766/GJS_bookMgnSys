package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

public class QueryBorrowedNumReq extends RequestInfo {
	private int uid;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public QueryBorrowedNumReq(int uid) {
		super();
		this.uid = uid;
	}

}
