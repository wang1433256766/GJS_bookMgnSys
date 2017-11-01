package cn.com.miaoto.modules.setting.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Setting;

import java.util.List;

/**
 * Created by hx on 2017/8/24.
 */
public class GetSettingResp extends ResponseInfo {

    private List<Setting> settingList;

    public List<Setting> getSettingList() {
        return settingList;
    }

    public void setSettingList(List<Setting> settingList) {
        this.settingList = settingList;
    }
}
