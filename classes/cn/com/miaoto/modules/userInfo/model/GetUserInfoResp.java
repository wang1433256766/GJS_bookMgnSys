package cn.com.miaoto.modules.userInfo.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.pojo.UserInfo;

import java.util.List;

public class GetUserInfoResp extends ResponseInfo {

    private UserInfo user;

    private List<Borrow> borrowList;

    public GetUserInfoResp() {
    }

    public GetUserInfoResp(UserInfo user) {
        super();
        this.user = user;
    }

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public void clearSensitive() {
        this.user.setSalt("");
        this.user.setPwd("");
    }
}
