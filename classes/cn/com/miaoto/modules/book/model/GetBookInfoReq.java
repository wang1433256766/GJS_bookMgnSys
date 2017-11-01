package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

public class GetBookInfoReq extends RequestInfo {
	private String barCode;

	public GetBookInfoReq(String barCode) {
		super();
		this.barCode = barCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

}
