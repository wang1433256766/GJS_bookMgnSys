package cn.com.miaoto.modules.common.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

/**
 * Created by hx on 2017/8/24.
 */
public class SendNotiReq extends RequestInfo {

    private String title;

    private String context;

    public SendNotiReq(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
