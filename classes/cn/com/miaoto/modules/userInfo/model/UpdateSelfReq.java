package cn.com.miaoto.modules.userInfo.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.UserInfo;

/**
 * Created by hx on 2017/9/5.
 */
public class UpdateSelfReq extends RequestInfo {

    private UserInfo userInfo;

    public UpdateSelfReq(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
