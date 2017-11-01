package cn.com.miaoto.pojo;

import java.util.List;

public class BookEntity {
    private Long beid;
    private Integer bid;
    private Long barcode;
    private Integer status;
    private Integer bmUid;
    private Integer smUid;
    private String betype;
    private String bemark;
    private String batchPrice;
    private Integer batchId;
    private String smTime;
    private String bmTime;
    private Integer loster;
    private String updatetime;
    private String ctrlNum;
    private String beSource;


    private Book book;
    private String statusStr;
    private List<Integer> statusList;
    private String beidStr;
    private List<Integer> beidList;
    private Integer queryUID;
    private Integer followStatus;
    private String losterName;

    public Long getBeid() {
        return beid;
    }

    public void setBeid(Long beid) {
        this.beid = beid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBmUid() {
        return bmUid;
    }

    public void setBmUid(Integer bmUid) {
        this.bmUid = bmUid;
    }

    public Integer getSmUid() {
        return smUid;
    }

    public void setSmUid(Integer smUid) {
        this.smUid = smUid;
    }

    public String getBetype() {
        return betype;
    }

    public void setBetype(String betype) {
        this.betype = betype;
    }

    public String getBemark() {
        return bemark;
    }

    public void setBemark(String bemark) {
        this.bemark = bemark;
    }

    public String getBatchPrice() {
        return batchPrice;
    }

    public void setBatchPrice(String batchPrice) {
        this.batchPrice = batchPrice;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getSmTime() {
        return smTime;
    }

    public void setSmTime(String smTime) {
        this.smTime = smTime;
    }

    public String getBmTime() {
        return bmTime;
    }

    public void setBmTime(String bmTime) {
        this.bmTime = bmTime;
    }

    public Integer getLoster() {
        return loster;
    }

    public void setLoster(Integer loster) {
        this.loster = loster;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCtrlNum() {
        return ctrlNum;
    }

    public void setCtrlNum(String ctrlNum) {
        this.ctrlNum = ctrlNum;
    }

    public String getBeSource() {
        return beSource;
    }

    public void setBeSource(String beSource) {
        this.beSource = beSource;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public String getBeidStr() {
        return beidStr;
    }

    public void setBeidStr(String beidStr) {
        this.beidStr = beidStr;
    }

    public List<Integer> getBeidList() {
        return beidList;
    }

    public void setBeidList(List<Integer> beidList) {
        this.beidList = beidList;
    }

    public Integer getQueryUID() {
        return queryUID;
    }

    public void setQueryUID(Integer queryUID) {
        this.queryUID = queryUID;
    }

    public Integer getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(Integer followStatus) {
        this.followStatus = followStatus;
    }

    public String getLosterName() {
        return losterName;
    }

    public void setLosterName(String losterName) {
        this.losterName = losterName;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "beid=" + beid +
                ", bid=" + bid +
                ", barcode=" + barcode +
                ", status=" + status +
                ", bmUid=" + bmUid +
                ", smUid=" + smUid +
                ", betype='" + betype + '\'' +
                ", bemark='" + bemark + '\'' +
                ", batchPrice='" + batchPrice + '\'' +
                ", batchId=" + batchId +
                ", smTime='" + smTime + '\'' +
                ", bmTime='" + bmTime + '\'' +
                ", loster=" + loster +
                ", updatetime='" + updatetime + '\'' +
                ", ctrlNum='" + ctrlNum + '\'' +
                ", beSource='" + beSource + '\'' +
                ", book=" + book +
                ", statusStr='" + statusStr + '\'' +
                ", statusList=" + statusList +
                ", beidStr='" + beidStr + '\'' +
                ", beidList=" + beidList +
                ", queryUID=" + queryUID +
                ", followStatus=" + followStatus +
                ", losterName='" + losterName + '\'' +
                '}';
    }
}