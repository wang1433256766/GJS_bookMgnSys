package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

/**
 * Created by hx on 2017/7/22.
 */
public class GetUserBorrowedBookReq extends RequestInfo{

    private  int uid;

    private long backBookBeid;

    public long getBackBookBeid() {
        return backBookBeid;
    }

    public void setBackBookBeid(long backBookBeid) {
        this.backBookBeid = backBookBeid;
    }

    public GetUserBorrowedBookReq(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
