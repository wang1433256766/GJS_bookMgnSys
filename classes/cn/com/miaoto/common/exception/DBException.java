package cn.com.miaoto.common.exception;

public class DBException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 含自定义错误信息的构造方法
     *
     * @param strMsg 自定义错误信息
     */
    public DBException(String strMsg) {
        super(strMsg);
    }

    /**
     * 带参构造
     *
     * @param ex 传入的异常类实例
     */
    public DBException(Throwable ex) {
        super(ex);
    }

    /**
     * 带参构造
     *
     * @param strMsg 待记录的异常消息
     * @param ex     传入的异常类实例
     */
    public DBException(String strMsg, Throwable ex) {
        super(strMsg, ex);
    }

    /**
     * 重写
     *
     * @return 返回该类实例的字符串表示
     */
    public String toString() {
        return super.toString();
    }
}
