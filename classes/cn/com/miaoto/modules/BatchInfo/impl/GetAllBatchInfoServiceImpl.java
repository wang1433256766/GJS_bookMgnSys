package cn.com.miaoto.modules.BatchInfo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BatchInfoDao;
import cn.com.miaoto.modules.BatchInfo.inf.GetAllBatchInfoService;
import cn.com.miaoto.modules.BatchInfo.model.GetAllBatchInfoReq;
import cn.com.miaoto.modules.BatchInfo.model.GetAllBatchInfoResp;
import cn.com.miaoto.pojo.BatchInfo;

@Service
public class GetAllBatchInfoServiceImpl extends AbstractService<GetAllBatchInfoReq, GetAllBatchInfoResp> implements GetAllBatchInfoService {

	public static final Logger LOGGER = LoggerFactory.getLogger(GetAllBatchInfoServiceImpl.class);

	@Resource
	public BatchInfoDao batchInfoDao;

	@Override
	public GetAllBatchInfoResp getAllBatchInfo(GetAllBatchInfoReq reqBean, GetAllBatchInfoResp respBean) {
		return (GetAllBatchInfoResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(GetAllBatchInfoReq reqBean) {
		return true;
	}

	@Override
	protected void handle(GetAllBatchInfoReq reqBean, GetAllBatchInfoResp respBean) throws Exception {
		List<BatchInfo> batchInfoList = batchInfoDao.selectAll();
		if (batchInfoList != null) {
			respBean.setBatchInfoList(batchInfoList);
		}
	}
}
