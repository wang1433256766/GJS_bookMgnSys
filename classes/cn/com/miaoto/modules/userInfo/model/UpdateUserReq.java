package cn.com.miaoto.modules.userInfo.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.UserInfo;

/**
 * Created by hx on 2017/7/27.
 */
public class UpdateUserReq extends RequestInfo {

    private UserInfo user;

    public UpdateUserReq(UserInfo user) {
        this.user = user;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
