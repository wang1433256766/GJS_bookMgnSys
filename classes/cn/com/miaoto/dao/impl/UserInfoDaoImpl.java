package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.UserInfoDao;
import cn.com.miaoto.modules.userInfo.model.GetAllUserInfoReq;
import cn.com.miaoto.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * userInfoDao
 */
@Repository
public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserInfoDaoImpl.class);

    @Override
    public List<UserInfo> queryAllUsers(GetAllUserInfoReq reqBean) throws DBException {
        List<UserInfo> userInfoList;
        try {
            userInfoList = getSqlSession().selectList("UserInfo.selectAll", reqBean);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl queryAllUsers() error...", e);
        }
        return userInfoList;
    }

    @Override
    public UserInfo queryUserInfo(UserInfo user) throws DBException {
        UserInfo userInfo;
        try {
            userInfo = getSqlSession().selectOne("UserInfo.selectOne", user);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl queryUserInfo() error...", e);
        }
        return userInfo;
    }

    @Override
    public int addUser(UserInfo user) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("UserInfo.insert", user);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl addUser() error...", e);
        }
        return effected;
    }

    @Override
    public int update(UserInfo user) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("UserInfo.update", user);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl update() error...", e);
        }
        return effected;
    }

    @Override
    public List<UserInfo> selectAva(UserInfo user) throws DBException {
        List<UserInfo> userInfoList;
        try {
            userInfoList = getSqlSession().selectList("UserInfo.selectAva", user);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl selectAva(user) error...", e);
        }
        return userInfoList;
    }

    @Override
    public int count(UserInfo user) throws DBException {
        int count;
        try {
            count = getSqlSession().selectOne("UserInfo.count", user);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl count(user) error...", e);
        }
        return count;
    }

    @Override
    public int updateByIDCard(UserInfo user) throws DBException {
        int effected;
        try {
            effected = getSqlSession().update("UserInfo.updateByIDCard", user);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl updateByIDCard() error...", e);
        }
        return effected;
    }

    @Override
    public UserInfo selectNext(int row) throws DBException {
        UserInfo user;
        try {
            HashMap<String, Integer> map = new HashMap<>();
            map.put("row", row);
            user = getSqlSession().selectOne("UserInfo.selectNext", map);
        } catch (Exception e) {
            throw new DBException("UserInfoDaoImpl selectNext() error...", e);
        }
        return user;
    }
}
