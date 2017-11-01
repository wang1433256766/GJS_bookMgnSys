package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.FeedbackDao;
import cn.com.miaoto.pojo.Feedback;
import cn.com.miaoto.pojo.common.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * feedbackDao
 * 
 * Created by hx on 2017/7/27.
 */
@Repository
public class FeedbackDaoImpl extends BaseDao implements FeedbackDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(FeedbackDaoImpl.class);

    @Override
    public int insert(Feedback feedback) throws DBException {
        int id;
        try {
            id = getSqlSession().insert("Feedback.insert", feedback);
        } catch (Exception e) {
            throw new DBException("FeedbackDaoImpl insert(feedback) error...", e);
        }
        if (id != 1) {
            return 0;
        }
        return feedback.getFid();

    }

    @Override
    public int update(Feedback feedback) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("Feedback.update", feedback);
        } catch (Exception e) {
            throw new DBException("FeedbackDaoImpl update(feedback) error...", e);
        }
        return effected;

    }

    @Override
    public List<Feedback> selectAllFeedback(SearchFilter searchFilter, Feedback feedback) throws DBException {
        List<Feedback> feedbackList;
        try {
            searchFilter.setBegin((searchFilter.getPage() - 1) * searchFilter.getRows());
            searchFilter.setEnd((searchFilter.getPage()) * searchFilter.getRows());
            Map<String, Object> map = new HashMap<>();
            map.put("searchFilter", searchFilter);
            map.put("feedback", feedback);
            feedbackList = getSqlSession().selectList("Feedback.selectAll", map);
        } catch (Exception e) {
            throw new DBException("FeedbackDaoImpl selectAllFeedback() error...", e);
        }
        return feedbackList;
    }

    @Override
    public long selectFeedbackCount(Feedback feedback) throws DBException {
        long count;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("feedback", feedback);
            count = getSqlSession().selectOne("Feedback.selectCount", map);
        } catch (Exception e) {
            throw new DBException("FeedbackDaoImpl selectFeedbackCount() error...", e);
        }
        return count;
    }

    @Override
    public Feedback selectFeedback(Feedback feedback) throws DBException {
        try {
            feedback = getSqlSession().selectOne("Feedback.select", feedback);
        } catch (Exception e) {
            throw new DBException("FeedbackDaoImpl selectFeedback(feedback) error...", e);
        }
        return feedback;
    }
}
