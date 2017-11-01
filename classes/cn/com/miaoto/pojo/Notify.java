package cn.com.miaoto.pojo;

/**
 * Created by hx on 2017/7/31.
 */
public class Notify {
    private Integer nid;
    private String title;
    private String content;
    private String createtime;
    private String updatetime;
    private Integer type;
    private Integer from;
    private Integer to;
    private String nuTime;

    public String getNuTime() {
        return nuTime;
    }

    public void setNuTime(String nuTime) {
        this.nuTime = nuTime;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Notify{" +
                "nid=" + nid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", type=" + type +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
