package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.GetUncheckBookReq;
import cn.com.miaoto.modules.book.model.GetUncheckBookResp;

public interface GetUncheckBookService {

	GetUncheckBookResp getUNcheckedBook(GetUncheckBookReq reqBean, GetUncheckBookResp respBean);

}
