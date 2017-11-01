package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetHotBookReq;
import cn.com.miaoto.modules.book.model.GetHotBookResp;

/**
 * Created by hx on 2017/8/22.
 */
public interface GetHotBookService {
    GetHotBookResp getHotBook(GetHotBookReq reqBean, GetHotBookResp respBean);
}
