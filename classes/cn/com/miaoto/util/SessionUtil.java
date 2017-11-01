package cn.com.miaoto.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * session相关工具类<br>
 * 依赖: shiro-core-1.2.3.jar, shiro-spring-1.2.3.jar, shiro-web-1.2.3.jar
 */
public class SessionUtil {
    /**
	 * 设置session
	 * 
	 * @param key
	 *            session的key
	 * @param value
	 *            session的value
	 */
    public static void setSession(String key, String value) {
        Subject email = SecurityUtils.getSubject();
        if (null != email) {
            Session session = email.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

	/**
	 * 获取session对应calue
	 * 
	 * @param key
	 *            session的key
	 * @return session的value
	 */
    public static Object getSessionValue(String key) {
        if (key == null || key.trim().equals("")) {
            return null;
        } else {
            Subject email = SecurityUtils.getSubject();
            if (email != null) {
                Session session = email.getSession();
                if (null != session) {
                    return session.getAttribute(key);
				} else {
                }
			} else {
            }
        }
        return null;
    }
}
