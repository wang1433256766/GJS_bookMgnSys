package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.SynBaseDao;
import cn.com.miaoto.dao.inf.SynUserDao;
import cn.com.miaoto.pojo.SynUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hx on 2017/9/11.
 */
@Repository
public class SynUserDaoImpl extends SynBaseDao implements SynUserDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(SynUserDaoImpl.class);

    @Override
    public SynUserInfo queryOneUser(int row) throws DBException {
        SynUserInfo user;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("row", row);
            user = getSqlSession().selectOne("SynUser.selectOne", map);
        } catch (Exception e) {
            throw new DBException("SynUserDaoImpl queryOneUser() error...", e);
        }
        return user;
    }

    @Override
    public Integer count() throws DBException {
        int count;
        try {
            count = getSqlSession().selectOne("SynUser.selectCount", null);
        } catch (Exception e) {
            throw new DBException("SynUserDaoImpl count() error...", e);
        }
        return count;
    }
}
