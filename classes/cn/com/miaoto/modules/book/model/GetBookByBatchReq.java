package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

/**
 * Created by hx on 2017/8/21.
 */
public class GetBookByBatchReq extends RequestInfo {

    private int batchId;

    public GetBookByBatchReq(int batchId) {
        this.batchId = batchId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }
}
