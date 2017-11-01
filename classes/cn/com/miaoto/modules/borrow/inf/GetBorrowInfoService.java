package cn.com.miaoto.modules.borrow.inf;

import cn.com.miaoto.modules.borrow.model.GetBorrowInfoReq;
import cn.com.miaoto.modules.borrow.model.GetBorrowInfoResp;

/**
 * Created by hx on 2017/8/25.
 */
public interface GetBorrowInfoService {

    GetBorrowInfoResp getBorrowInfoRecord(GetBorrowInfoReq reqBean, GetBorrowInfoResp respBean);

}
