package cn.com.miaoto.pojo.common;

/**
 * Created by hx on 2017/8/14.
 */
public class BorrowReturn {
    private int bookStatus;
    private String bookName;
    private String barcode;

    public int getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
