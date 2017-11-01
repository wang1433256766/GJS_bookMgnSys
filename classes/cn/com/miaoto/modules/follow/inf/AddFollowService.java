package cn.com.miaoto.modules.follow.inf;

import cn.com.miaoto.modules.follow.model.AddFollowReq;
import cn.com.miaoto.modules.follow.model.AddFollowResp;

/**
 * Created by hx on 2017/8/18.
 */
public interface AddFollowService {
    AddFollowResp addFollow(AddFollowReq reqBean, AddFollowResp respBean);
}
