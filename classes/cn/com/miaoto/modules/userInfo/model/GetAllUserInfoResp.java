package cn.com.miaoto.modules.userInfo.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.UserInfo;

import java.util.List;

public class GetAllUserInfoResp extends ResponseInfo {
    private List<UserInfo> userInfoList;

    private int page;

    private int allRows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

}
