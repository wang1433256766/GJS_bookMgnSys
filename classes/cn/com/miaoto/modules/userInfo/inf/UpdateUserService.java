package cn.com.miaoto.modules.userInfo.inf;

import cn.com.miaoto.modules.userInfo.model.UpdateUserReq;
import cn.com.miaoto.modules.userInfo.model.UpdateUserResp;

/**
 * Created by hx on 2017/7/27.
 */
public interface UpdateUserService {

    UpdateUserResp update(UpdateUserReq reqBean, UpdateUserResp respBean);
}
