package cn.com.miaoto.pojo;

/**
 * Created by hx on 2017/7/31.
 */
public class NotifyUser {
    private Integer nuid;
    private Integer nuUID;
    private String nutime;
    private Integer nuNID;

    public NotifyUser(Integer nuUID, Integer nuNID) {
        this.nuUID = nuUID;
        this.nuNID = nuNID;
    }

    public Integer getNuid() {
        return nuid;
    }

    public void setNuid(Integer nuid) {
        this.nuid = nuid;
    }

    public Integer getNuUID() {
        return nuUID;
    }

    public void setNuUID(Integer nuUID) {
        this.nuUID = nuUID;
    }

    public String getNutime() {
        return nutime;
    }

    public void setNutime(String nutime) {
        this.nutime = nutime;
    }

    public Integer getNuNID() {
        return nuNID;
    }

    public void setNuNID(Integer nuNID) {
        this.nuNID = nuNID;
    }

    @Override
    public String toString() {
        return "NotifyUser{" +
                "nuid=" + nuid +
                ", nuUID=" + nuUID +
                ", nutime='" + nutime + '\'' +
                ", nuNID=" + nuNID +
                '}';
    }
}
