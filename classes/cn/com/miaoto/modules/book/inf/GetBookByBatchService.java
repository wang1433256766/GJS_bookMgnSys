package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetBookByBatchReq;
import cn.com.miaoto.modules.book.model.GetBookByBatchResp;

/**
 * Created by hx on 2017/8/21.
 */
public interface GetBookByBatchService {
    GetBookByBatchResp getBookByBatch(GetBookByBatchReq reqBean, GetBookByBatchResp respBean);
}
