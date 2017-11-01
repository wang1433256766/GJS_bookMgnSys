
package cn.com.miaoto.util;

import cn.com.miaoto.pojo.UserInfo;
import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class StringUtil {
    /**
     * 拼装字符串
     *
     * @param stringArr 需要拼接的字符串数组
     */
    public static String appendStr(String... stringArr) {
        if (null == stringArr || 0 == stringArr.length) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : stringArr) {
            sb.append(toString(str));
        }
        return sb.toString();
    }

    /**
     * 将null转为空字符串
     *
     * @param str 传入字符串
     */
    public static String toString(String str) {
        return null == str ? "" : str;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 通过属性名获得get方法
     *
     * @param fieldName 属性名
     */
    public static String getGetmethod(String fieldName) {
        if (StringUtil.isEmpty(fieldName)) {
            return null;
        }
        return StringUtil.appendStr("get", fieldName.substring(0, 1).toUpperCase(), fieldName.substring(1));
    }

    /**
     * 判断字符串是否为数字
     *
     * @param str 字符串
     * @return 是否为数字
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 产生随机字符
     *
     * @param length 字符长度
     * @return 字符串
     */
    public static String createRandomCharData(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);

            switch (index) {
                case 0:
                    data = randdata.nextInt(10);
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;
                    sb.append((char) data);
                    break;
                case 2:
                    data = randdata.nextInt(26) + 97;
                    sb.append((char) data);
                    break;
            }
        }
        return sb.toString();
    }

    public static String encodeSubject(String subject) {
        byte[] base64 = Base64.encodeBase64(subject.getBytes());
        return String.format("=?UTF-8?B?%s?=", new String(base64));
    }

    public static List<String> splitKey(String splitStr) {
        splitStr = " " + splitStr + " ";
        // 最后的key list
        List<String> keyList = new ArrayList<>();
        // 待用空格分割的句子
        List<String> waitList = new ArrayList<>();
        //引号句子的位置
        HashSet<Integer> set = new HashSet<>();
        //用引号分割的list
        String[] tmpKey = splitStr.split("'");

        int quotaNum = (tmpKey.length - 1) / 2;
        for (int i = 1; i <= quotaNum; i++) {
            set.add(i * 2);
        }

        if (quotaNum > 0) {
            for (int i = 1; i <= tmpKey.length; i++) {
                if (set.contains(i)) {
                    if (!StringUtil.isEmpty(tmpKey[i - 1].trim())) {
                        keyList.add(tmpKey[i - 1].trim());
                    }
                } else {
                    waitList.add(tmpKey[i - 1]);
                }
            }
        } else {
            // 没有2个引号包含的句子,全部放入waitList
            for (String key : tmpKey) {
                waitList.add(key.trim());
            }
        }

        for (String str : waitList) {
            String[] tmp = str.split("\\s+");
            for (int i = 0; i < tmp.length; i++) {
                if (!StringUtil.isEmpty(tmp[i])) {
                    keyList.add(tmp[i].trim());
                }
            }
        }
        return keyList;
    }

    public static String getXmlPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        path = path.replace("file:", "");
        path = path.replace("classes/", "");
        return path;
    }


    public static UserInfo clearSensitive(UserInfo userInfo) {
        userInfo.setSalt("");
        userInfo.setPwd("");
        return userInfo;
    }

    public static List<UserInfo> clearSensitive(List<UserInfo> userInfoList) {
        for (UserInfo user : userInfoList) {
            user.setPwd("");
            user.setSalt("");
        }
        return userInfoList;
    }

    public static String fillZero(long num) {
        String numStr = String.valueOf(num);
        int zeroNum = 7 - numStr.length();
        for (int i = 0; i < zeroNum; i++) {
            numStr = "0" + numStr;
        }
        return numStr;
    }

}
