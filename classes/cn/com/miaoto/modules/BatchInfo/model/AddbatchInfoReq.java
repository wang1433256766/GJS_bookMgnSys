package cn.com.miaoto.modules.BatchInfo.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.BatchInfo;

public class AddbatchInfoReq extends RequestInfo {
	private BatchInfo batchInfo;

	public BatchInfo getBatchInfo() {
		return batchInfo;
	}

	public void setBatchInfo(BatchInfo batchInfo) {
		this.batchInfo = batchInfo;
	}

	public AddbatchInfoReq(BatchInfo batchInfo) {
		super();
		this.batchInfo = batchInfo;
	}

}
