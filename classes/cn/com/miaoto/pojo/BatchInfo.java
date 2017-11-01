package cn.com.miaoto.pojo;

public class BatchInfo {
	private int biid;
	private String createtime;
	private String mark;

	public int getBiid() {
		return biid;
	}

	public void setBiid(int biid) {
		this.biid = biid;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "BatchInfo [biid=" + biid + ", createtime=" + createtime + ", mark=" + mark + "]";
	}


}
