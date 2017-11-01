package cn.com.miaoto.pojo.common;

public class SearchFilter {
    private String sidx;
    private String sord;
    private int rows;
    private int page;

    private int begin;
    private int end;

    public SearchFilter() {
    }

    public SearchFilter(String sidx, String sord, int rows, int page) {
        this.sidx = sidx;
        this.sord = sord;
        this.rows = rows;
        this.page = page;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
