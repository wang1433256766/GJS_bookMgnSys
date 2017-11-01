package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BatchInfoDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.GetBookByBatchService;
import cn.com.miaoto.modules.book.model.GetBookByBatchReq;
import cn.com.miaoto.modules.book.model.GetBookByBatchResp;
import cn.com.miaoto.pojo.BatchInfo;
import cn.com.miaoto.pojo.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/21.
 */
@Service
public class GetBookByBatchServiceImpl extends AbstractService<GetBookByBatchReq, GetBookByBatchResp> implements GetBookByBatchService {
    public static final Logger LOGGER = LoggerFactory.getLogger(GetBookByBatchServiceImpl.class);

    @Resource
    BookEntityDao bookEntityDao;

    @Resource
    BatchInfoDao batchInfoDao;

    @Override
    public GetBookByBatchResp getBookByBatch(GetBookByBatchReq reqBean, GetBookByBatchResp respBean) {
        return (GetBookByBatchResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetBookByBatchReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetBookByBatchReq reqBean, GetBookByBatchResp respBean) throws Exception {
        BookEntity queryBookEntity = new BookEntity();
        queryBookEntity.setBatchId(reqBean.getBatchId());
        List<BookEntity> bookEntityList = bookEntityDao.queryBookEntity(queryBookEntity);
        if (bookEntityList == null) {
            LOGGER.error("get booklist by batch is null");
            return;
        }
        if (bookEntityList.size() == 0) {
            LOGGER.warn("get booklist by batch size is 0");
        }
        respBean.setBookEntityList(bookEntityList);

        // batch 信息
        BatchInfo batchInfo = new BatchInfo();
        batchInfo.setBiid(reqBean.getBatchId());
        List<BatchInfo> batchInfoList = batchInfoDao.select(batchInfo);
        if (batchInfoList == null) {
            LOGGER.error("get batch info failed");
            return;
        }
        if (batchInfoList.size() == 0) {
            LOGGER.error("get batch info size is 0");
            return;
        }
        respBean.setBatchInfo(batchInfoList.get(0));
        respBean.setResultCode(1);
    }
}
