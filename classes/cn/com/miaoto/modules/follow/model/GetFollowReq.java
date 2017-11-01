package cn.com.miaoto.modules.follow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Follow;
import cn.com.miaoto.pojo.common.SearchFilter;

/**
 * Created by hx on 2017/8/25.
 */
public class GetFollowReq extends RequestInfo {

    private SearchFilter searchFilter;

    private Follow follow;

    public GetFollowReq(SearchFilter searchFilter, Follow follow) {
        this.searchFilter = searchFilter;
        this.follow = follow;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
}
