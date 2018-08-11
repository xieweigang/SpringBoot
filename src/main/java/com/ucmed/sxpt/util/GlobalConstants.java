package com.ucmed.sxpt.util;

import org.thymeleaf.util.StringUtils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GlobalConstants {
    //public static final String ABS_URL = "/home/ucmed/file/sxpt"; // 生产
    //public static final String WEB_URL = "https://sxpt.zwjk.com"; // 生产
    public static final String ABS_URL = "/home/ucmed/opt/apache-tomcat-sxpt-32505/webapps"; // 测试
    //public static final String WEB_URL = "http://188e9u4827.iok.la:20119"; // 测试
    //public static final String WEB_URL = "https://testsxpt.zwjk.com"; // 测试
    public static final String WEB_URL = "http://test.shaoxpt.ucmed.cn"; // 测试
    public static final DecimalFormat DF0 = new DecimalFormat("######0"); //四色五入转换成整数
    public static final DecimalFormat DF0_00 = new DecimalFormat("0.00"); // 保留两位小数

    public static double DoubleValueOf(String value) {
        value = GlobalConstants.trim(value);
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(value);
        if (isNum.matches()) {
            return Double.parseDouble(value);
        }
        return Double.parseDouble("0");
    }

    public static String trim(String value) {
        if (StringUtils.isEmpty(value)) {
            return "";
        }
        return value.trim();
    }

    public static String RemoveZero(double value) {
        String val = GlobalConstants.DF0_00.format(value);
        val = val.replaceAll("0+?$", "");//去掉多余的0
        val = val.replaceAll("[.]$", "");//如最后一位是.则去掉
        return val;
    }
}
