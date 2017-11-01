package cn.com.miaoto.modules.common.inf;

import cn.com.miaoto.modules.common.model.SendNotiReq;
import cn.com.miaoto.modules.common.model.SendNotiResp;

/**
 * Created by hx on 2017/8/24.
 */
public interface SendNotiService {
    SendNotiResp sendNotiRecord(SendNotiReq reqBean, SendNotiResp respBean);
}
