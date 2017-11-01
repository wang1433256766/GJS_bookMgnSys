package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Book;

public class UpdateBookReq extends RequestInfo {
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public UpdateBookReq(Book book) {
		super();
		this.book = book;
	}

}
