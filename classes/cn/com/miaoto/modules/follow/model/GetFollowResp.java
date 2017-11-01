package cn.com.miaoto.modules.follow.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.Follow;

import java.util.List;

/**
 * Created by hx on 2017/8/25.
 */
public class GetFollowResp extends ResponseInfo {

    private List<Follow> followList;

    public List<Follow> getFollowList() {
        return followList;
    }

    public void setFollowList(List<Follow> followList) {
        this.followList = followList;
    }
}
