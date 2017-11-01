package cn.com.miaoto.modules.permission.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

/**
 * Created by hx on 2017/8/1.
 */
public class UpdatePermissionOfRoleReq extends RequestInfo {
    int rid;
    String pids;

    public UpdatePermissionOfRoleReq(int rid, String pids) {
        this.rid = rid;
        this.pids = pids;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }
}
