package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetBookInfoByBidReq;
import cn.com.miaoto.modules.book.model.GetBookInfoByBidResp;

/**
 * Created by hx on 2017/7/21.
 */
public interface GetBookInfoByBidService {
    GetBookInfoByBidResp getBookInfoByBid(GetBookInfoByBidReq reqBean, GetBookInfoByBidResp respBean);
}
