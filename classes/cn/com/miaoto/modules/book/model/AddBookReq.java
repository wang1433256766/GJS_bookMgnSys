package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;

public class AddBookReq extends RequestInfo {
	private Book book;
	private BookEntity bookEntity;
	private int numOfBook;

	public int getNumOfBook() {
		return numOfBook;
	}

	public void setNumOfBook(int numOfBook) {
		this.numOfBook = numOfBook;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookEntity getBookEntity() {
		return bookEntity;
	}

	public void setBookEntity(BookEntity bookEntity) {
		this.bookEntity = bookEntity;
	}

	public AddBookReq(Book book, BookEntity bookEntity) {
		super();
		this.book = book;
		this.bookEntity = bookEntity;
	}

}
