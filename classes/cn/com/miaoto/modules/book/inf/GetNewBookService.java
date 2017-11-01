package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetNewBookReq;
import cn.com.miaoto.modules.book.model.GetNewBookResp;

/**
 * Created by hx on 2017/8/22.
 */
public interface GetNewBookService {
    GetNewBookResp getNewBook(GetNewBookReq reqBean, GetNewBookResp respBean);
}
