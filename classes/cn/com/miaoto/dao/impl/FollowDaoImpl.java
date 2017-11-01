package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.FollowDao;
import cn.com.miaoto.modules.follow.model.GetFollowReq;
import cn.com.miaoto.pojo.Follow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * followDao
 * Created by hx on 2017/8/17.
 */
@Repository
public class FollowDaoImpl extends BaseDao implements FollowDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(FollowDaoImpl.class);

    @Override
    public int insert(Follow follow) throws DBException {
        int id;
        try {
            id = getSqlSession().insert("Follow.insert", follow);
        } catch (Exception e) {
            throw new DBException("FollowDaoImpl insert(follow) error...", e);
        }
        if (id != 1) {
            return 0;
        }
        return follow.getFoID();
    }

    @Override
    public List<Follow> selectReminder(Follow follow) throws DBException {
        List<Follow> followList;
        try {
            followList = getSqlSession().selectList("Follow.searchReminder", follow);
        } catch (Exception e) {
            throw new DBException("FollowDaoImpl selectReminder(follow) error...", e);
        }
        return followList;
    }

    @Override
    public int updateStatus(Follow follow) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("Follow.update", follow);
        } catch (Exception e) {
            throw new DBException("FollowDaoImpl updateStatus(follow) error...", e);
        }
        return effected;
    }

    @Override
    public List<Follow> selectOwn(GetFollowReq reqBean) throws DBException {
        List<Follow> followList;
        try {
            reqBean.getSearchFilter().setBegin((reqBean.getSearchFilter().getPage() - 1) * reqBean.getSearchFilter().getRows());
            reqBean.getSearchFilter().setEnd((reqBean.getSearchFilter().getPage()) * reqBean.getSearchFilter().getRows());
            followList = getSqlSession().selectList("Follow.searchOwn", reqBean);
        } catch (Exception e) {
            throw new DBException("FollowDaoImpl selectOwn(getFollowReq) error...", e);
        }
        return followList;
    }

    @Override
    public List<Follow> selectFollowing(Follow follow) throws DBException {
        List<Follow> followList;
        try {
            followList = getSqlSession().selectList("Follow.searchFollowing", follow);
        } catch (Exception e) {
            throw new DBException("FollowDaoImpl selectFollowing(follow) error...", e);
        }
        return followList;
    }
}
