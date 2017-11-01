package cn.com.miaoto.pojo;

/**
 * Created by hx on 2017/7/31.
 */
public class UserRole {
    private Integer urid;
    private Integer urUid;
    private Integer urRid;
    private String createtime;
    private String updatetime;

    public Integer getUrid() {
        return urid;
    }

    public void setUrid(Integer urid) {
        this.urid = urid;
    }

    public Integer getUrUid() {
        return urUid;
    }

    public void setUrUid(Integer urUid) {
        this.urUid = urUid;
    }

    public Integer getUrRid() {
        return urRid;
    }

    public void setUrRid(Integer urRid) {
        this.urRid = urRid;
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

    @Override
    public String toString() {
        return "UserRole{" +
                "urid=" + urid +
                ", urUid=" + urUid +
                ", urRid=" + urRid +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
