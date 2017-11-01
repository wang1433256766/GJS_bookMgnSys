package cn.com.miaoto.dao.inf;

import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.modules.notify.model.GetAllNotifyReq;
import cn.com.miaoto.pojo.Notify;

import java.util.List;

/**
 * Created by hx on 2017/7/31.
 */
public interface NotifyDao {

    int insert(Notify notify) throws DBException;

    List<Notify> selectAll(GetAllNotifyReq reqBean) throws DBException;

    int selectCount(Notify notify) throws DBException;

    Notify select(Notify notify) throws DBException;
}
