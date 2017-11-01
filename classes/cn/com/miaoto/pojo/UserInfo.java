package cn.com.miaoto.pojo;

public class UserInfo {
    private Integer uid;
    private String uname;
    private String email;
    private Long card;
    private String qrCode;
    private String qrCodeImg;
    private Integer status;
    private Integer permitUser;
    private String number;
    private String createTime;
    private String updateTime;
    private String salt;
    private String pwd;
    private Integer type;
    private String idcard;
    private String phone;
    private String tel;
    private String department;
    private String tutor;

    private String roleStr;

    public UserInfo() {
    }

    public UserInfo(long card) {
        this.card = card;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public Long getCard() {
        return card;
    }

    public void setCard(Long card) {
        this.card = card;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCodeImg() {
        return qrCodeImg;
    }

    public void setQrCodeImg(String qrCodeImg) {
        this.qrCodeImg = qrCodeImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPermitUser() {
        return permitUser;
    }

    public void setPermitUser(Integer permitUser) {
        this.permitUser = permitUser;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", email='" + email + '\'' +
                ", card=" + card +
                ", qrCode='" + qrCode + '\'' +
                ", qrCodeImg='" + qrCodeImg + '\'' +
                ", status=" + status +
                ", permitUser=" + permitUser +
                ", number=" + number +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", salt='" + salt + '\'' +
                ", pwd='" + pwd + '\'' +
                ", type=" + type +
                ", idcard='" + idcard + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", department='" + department + '\'' +
                ", tutor='" + tutor + '\'' +
                ", roleStr='" + roleStr + '\'' +
                '}';
    }
}
