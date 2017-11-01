package cn.com.miaoto.modules.setting.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.SettingDao;
import cn.com.miaoto.modules.setting.inf.GetSettingService;
import cn.com.miaoto.modules.setting.model.GetSettingReq;
import cn.com.miaoto.modules.setting.model.GetSettingResp;
import cn.com.miaoto.pojo.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/24.
 */
@Service
public class GetSettingServiceImpl extends AbstractService<GetSettingReq, GetSettingResp> implements GetSettingService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetSettingServiceImpl.class);

    @Resource
    SettingDao settingDao;

    @Override
    public GetSettingResp getSetting(GetSettingReq reqBean, GetSettingResp respBean) {
        return (GetSettingResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetSettingReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetSettingReq reqBean, GetSettingResp respBean) throws Exception {
        List<Setting> settingList = settingDao.queryAllSetting();
        if (settingList == null) {
            LOGGER.error("query setting failed");
            return;
        }
        respBean.setResultCode(1);
        respBean.setSettingList(settingList);
    }
}
