package cn.com.miaoto.common;

/**
 * api返回信息结构
 */
public class ReturnMsg {

    private int status;
    private String msg;
    private Object data;

    public ReturnMsg() {

    }

    public void setMsg(int status, String msg, Object object) {
        this.status = status;
        this.msg = msg;
        this.data = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReturnMsg [status=" + status + ", msg=" + msg + ", data=" + data + "]";
    }

}
