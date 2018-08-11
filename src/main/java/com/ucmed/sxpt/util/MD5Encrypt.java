package com.ucmed.sxpt.util;

import org.thymeleaf.util.StringUtils;

import java.security.MessageDigest;

/**
 * MD5加密 Util.
 */
public class MD5Encrypt {
    /**
     * 对字符串进行MD5加密,返回长度为32的加密字符串.
     */
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            if (!StringUtils.isEmpty(s)) {
                byte[] strTemp = s.getBytes("utf-8");
                MessageDigest mdTemp = MessageDigest.getInstance("MD5");
                mdTemp.update(strTemp);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(str);
            } else {
                return "";
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回长度为16的MD5加密字符串.
     */
    public final static String MD5To16(String s) {
        String encrypted = MD5(s);
        if (!StringUtils.isEmpty(encrypted)
                && StringUtils.trim(encrypted).length() > 24) {
            return encrypted.trim().substring(8, 24);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(MD5Encrypt.MD5("123456"));
    }

}
