package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.pojo.Feedback;
import cn.com.miaoto.pojo.common.SearchFilter;

import java.util.List;

/**
 * Created by hx on 2017/7/27.
 */
public interface FeedbackDao {

    int insert(Feedback feedback) throws DBException;

    List<Feedback> selectAllFeedback(SearchFilter searchFilter, Feedback feedback) throws DBException;

    int update(Feedback feedback) throws DBException;

    long selectFeedbackCount(Feedback feedback) throws DBException;

    Feedback selectFeedback(Feedback feedback) throws DBException;
}
