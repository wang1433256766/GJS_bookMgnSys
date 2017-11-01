package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.userInfo.model.GetAllUserInfoReq;
import cn.com.miaoto.pojo.UserInfo;

import java.util.List;

public interface UserInfoDao {

    List<UserInfo> queryAllUsers(GetAllUserInfoReq reqBean) throws DBException;

    UserInfo queryUserInfo(UserInfo user) throws DBException;

    int addUser(UserInfo user) throws DBException;

    int update(UserInfo user) throws DBException;

    List<UserInfo> selectAva(UserInfo user) throws DBException;

    int count(UserInfo user) throws DBException;

    int updateByIDCard(UserInfo user) throws DBException;

    UserInfo selectNext(int row) throws DBException;
}