package cn.com.miaoto.modules.setting.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

import java.util.Map;

/**
 * Created by hx on 2017/8/24.
 */
public class UpdateSettingReq extends RequestInfo {

    Map<String, String> map;

    public UpdateSettingReq(Map<String, String> map) {
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
