package cn.com.miaoto.pojo;

/**
 * Created by hx on 2017/7/27.
 */
public class Feedback {
    private Integer fid;
    private String fdCreatetime;
    private String fdUpdatetime;
    private Integer fdUid;
    private Integer adminUid;
    private String content;
    private String adminContent;
    private Integer isEmail;
    private String uname;

    public String getAdminContent() {
        return adminContent;
    }

    public void setAdminContent(String adminContent) {
        this.adminContent = adminContent;
    }

    public Integer getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(Integer isEmail) {
        this.isEmail = isEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFdCreatetime() {
        return fdCreatetime;
    }

    public void setFdCreatetime(String fdCreatetime) {
        this.fdCreatetime = fdCreatetime;
    }

    public String getFdUpdatetime() {
        return fdUpdatetime;
    }

    public void setFdUpdatetime(String fdUpdatetime) {
        this.fdUpdatetime = fdUpdatetime;
    }

    public Integer getFdUid() {
        return fdUid;
    }

    public void setFdUid(Integer fdUid) {
        this.fdUid = fdUid;
    }

    public Integer getAdminUid() {
        return adminUid;
    }

    public void setAdminUid(Integer adminUid) {
        this.adminUid = adminUid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "fid=" + fid +
                ", fdCreatetime='" + fdCreatetime + '\'' +
                ", fdUpdatetime='" + fdUpdatetime + '\'' +
                ", fdUid=" + fdUid +
                ", adminUid=" + adminUid +
                ", content='" + content + '\'' +
                ", adminContent='" + adminContent + '\'' +
                ", isEmail=" + isEmail +
                ", uname='" + uname + '\'' +
                '}';
    }
}
