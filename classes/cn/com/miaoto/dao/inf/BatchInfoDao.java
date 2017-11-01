package cn.com.miaoto.dao.inf;

import java.util.List;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.pojo.BatchInfo;

public interface BatchInfoDao {

	List<BatchInfo> selectAll() throws DBException;

	int insert(BatchInfo batchInfo) throws DBException;

	List<BatchInfo> select(BatchInfo batchInfo) throws DBException;

}
