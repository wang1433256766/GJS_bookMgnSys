package cn.com.miaoto.modules.notify.inf;

import cn.com.miaoto.modules.notify.model.GetNotifyReq;
import cn.com.miaoto.modules.notify.model.GetNotifyResp;

/**
 * Created by hx on 2017/8/30.
 */
public interface GetNotifyService {
    GetNotifyResp getNotify(GetNotifyReq reqBean, GetNotifyResp respBean);
}
