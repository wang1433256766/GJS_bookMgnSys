package cn.com.miaoto.modules.follow.inf;

import cn.com.miaoto.modules.follow.model.UpdateFollowReq;
import cn.com.miaoto.modules.follow.model.UpdateFollowResp;

/**
 * Created by hx on 2017/8/25.
 */
public interface UpdateFollowService {
    UpdateFollowResp updateFollow(UpdateFollowReq reqBean, UpdateFollowResp respBean);
}
