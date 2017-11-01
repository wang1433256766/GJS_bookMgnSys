
package cn.com.miaoto.pojo;

public class Borrow {
    private int boid;
    private int uid;
    private long beid;
    private String createtime;
    private int renewal;
    private int borrow;
    private Integer backUid;
    private String backtime;
    private String realback;
    private long card;
    private String bookName;
    private String userName;
    private String email;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCard() {
        return card;
    }

    public void setCard(long card) {
        this.card = card;
    }

    public long getBeid() {
        return beid;
    }

    public void setBeid(long beid) {
        this.beid = beid;
    }

    public String getRealback() {
        return realback;
    }

    public void setRealback(String realback) {
        this.realback = realback;
    }

    public int getBoid() {
        return boid;
    }

    public void setBoid(int boid) {
        this.boid = boid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getRenewal() {
        return renewal;
    }

    public void setRenewal(int renewal) {
        this.renewal = renewal;
    }

    public int getBorrow() {
        return borrow;
    }

    public void setBorrow(int borrow) {
        this.borrow = borrow;
    }

    public Integer getBackUid() {
        return backUid;
    }

    public void setBackUid(Integer backUid) {
        this.backUid = backUid;
    }

    public String getBacktime() {
        return backtime;
    }

    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }

    @Override
    public String toString() {
        return "Borrow [boid=" + boid + ", uid=" + uid + ", beid=" + beid + ", createtime=" + createtime + ", renewal=" + renewal + ", borrow=" + borrow
                + ", backUid=" + backUid + ", backtime=" + backtime + ", realback=" + realback + ", card=" + card + "]";
    }


}
