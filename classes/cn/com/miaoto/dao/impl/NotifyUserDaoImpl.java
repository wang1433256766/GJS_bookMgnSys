package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.NotifyUserDao;
import cn.com.miaoto.pojo.NotifyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * notifyUserDao
 * Created by hx on 2017/7/31.
 */
@Repository
public class NotifyUserDaoImpl extends BaseDao implements NotifyUserDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(NotifyUserDaoImpl.class);

    @Override
    public int addReadrecord(List<NotifyUser> notifyUserList) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("NotifyUser.insert", notifyUserList);
        } catch (Exception e) {
            throw new DBException("NotifyUserDaoImpl addReadrecord(notifyUserList) error...", e);
        }
        return effected;
    }

    @Override
    public int queryReadrecord(NotifyUser notifyUser) throws DBException {
        int effected;
        try {
            effected = getSqlSession().selectOne("NotifyUser.select", notifyUser);
        } catch (Exception e) {
            throw new DBException("NotifyUserDaoImpl queryReadrecord(notifyUser) error...", e);
        }
        return effected;
    }
}
