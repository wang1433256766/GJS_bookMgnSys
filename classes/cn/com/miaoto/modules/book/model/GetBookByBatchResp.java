package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.BatchInfo;
import cn.com.miaoto.pojo.BookEntity;

import java.util.List;

/**
 * Created by hx on 2017/8/21.
 */
public class GetBookByBatchResp extends ResponseInfo {

    private List<BookEntity> bookEntityList;

    private BatchInfo batchInfo;

    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }

    public List<BookEntity> getBookEntityList() {
        return bookEntityList;
    }

    public void setBookEntityList(List<BookEntity> bookEntityList) {
        this.bookEntityList = bookEntityList;
    }
}
