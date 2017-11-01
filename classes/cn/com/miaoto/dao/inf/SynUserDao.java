package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.pojo.SynUserInfo;

/**
 * Created by hx on 2017/9/11.
 */
public interface SynUserDao {
    
    SynUserInfo queryOneUser(int row) throws DBException;

    Integer count() throws DBException;
}
