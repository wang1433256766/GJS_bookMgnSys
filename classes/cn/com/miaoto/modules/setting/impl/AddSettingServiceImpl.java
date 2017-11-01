package cn.com.miaoto.modules.setting.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.SettingDao;
import cn.com.miaoto.modules.setting.inf.AddSettingService;
import cn.com.miaoto.modules.setting.model.AddSettingReq;
import cn.com.miaoto.modules.setting.model.AddSettingResp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hx on 2017/7/31.
 */
@Service
public class AddSettingServiceImpl extends AbstractService<AddSettingReq, AddSettingResp> implements AddSettingService {
    @Resource
    SettingDao settingDao;

    @Override
    public AddSettingResp addSetting(AddSettingReq reqBean, AddSettingResp respBean) {
        return (AddSettingResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(AddSettingReq reqBean) {
        return true;
    }

    @Override
    protected void handle(AddSettingReq reqBean, AddSettingResp respBean) throws Exception {
        int effected = settingDao.addSetting(reqBean.getSetting());
        if(effected == 0) {
            LOGGER.error("add setting failed");
            return;
        }
        respBean.setResultCode(1);
    }
}
