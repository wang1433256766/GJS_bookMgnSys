package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.pojo.Setting;

import java.util.List;

public interface SettingDao {

    List<Setting> queryAllSetting() throws DBException;

    int addSetting(Setting setting) throws DBException;

    int updateSetting(String key, String value) throws DBException;


}
