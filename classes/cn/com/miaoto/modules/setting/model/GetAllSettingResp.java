package cn.com.miaoto.modules.setting.model;

import java.util.List;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Setting;

public class GetAllSettingResp extends ResponseInfo {
	private List<Setting> settingList;

	public List<Setting> getSettingList() {
		return settingList;
	}

	public void setSettingList(List<Setting> settingList) {
		this.settingList = settingList;
	}

}
