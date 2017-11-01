package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetHotBorrowReq;
import cn.com.miaoto.modules.book.model.GetHotBorrowResp;

/**
 * Created by hx on 2017/8/22.
 */
public interface GetHotBorrowService {
    GetHotBorrowResp getHotBorrow(GetHotBorrowReq reqBean, GetHotBorrowResp respBean);
}
