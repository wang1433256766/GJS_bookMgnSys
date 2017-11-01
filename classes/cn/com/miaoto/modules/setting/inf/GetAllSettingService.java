package cn.com.miaoto.modules.setting.inf;

import cn.com.miaoto.modules.setting.model.GetAllSettingReq;
import cn.com.miaoto.modules.setting.model.GetAllSettingResp;

public interface GetAllSettingService {

	GetAllSettingResp getAllSetting(GetAllSettingReq reqBean, GetAllSettingResp respBean);

}
