package cn.com.miaoto.modules.setting.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.SettingDao;
import cn.com.miaoto.modules.setting.inf.UpdateSettingService;
import cn.com.miaoto.modules.setting.model.UpdateSettingReq;
import cn.com.miaoto.modules.setting.model.UpdateSettingResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by hx on 2017/8/24.
 */
@Service
public class UpdateSettingServiceImpl extends AbstractService<UpdateSettingReq, UpdateSettingResp> implements UpdateSettingService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UpdateSettingServiceImpl.class);

    @Resource
    SettingDao settingDao;

    @Override
    public UpdateSettingResp updateSetting(UpdateSettingReq reqBean, UpdateSettingResp respBean) {
        return (UpdateSettingResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(UpdateSettingReq reqBean) {
        return true;
    }

    @Override
    protected void handle(UpdateSettingReq reqBean, UpdateSettingResp respBean) throws Exception {
        for (Map.Entry<String, String> map : reqBean.getMap().entrySet()) {
            int effected = settingDao.updateSetting(map.getKey(), map.getValue());
            if (effected == 0) {
                LOGGER.error("update setting key = {}, value={}", map.getKey(), map.getValue());
            }
        }
        respBean.setResultCode(1);
    }
}
