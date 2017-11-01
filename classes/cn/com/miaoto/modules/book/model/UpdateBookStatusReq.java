package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.RequestInfo;

public class UpdateBookStatusReq extends RequestInfo {
	private int status;
	private long barCode;

	public UpdateBookStatusReq() {
		super();
	}

	public UpdateBookStatusReq(int status, long barCode) {
		super();
		this.status = status;
		this.barCode = barCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getBarCode() {
		return barCode;
	}

	public void setBarCode(long barCode) {
		this.barCode = barCode;
	}

	@Override
	public String toString() {
		return "UpdateBookStatusReq [status=" + status + ", barCode=" + barCode + "]";
	}

}
