package cn.com.miaoto.modules.book.model;

import java.util.List;

import cn.com.miaoto.common.httpBean.ResponseInfo;
import cn.com.miaoto.pojo.BookEntity;

public class GetUncheckBookResp extends ResponseInfo {
	List<BookEntity> bookEntityList;

	public List<BookEntity> getBookEntityList() {
		return bookEntityList;
	}

	public void setBookEntityList(List<BookEntity> bookEntityList) {
		this.bookEntityList = bookEntityList;
	}

}
