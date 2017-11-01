package cn.com.miaoto.modules.book.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.GetBookInfoService;
import cn.com.miaoto.modules.book.model.GetBookInfoReq;
import cn.com.miaoto.modules.book.model.GetBookInfoResp;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;

@Service
public class GetBookInfoServiceImpl extends AbstractService<GetBookInfoReq, GetBookInfoResp> implements GetBookInfoService {

	@Resource
	BookDao bookDao;

	@Resource
	BookEntityDao bookEntityDao;

	@Override
	public GetBookInfoResp getBookInfoBycode(GetBookInfoReq reqBean, GetBookInfoResp respBean) {
		return (GetBookInfoResp) super.execute(reqBean, respBean);
	}

	@Override
	public boolean checkInput(GetBookInfoReq reqBean) {
		return true;
	}

	@Override
	protected void handle(GetBookInfoReq reqBean, GetBookInfoResp respBean) throws Exception {
		BookEntity bookEntityTmp = new BookEntity();
		bookEntityTmp.setBarcode(Long.parseLong(reqBean.getBarCode()));
		if(bookEntityDao.queryBookEntityByBar(bookEntityTmp).size() == 0) {
			return;
		}
		BookEntity bookEntity = bookEntityDao.queryBookEntityByBar(bookEntityTmp).get(0);
		if (bookEntity == null) {
			return;
		}
		Book book = bookDao.queryBookById(bookEntity.getBid());
		if (book == null) {
			return;
		}
		bookEntity.setBook(book);
		respBean.setBookEntity(bookEntity);
	}

}
