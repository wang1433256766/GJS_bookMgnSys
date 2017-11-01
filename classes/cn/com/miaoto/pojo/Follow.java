package cn.com.miaoto.pojo;

/**
 * Created by hx on 2017/8/17.
 */
public class Follow {

    private int foID;

    private int foUID;

    private String foCreatetime;

    private String foUpdatetime;

    private long foBeid;

    private int foStatus;

    private String email;

    private String uname;

    private String bookName;

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFoID() {
        return foID;
    }

    public void setFoID(int foID) {
        this.foID = foID;
    }

    public int getFoUID() {
        return foUID;
    }

    public void setFoUID(int foUID) {
        this.foUID = foUID;
    }

    public String getFoCreatetime() {
        return foCreatetime;
    }

    public void setFoCreatetime(String foCreatetime) {
        this.foCreatetime = foCreatetime;
    }

    public String getFoUpdatetime() {
        return foUpdatetime;
    }

    public void setFoUpdatetime(String foUpdatetime) {
        this.foUpdatetime = foUpdatetime;
    }

    public long getFoBeid() {
        return foBeid;
    }

    public void setFoBeid(long foBeid) {
        this.foBeid = foBeid;
    }

    public int getFoStatus() {
        return foStatus;
    }

    public void setFoStatus(int foStatus) {
        this.foStatus = foStatus;
    }
}
