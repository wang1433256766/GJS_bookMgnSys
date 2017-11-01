package cn.com.miaoto.modules.notify.inf;

import cn.com.miaoto.modules.notify.model.ReadNotifyReq;
import cn.com.miaoto.modules.notify.model.ReadNotifyResp;

/**
 * Created by hx on 2017/8/14.
 */
public interface ReadNotifyService {
    ReadNotifyResp readNotify(ReadNotifyReq reqBean, ReadNotifyResp respBean);
}
