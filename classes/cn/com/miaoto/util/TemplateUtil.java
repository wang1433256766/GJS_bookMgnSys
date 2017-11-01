package cn.com.miaoto.util;

import java.util.ArrayList;

/**
 * Created by hx on 2017/8/16.
 */
public class TemplateUtil {

    public static String replace(String origin, ArrayList<String> paras) {
        String newStr = origin;
        for (int i = 0; i < paras.size(); i++) {
            String oldStr = "{" + (i+1) + "}";
            newStr = newStr.replace(oldStr, paras.get(i));
        }
        return newStr;
    }
}
