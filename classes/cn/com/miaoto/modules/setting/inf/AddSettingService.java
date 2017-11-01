package cn.com.miaoto.modules.setting.inf;

import cn.com.miaoto.modules.setting.model.AddSettingReq;
import cn.com.miaoto.modules.setting.model.AddSettingResp;

/**
 * Created by hx on 2017/7/31.
 */
public interface AddSettingService {
    AddSettingResp addSetting(AddSettingReq reqBean, AddSettingResp respBean);
}
