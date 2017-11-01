package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.pojo.UserInfo;
import cn.com.miaoto.pojo.common.BackReturn;

import java.util.List;

public class BackBookResp extends ResponseInfo {

    private int result;

    private UserInfo userInfo;

    private List<Borrow> borrowList;

    private BackReturn backReturn;

    public BackReturn getBackReturn() {
        return backReturn;
    }

    public void setBackReturn(BackReturn backReturn) {
        this.backReturn = backReturn;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Borrow> getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }
}
