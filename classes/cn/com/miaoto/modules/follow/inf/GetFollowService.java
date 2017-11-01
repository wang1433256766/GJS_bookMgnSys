package cn.com.miaoto.modules.follow.inf;

import cn.com.miaoto.modules.follow.model.GetFollowReq;
import cn.com.miaoto.modules.follow.model.GetFollowResp;

/**
 * Created by hx on 2017/8/25.
 */
public interface GetFollowService {
    GetFollowResp getFollow(GetFollowReq reqBean, GetFollowResp respBean);
}
