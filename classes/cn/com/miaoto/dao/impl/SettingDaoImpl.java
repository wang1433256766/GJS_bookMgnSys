package cn.com.miaoto.dao.impl;


import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.SettingDao;
import cn.com.miaoto.pojo.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * settingDao
 */
@Repository
public class SettingDaoImpl extends BaseDao implements SettingDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(SettingDaoImpl.class);

    @Override
    public List<Setting> queryAllSetting() throws DBException {
        List<Setting> settingList;
        try {
            settingList = getSqlSession().selectList("Setting.selectAll", null);
        } catch (Exception e) {
            throw new DBException("SettingDaoImpl queryAllSetting() error...", e);
        }
        return settingList;
    }

    @Override
    public int addSetting(Setting setting) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("Setting.insert", setting);
        } catch (Exception e) {
            throw new DBException("SettingDaoImpl addSetting() error...", e);
        }
        return effected;
    }

    @Override
    public int updateSetting(String key, String value) throws DBException {
        int effected;
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key", key);
            map.put("value", value);
            effected = getSqlSession().update("Setting.update", map);
        } catch (Exception e) {
            throw new DBException("SettingDaoImpl updateSetting(key, value) error...", e);
        }
        return effected;
    }

}
