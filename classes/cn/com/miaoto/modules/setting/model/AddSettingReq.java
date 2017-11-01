package cn.com.miaoto.modules.setting.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Setting;

/**
 * Created by hx on 2017/7/31.
 */
public class AddSettingReq extends RequestInfo {
    private Setting setting;

    public AddSettingReq(Setting setting) {
        this.setting = setting;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
}
