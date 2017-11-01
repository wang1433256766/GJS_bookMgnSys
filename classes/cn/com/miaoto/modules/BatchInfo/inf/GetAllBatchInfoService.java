package cn.com.miaoto.modules.BatchInfo.inf;

import cn.com.miaoto.modules.BatchInfo.model.GetAllBatchInfoReq;
import cn.com.miaoto.modules.BatchInfo.model.GetAllBatchInfoResp;

public interface GetAllBatchInfoService {

	GetAllBatchInfoResp getAllBatchInfo(GetAllBatchInfoReq reqBean, GetAllBatchInfoResp respBean);

}
