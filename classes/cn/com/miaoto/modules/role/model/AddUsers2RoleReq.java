package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

import java.util.List;

/**
 * Created by hx on 2017/7/31.
 */
public class AddUsers2RoleReq extends RequestInfo {
    private String uids;
    private int rid;
    private List<Integer> uidList;

    public AddUsers2RoleReq(String uids, int rid) {
        this.uids = uids;
        this.rid = rid;
    }

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public List<Integer> getUidList() {
        return uidList;
    }

    public void setUidList(List<Integer> uidList) {
        this.uidList = uidList;
    }
}
