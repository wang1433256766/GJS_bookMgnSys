package cn.com.miaoto.pojo;

public class Permission {
	private int pid;
	private String pname;
	private String createtime;
	private String updatetime;
	private String mark;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Permission{" +
				"pid=" + pid +
				", pname='" + pname + '\'' +
				", createtime='" + createtime + '\'' +
				", updatetime='" + updatetime + '\'' +
				", mark='" + mark + '\'' +
				'}';
	}
}
