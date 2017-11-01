package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;

public class AddBookEntityResp extends ResponseInfo {
	private int result;
	private long barcode;

	public long getBarcode() {
		return barcode;
	}

	public void setBarcode(long barcode) {
		this.barcode = barcode;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
