package cn.com.miaoto.pojo;

/**
 * Created by hx on 2017/7/26.
 */
public class RolePermission {
    private int rpid;
    private int rpRid;
    private int rpPid;
    private String rpCreatetime;
    private String rpUpdatetime;

    public int getRpid() {
        return rpid;
    }

    public void setRpid(int rpid) {
        this.rpid = rpid;
    }

    public int getRpRid() {
        return rpRid;
    }

    public void setRpRid(int rpRid) {
        this.rpRid = rpRid;
    }

    public int getRpPid() {
        return rpPid;
    }

    public void setRpPid(int rpPid) {
        this.rpPid = rpPid;
    }

    public String getRpCreatetime() {
        return rpCreatetime;
    }

    public void setRpCreatetime(String rpCreatetime) {
        this.rpCreatetime = rpCreatetime;
    }

    public String getRpUpdatetime() {
        return rpUpdatetime;
    }

    public void setRpUpdatetime(String rpUpdatetime) {
        this.rpUpdatetime = rpUpdatetime;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "rpid=" + rpid +
                ", rpRid=" + rpRid +
                ", rpPid=" + rpPid +
                ", rpCreatetime='" + rpCreatetime + '\'' +
                ", rpUpdatetime='" + rpUpdatetime + '\'' +
                '}';
    }
}
