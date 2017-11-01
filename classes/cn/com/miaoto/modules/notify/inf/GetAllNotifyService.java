package cn.com.miaoto.modules.notify.inf;

import cn.com.miaoto.modules.notify.model.GetAllNotifyReq;
import cn.com.miaoto.modules.notify.model.GetAllNotifyResp;

/**
 * Created by hx on 2017/8/14.
 */
public interface GetAllNotifyService {

    GetAllNotifyResp getAllNotify(GetAllNotifyReq reqBean, GetAllNotifyResp respBean);
}
