package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

/**
 * Created by hx on 2017/7/21.
 */
public class GetBookInfoByBidReq extends RequestInfo{
    int bid;

    public GetBookInfoByBidReq(int bid) {
        this.bid = bid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
}
