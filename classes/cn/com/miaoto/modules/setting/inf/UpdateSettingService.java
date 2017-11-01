package cn.com.miaoto.modules.setting.inf;

import cn.com.miaoto.modules.setting.model.UpdateSettingReq;
import cn.com.miaoto.modules.setting.model.UpdateSettingResp;

/**
 * Created by hx on 2017/8/24.
 */
public interface UpdateSettingService {
    UpdateSettingResp updateSetting(UpdateSettingReq reqBean, UpdateSettingResp respBean);
}
