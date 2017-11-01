package cn.com.miaoto.dao.impl;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.common.mvcBean.BaseDao;
import cn.com.miaoto.dao.inf.BatchInfoDao;
import cn.com.miaoto.pojo.BatchInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * batchInfoDao
 */
@Repository
public class BatchInfoDaoImpl extends BaseDao implements BatchInfoDao {
    public static final Logger LOGGER = LoggerFactory.getLogger(BatchInfoDaoImpl.class);

    @Override
    public List<BatchInfo> selectAll() throws DBException {
        List<BatchInfo> batchInfoList;
        try {
            Map<String, Integer> map = new HashMap<>();
            batchInfoList = getSqlSession().selectList("BatchInfo.selectAll", map);
        } catch (Exception e) {
            throw new DBException("BatchInfoDaoImpl selectAll() error...", e);
        }
        return batchInfoList;
    }

    @Override
    public int insert(BatchInfo batchInfo) throws DBException {
        int effected;
        try {
            effected = getSqlSession().insert("BatchInfo.insert", batchInfo);
        } catch (Exception e) {
            throw new DBException("BatchInfoDaoImpl insert(batchInfo) error...", e);
        }
        return effected;
    }

    @Override
    public List<BatchInfo> select(BatchInfo batchInfo) throws DBException {
        List<BatchInfo> batchInfoList;
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("batchId", batchInfo.getBiid());
            batchInfoList = getSqlSession().selectList("BatchInfo.select", map);
        } catch (Exception e) {
            throw new DBException("BatchInfoDaoImpl select() error...", e);
        }
        return batchInfoList;
    }
}
