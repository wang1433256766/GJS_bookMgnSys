package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.BookEntity;

public class GetBookInfoResp extends ResponseInfo {
	
	private BookEntity bookEntity;

	public BookEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

}
