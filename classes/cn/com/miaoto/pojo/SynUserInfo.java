package cn.com.miaoto.pojo;

/**
 * Created by hx on 2017/9/8.
 */
public class SynUserInfo {

    private String synUserName;

    private Long card;

    private String sex;

    private String idcard;

    private String dptID;

    private String dptName;

    private String cardType;

    private String loginName;

    private String workNum;

    public String getSynUserName() {
        return synUserName;
    }

    public void setSynUserName(String synUserName) {
        this.synUserName = synUserName;
    }

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getDptID() {
        return dptID;
    }

    public void setDptID(String dptID) {
        this.dptID = dptID;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    @Override
    public String toString() {
        return "SynUserInfo{" +
                "synUserName='" + synUserName + '\'' +
                ", card=" + card +
                ", sex='" + sex + '\'' +
                ", idcard='" + idcard + '\'' +
                ", dptID='" + dptID + '\'' +
                ", dptName='" + dptName + '\'' +
                ", cardType='" + cardType + '\'' +
                ", loginName='" + loginName + '\'' +
                ", workNum='" + workNum + '\'' +
                '}';
    }
}
