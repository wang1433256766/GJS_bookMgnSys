package cn.com.miaoto.pojo.common;

import java.util.List;

/**
 * Created by hx on 2017/8/14.
 */
public class AddBookReturn {
    private String bookName;
    private List<Long> barcodeList;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<Long> getBarcodeList() {
        return barcodeList;
    }

    public void setBarcodeList(List<Long> barcodeList) {
        this.barcodeList = barcodeList;
    }
}
