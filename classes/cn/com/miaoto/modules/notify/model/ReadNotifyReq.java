package cn.com.miaoto.modules.notify.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

/**
 * Created by hx on 2017/8/14.
 */
public class ReadNotifyReq extends RequestInfo {

    private String nids;

    public String getNids() {
        return nids;
    }

    public void setNids(String nids) {
        this.nids = nids;
    }
}
