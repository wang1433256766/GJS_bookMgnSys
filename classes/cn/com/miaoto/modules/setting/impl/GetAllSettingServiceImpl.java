package cn.com.miaoto.modules.setting.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.SettingDao;
import cn.com.miaoto.modules.setting.inf.GetAllSettingService;
import cn.com.miaoto.modules.setting.model.GetAllSettingReq;
import cn.com.miaoto.modules.setting.model.GetAllSettingResp;
import cn.com.miaoto.pojo.Setting;

@Service("getAllSettingService")
public class GetAllSettingServiceImpl extends AbstractService<GetAllSettingReq, GetAllSettingResp> implements GetAllSettingService {
	@Resource
	SettingDao settingDao;

	@Override
	public GetAllSettingResp getAllSetting(GetAllSettingReq reqBean, GetAllSettingResp respBean) {
		return (GetAllSettingResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(GetAllSettingReq reqBean) {
		return true;
	}

	@Override
	protected void handle(GetAllSettingReq reqBean, GetAllSettingResp respBean) throws Exception {
		List<Setting> settingList = settingDao.queryAllSetting();
		if (settingList != null) {
			respBean.setSettingList(settingList);
		}
	}
}
