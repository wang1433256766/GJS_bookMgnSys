package cn.com.miaoto.modules.notify.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Notify;

/**
 * Created by hx on 2017/8/30.
 */
public class GetNotifyReq extends RequestInfo {

    private Notify notify;

    public GetNotifyReq(Notify notify) {
        this.notify = notify;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }
}
