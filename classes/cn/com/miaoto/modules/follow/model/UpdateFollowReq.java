package cn.com.miaoto.modules.follow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

/**
 * Created by hx on 2017/8/25.
 */
public class UpdateFollowReq extends RequestInfo {

    private int foid;

    private int status;

    public UpdateFollowReq(int foid, int status) {
        this.foid = foid;
        this.status = status;
    }

    public int getFoid() {
        return foid;
    }

    public void setFoid(int foid) {
        this.foid = foid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
