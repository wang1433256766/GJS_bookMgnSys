package cn.com.miaoto.modules.setting.inf;

import cn.com.miaoto.modules.setting.model.GetSettingReq;
import cn.com.miaoto.modules.setting.model.GetSettingResp;

/**
 * Created by hx on 2017/8/24.
 */
public interface GetSettingService {
    GetSettingResp getSetting(GetSettingReq reqBean, GetSettingResp respBean);
}
