package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.modules.notify.model.GetAllNotifyReq;
import cn.com.miaoto.pojo.Notify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * notifyDao
 * Created by hx on 2017/7/31.
 */
@Repository
public class NotifyDaoImpl extends BaseDao implements NotifyDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(NotifyDaoImpl.class);

    @Override
    public int insert(Notify notify) throws DBException {
        int id;
        try {
            id = getSqlSession().insert("Notify.insert", notify);
        } catch (Exception e) {
            throw new DBException("NotifyDaoImpl insert(notify) error...", e);
        }
        return id;

    }

    @Override
    public List<Notify> selectAll(GetAllNotifyReq reqBean) throws DBException {
        List<Notify> notifyList;
        try {
            reqBean.getSearchFilter().setBegin((reqBean.getSearchFilter().getPage() - 1) * reqBean.getSearchFilter().getRows());
            reqBean.getSearchFilter().setEnd((reqBean.getSearchFilter().getPage()) * reqBean.getSearchFilter().getRows());
            notifyList = getSqlSession().selectList("Notify.selectAll", reqBean);
        } catch (Exception e) {
            throw new DBException("NotifyDaoImpl selectAll(notify) error...", e);
        }
        return notifyList;
    }

    @Override
    public Notify select(Notify notify) throws DBException {
        try {
            notify = getSqlSession().selectOne("Notify.select", notify);
        } catch (Exception e) {
            throw new DBException("NotifyDaoImpl selectAll(notify) error...", e);
        }
        return notify;
    }

    @Override
    public int selectCount(Notify notify) throws DBException {
        int rows;
        try {
            rows = getSqlSession().selectOne("Notify.selectCount", notify);
        } catch (Exception e) {
            throw new DBException("NotifyDaoImpl selectCount(notify) error...", e);
        }
        return rows;
    }
}
