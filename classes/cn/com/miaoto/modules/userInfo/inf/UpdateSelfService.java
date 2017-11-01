package cn.com.miaoto.modules.userInfo.inf;

import cn.com.miaoto.modules.userInfo.model.UpdateSelfReq;
import cn.com.miaoto.modules.userInfo.model.UpdateSelfResp;

/**
 * Created by hx on 2017/9/5.
 */
public interface UpdateSelfService {

    UpdateSelfResp updateSelf(UpdateSelfReq reqBean, UpdateSelfResp respBean);

}
