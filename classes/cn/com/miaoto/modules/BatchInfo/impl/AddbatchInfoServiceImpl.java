package cn.com.miaoto.modules.BatchInfo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BatchInfoDao;
import cn.com.miaoto.modules.BatchInfo.inf.AddbatchInfoService;
import cn.com.miaoto.modules.BatchInfo.model.AddbatchInfoReq;
import cn.com.miaoto.modules.BatchInfo.model.AddbatchInfoResp;
import cn.com.miaoto.util.TimeUtil;

@Service
public class AddbatchInfoServiceImpl extends AbstractService<AddbatchInfoReq, AddbatchInfoResp> implements AddbatchInfoService {
	public static final Logger LOGGER = LoggerFactory.getLogger(AddbatchInfoServiceImpl.class);

	@Resource
	public BatchInfoDao batchInfoDao;

	@Override
	public AddbatchInfoResp insertBatchInfo(AddbatchInfoReq reqBean, AddbatchInfoResp respBean) {
		return (AddbatchInfoResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(AddbatchInfoReq reqBean) {
		return true;
	}

	@Override
	protected void handle(AddbatchInfoReq reqBean, AddbatchInfoResp respBean) throws Exception {
		reqBean.getBatchInfo().setCreatetime(TimeUtil.getCurrentTimeStr());
		int effected = batchInfoDao.insert(reqBean.getBatchInfo());
		respBean.setResult(effected);
	}
}
