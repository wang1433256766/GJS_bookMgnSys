package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.BackBookReq;
import cn.com.miaoto.modules.book.model.BackBookResp;

public interface BackBookService {

	BackBookResp backBook(BackBookReq reqBean, BackBookResp respBean);

}
