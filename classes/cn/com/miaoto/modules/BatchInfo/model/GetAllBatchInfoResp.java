package cn.com.miaoto.modules.BatchInfo.model;

import java.util.List;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.BatchInfo;

public class GetAllBatchInfoResp extends ResponseInfo {
	List<BatchInfo> batchInfoList;

	public List<BatchInfo> getBatchInfoList() {
		return batchInfoList;
	}

	public void setBatchInfoList(List<BatchInfo> batchInfoList) {
		this.batchInfoList = batchInfoList;
	}

}
