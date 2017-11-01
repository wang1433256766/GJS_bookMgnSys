package cn.com.miaoto.modules.BatchInfo.inf;

import cn.com.miaoto.modules.BatchInfo.model.AddbatchInfoReq;
import cn.com.miaoto.modules.BatchInfo.model.AddbatchInfoResp;

public interface AddbatchInfoService {

	AddbatchInfoResp insertBatchInfo(AddbatchInfoReq reqBean, AddbatchInfoResp respBean);

}
