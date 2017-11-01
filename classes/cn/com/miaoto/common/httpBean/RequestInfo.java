package cn.com.miaoto.common.httpBean;

import java.lang.reflect.Field;

public class RequestInfo {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                sb.append(field.getName()).append(" = ").append(field.get(this).toString()).append(";");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
