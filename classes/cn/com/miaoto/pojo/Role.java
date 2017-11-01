package cn.com.miaoto.pojo;

import java.util.List;

public class Role {
    private int rid;
    private String rname;
    private String createtime;
    private String updatetime;
    private String mark;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    private List<UserInfo> userInfoList;

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    private List<Permission> permissionList;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "Role [rid=" + rid + ", rname=" + rname + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
    }

    @Override
    public boolean equals(Object o) {
        Role role = (Role) o;
        if (this.getRid() == role.getRid()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getRid();
    }
}
