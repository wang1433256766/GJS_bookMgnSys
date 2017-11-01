package cn.com.miaoto.modules.role.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

import java.util.List;

/**
 * Created by hx on 2017/7/31.
 */
public class AddRoles4UserReq extends RequestInfo {
    private String rids;
    private int uid;
    private List<Integer> ridList;

    public AddRoles4UserReq(String rids, int uid) {
        this.rids = rids;
        this.uid = uid;
    }

    public String getRids() {
        return rids;
    }

    public void setRids(String rids) {
        this.rids = rids;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Integer> getRidList() {
        return ridList;
    }

    public void setRidList(List<Integer> ridList) {
        this.ridList = ridList;
    }
}
