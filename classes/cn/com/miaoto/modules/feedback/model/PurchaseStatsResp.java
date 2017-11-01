package cn.com.miaoto.modules.feedback.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;

import java.util.Map;

/**
 * Created by hx on 2017/8/24.
 */
public class PurchaseStatsResp extends ResponseInfo {

    private Map<String, Integer> map;

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
