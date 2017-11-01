package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.BookEntity;

public class UpdateBookEntityReq extends RequestInfo {
	private BookEntity bookEntity;

	public BookEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

	public UpdateBookEntityReq(BookEntity bookEntity) {
		super();
		this.bookEntity = bookEntity;
	}

}
