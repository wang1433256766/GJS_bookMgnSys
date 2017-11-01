package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

import java.util.List;

/**
 * Created by hx on 2017/7/31.
 */
public class AddRolePermissionReq extends RequestInfo {
    private int rid;
    private String pids;
    private List<Integer> pidList;

    public AddRolePermissionReq(int rid, String pids) {
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

    public List<Integer> getPidList() {
        return pidList;
    }

    public void setPidList(List<Integer> pidList) {
        this.pidList = pidList;
    }
}
