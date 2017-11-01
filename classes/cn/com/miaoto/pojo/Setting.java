package cn.com.miaoto.pojo;


public class Setting {
	private int sid;
	private String key;
	private String value;
	private int uid;
	private String createtime;
	private String updatetime;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	@Override
	public String toString() {
		return "Setting [sid=" + sid + ", key=" + key + ", value=" + value + ", uid=" + uid + ", createtime=" + createtime + ", updatetime=" + updatetime
				+ "]";
	}

}
