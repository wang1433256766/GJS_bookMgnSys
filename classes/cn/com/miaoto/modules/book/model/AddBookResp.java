package cn.com.miaoto.modules.book.model;

import cn.com.miaoto.common.httpBean.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class AddBookResp extends ResponseInfo {
	int result = 0;
	List<Long> barcodeList = new ArrayList<>();

	public List<Long> getBarcodeList() {
		return barcodeList;
	}

	public void setBarcodeList(List<Long> barcodeList) {
		this.barcodeList = barcodeList;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
