package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetUserBorrowedBookReq;
import cn.com.miaoto.modules.book.model.GetUserBorrowedBookResp;

/**
 * Created by hx on 2017/7/22.
 */
public interface GetUserBorrowedBookService {
    GetUserBorrowedBookResp getBookInfoByUid(GetUserBorrowedBookReq reqBean, GetUserBorrowedBookResp respBean);
}
