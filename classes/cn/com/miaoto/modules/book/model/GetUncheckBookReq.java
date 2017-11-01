package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

public class GetUncheckBookReq extends RequestInfo {
	private int batchId;

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public GetUncheckBookReq(int batchId) {
		super();
		this.batchId = batchId;
	}

}
