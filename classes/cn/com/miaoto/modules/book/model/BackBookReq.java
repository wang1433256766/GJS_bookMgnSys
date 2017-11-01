package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

public class BackBookReq extends RequestInfo {
	private long barCode;

	public long getBarCode() {
		return barCode;
	}

	public void setBarCode(long barCode) {
		this.barCode = barCode;
	}

	public BackBookReq(long barCode) {
		super();
		this.barCode = barCode;
	}

}
