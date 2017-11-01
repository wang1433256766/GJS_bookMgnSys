package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.UpdateBookReq;
import cn.com.miaoto.modules.book.model.UpdateBookResp;

public interface UpdateBookService {

	UpdateBookResp updateBook(UpdateBookReq reqBean, UpdateBookResp respBean);

}
