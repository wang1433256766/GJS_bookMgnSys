package cn.com.miaoto.modules.follow.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Follow;

/**
 * Created by hx on 2017/8/18.
 */
public class AddFollowReq extends RequestInfo {

    private Follow follow;

    public AddFollowReq(Follow follow) {
        this.follow = follow;
    }

    public Follow getFollow() {
        return follow;
    }

    public void setFollow(Follow follow) {
        this.follow = follow;
    }
}
