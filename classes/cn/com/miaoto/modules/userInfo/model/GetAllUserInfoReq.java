package cn.com.miaoto.modules.userInfo.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.common.SearchFilter;
import cn.com.miaoto.pojo.UserInfo;

public class GetAllUserInfoReq extends RequestInfo {
    private UserInfo userInfo;

    private SearchFilter searchFilter;

    public GetAllUserInfoReq(UserInfo userInfo, SearchFilter searchFilter) {
        this.userInfo = userInfo;
        this.searchFilter = searchFilter;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }
}
