package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;

/**
 * Created by hx on 2017/9/4.
 */
public class GetClaimNumberResp extends ResponseInfo {

    private String clainNumber;

    public String getClainNumber() {
        return clainNumber;
    }

    public void setClainNumber(String clainNumber) {
        this.clainNumber = clainNumber;
    }
}
