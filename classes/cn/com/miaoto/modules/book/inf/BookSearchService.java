package cn.com.miaoto.modules.book.inf;

import cn.com.miaoto.modules.book.model.BookSearchReq;
import cn.com.miaoto.modules.book.model.BookSearchResp;

public interface BookSearchService {

	BookSearchResp bookSearch(BookSearchReq reqBean, BookSearchResp respBean);

}
