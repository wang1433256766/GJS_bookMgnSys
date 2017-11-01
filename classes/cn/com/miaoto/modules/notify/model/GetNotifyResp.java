package cn.com.miaoto.modules.notify.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Notify;

/**
 * Created by hx on 2017/8/30.
 */
public class GetNotifyResp extends ResponseInfo {

    private Notify notify;

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }
}
