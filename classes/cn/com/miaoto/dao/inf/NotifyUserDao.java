package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.pojo.NotifyUser;

import java.util.List;

/**
 * Created by hx on 2017/7/31.
 */
public interface NotifyUserDao {

    int addReadrecord(List<NotifyUser> notifyUserList) throws DBException;

    int queryReadrecord(NotifyUser notifyUser) throws DBException;
}
