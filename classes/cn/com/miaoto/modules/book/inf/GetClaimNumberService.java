package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetClaimNumberReq;
import cn.com.miaoto.modules.book.model.GetClaimNumberResp;

/**
 * Created by hx on 2017/9/4.
 */
public interface GetClaimNumberService {

    GetClaimNumberResp getClaimNumber(GetClaimNumberReq reqBean, GetClaimNumberResp respBean);

}
