package cn.com.miaoto.common;

import cn.com.miaoto.modules.setting.inf.GetAllSettingService;
import cn.com.miaoto.modules.setting.model.GetAllSettingReq;
import cn.com.miaoto.modules.setting.model.GetAllSettingResp;
import cn.com.miaoto.pojo.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统设置
 */
@Component
@DependsOn({"getAllSettingService"})
public class SystemSetting {

    public static final Logger LOGGER = LoggerFactory.getLogger(SystemSetting.class);

    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    private static SystemSetting systemSetting;

    @Resource
    GetAllSettingService getAllSettingService;

    @PostConstruct
    public void init() {
        // 清除map
        map.clear();

        systemSetting = this;
        systemSetting.getAllSettingService = this.getAllSettingService;

        GetAllSettingReq reqBean = new GetAllSettingReq();
        GetAllSettingResp respBean = new GetAllSettingResp();
        respBean = getSettingService().getAllSetting(reqBean, respBean);
        if (respBean == null) {
            LOGGER.error("GetAllSettingResp is null");
        }
        for (Setting setting : respBean.getSettingList()) {
            map.put(setting.getKey(), setting.getValue() + "");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(String key, Class<T> clazz) {
        try {
            switch (clazz.getName()) {
                case "java.lang.Integer":
                    return (T) Integer.valueOf(map.get(key));
                case "java.lang.Long":
                    return (T) Long.valueOf(map.get(key));
                case "java.lang.String":
                    return (T) map.get(key);
                case "java.lang.Float":
                    return (T) Integer.valueOf(map.get(key));
                case "java.lang.Double":
                    return (T) Double.valueOf(map.get(key));
                case "java.lang.Boolean":
                    return (T) Boolean.valueOf(map.get(key));
            }
        } catch (Exception e) {
            LOGGER.error("convert {} to {}", key, clazz.toString(), e);
        }
        return null;
    }

    public GetAllSettingService getSettingService() {
        return systemSetting.getAllSettingService;
    }
}
