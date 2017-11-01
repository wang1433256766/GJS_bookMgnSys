package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.follow.model.GetFollowReq;
import cn.com.miaoto.pojo.Follow;

import java.util.List;

/**
 * Created by hx on 2017/8/17.
 */
public interface FollowDao {

    int insert(Follow follow) throws DBException;

    List<Follow> selectReminder(Follow follow) throws DBException;

    int updateStatus(Follow follow) throws DBException;

    List<Follow> selectOwn(GetFollowReq reqBean) throws DBException;

    List<Follow> selectFollowing(Follow follow) throws DBException;

}
