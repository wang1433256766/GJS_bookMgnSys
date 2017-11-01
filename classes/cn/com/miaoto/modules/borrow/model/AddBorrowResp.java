package cn.com.miaoto.modules.borrow.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;

public class AddBorrowResp extends ResponseInfo {
	private int id;

	private int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AddBorrowResp [id=" + id + "]";
	}

}
