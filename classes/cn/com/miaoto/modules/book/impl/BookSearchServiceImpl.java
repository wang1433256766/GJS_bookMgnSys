package cn.com.miaoto.modules.book.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.BookSearchService;
import cn.com.miaoto.modules.book.model.BookSearchReq;
import cn.com.miaoto.modules.book.model.BookSearchResp;
import cn.com.miaoto.pojo.Book;

@Service
public class BookSearchServiceImpl extends AbstractService<BookSearchReq, BookSearchResp> implements BookSearchService {
	public static final Logger LOGGER = LoggerFactory.getLogger(BookSearchServiceImpl.class);

	@Resource
	BookDao bookDao;

	@Resource
	BookEntityDao bookEntityDao;

	@Override
	public BookSearchResp bookSearch(BookSearchReq reqBean, BookSearchResp respBean) {
		return (BookSearchResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(BookSearchReq reqBean) {
		return true;
	}

	@Override
	protected void handle(BookSearchReq reqBean, BookSearchResp respBean) throws Exception {
		List<Book> bookList = bookDao.searchBook(reqBean);
		int count = bookDao.searchBookCount(reqBean);
		respBean.setCount(count);
		respBean.setPage(reqBean.getSearchFilter().getPage());
		for (Book book : bookList) {
			int allCount = bookEntityDao.queryBookEntityCount(book.getBid(), null);
			if (allCount != 0) {
				book.setCount(allCount);
			}
			int available = bookEntityDao.queryBookEntityCount(book.getBid(), SiomConstants.BOOKSTATUS_AVAILABLE);
			if (available != 0) {
				book.setAvailable(available);
			}
		}
		respBean.setBookList(bookList);
	}
}
