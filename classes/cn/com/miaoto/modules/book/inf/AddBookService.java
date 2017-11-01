package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.AddBookReq;
import cn.com.miaoto.modules.book.model.AddBookResp;

public interface AddBookService {

	AddBookResp addBook(AddBookReq reqBean, AddBookResp respBean);

}
