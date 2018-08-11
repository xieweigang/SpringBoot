package com.ucmed.sxpt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * 时分秒
     */
    private final static SimpleDateFormat hh_mm_ss = new SimpleDateFormat(
            "HH:mm:ss");

    /**
     * 时分秒
     */
    private final static SimpleDateFormat hh_mm = new SimpleDateFormat("HH:mm");

    /**
     * yyyy/MM/dd
     */
    private final static SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy/MM/dd");
    /**
     * yyyyMMdd
     */
    private final static SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
            "yyyyMMdd");
    /**
     * yyyy-MM-dd
     */
    private final static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(
            "yyyy-MM-dd");
    /**
     * yyyyMMddHHmm
     */
    private final static SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat(
            "yyyyMMddHHmm");

    /**
     * yyyy-MM-dd HH:mm
     */
    private final static SimpleDateFormat sdf1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private final static SimpleDateFormat sdf2 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    /**
     * HHmm
     */
    private final static SimpleDateFormat sdf3 = new SimpleDateFormat("HHmm");
    /**
     * yyyyMMddhhmmssSSS
     */
    private final static SimpleDateFormat yyyyMMddhhmmssSSS = new SimpleDateFormat(
            "yyyyMMddhhmmssSSS");

    private final static SimpleDateFormat MMddHHmm = new SimpleDateFormat(
            "MM-dd HH:mm");

    /**
     * date转String MM-dd HH:mm
     */
    public static String toStringMMddHHmm(Date date) {
        if (date == null)
            return "";
        return MMddHHmm.format(date);
    }

    /**
     * date转String yyyy/MM/dd
     */
    public String simpleDate(Date date) {
        if (date == null)
            return "";
        return sdf.format(date);
    }

    /**
     * date转String yyyyMMddhhmmssSSS
     */
    public static String simpleDate2(Date date) {
        if (yyyyMMddhhmmssSSS == null)
            return null;
        return yyyyMMddhhmmssSSS.format(date);

    }

    public static String stringDate10(String date) {
        return DateUtil.formatDate1(StringToDate(date));
    }

    public static String formatDate1(Date date) {
        if (date == null)
            return "";
        return yyyy_MM_dd.format(date);
    }

    /**
     * 取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {

        Calendar calendar = Calendar.getInstance();

        int i = calendar.get(1);
        int j = calendar.get(2) + 1;
        int k = calendar.get(5);
        return "" + i + (j >= 10 ? "" + j : "0" + j)
                + (k >= 10 ? "" + k : "0" + k);
    }

    public static Date calculateDate(Date startDay, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDay);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date calculateMonth(Date startDay, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDay);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * String 获取当前时间yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return dateToString4(getCurrentDate());
    }

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * String 获取今天日期yyyyMMdd
     *
     * @return
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        return DateUtil.getyyyyMMdd(calendar.getTime());
    }

    /**
     * String 获取明天日期yyyyMMdd
     *
     * @return
     */
    public static String getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return DateUtil.getyyyyMMdd(calendar.getTime());
    }

    /**
     * String 获取几天后日期yyyyMMdd
     *
     * @return
     */
    public static String getDateByDays(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return DateUtil.getyyyyMMdd(calendar.getTime());
    }

    /**
     * String 获取几天后日期yyyy-MM-dd 0今天 1明天...
     *
     * @return
     */
    public static String getDateByDays2(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return DateUtil.getyyyy_MM_dd(calendar.getTime());
    }

    /**
     * 将日期格式化为字符串
     *
     * @param date
     * @return
     */
    //@Deprecated
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getyyyyMMdd(Date date) {
        if (date == null)
            return "";
        return yyyyMMdd.format(date);
    }

    public static String getyyyy_MM_dd(Date date) {
        if (date == null)
            return "";
        return yyyy_MM_dd.format(date);
    }

    public static Date StringToDate(String date) {
        try {
            return yyyyMMdd.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date StringToDate6(String date) {
        try {
            return yyyy_MM_dd.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * String 获取时间 yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String format1(Date date) {
        if (date == null)
            date = new Date();
        return sdf1.format(date);
    }

    public static String dateToString1(Date date) {
        try {
            return sdf1.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Date 获取时间 yyyy-MM-dd HH:mm
     */
    public static Date StringToDate1(String date) {
        try {
            return sdf1.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * Date 获取时间 yyyy/MM/dd
     */
    public static Date StringToDate5(String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * Date 获取时间yyyyMMddHHmm
     */
    public static Date StringToDate2(String date) {
        try {
            return yyyyMMddHHmm.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * Date 获取时间yyyyMMddHHmm
     */
    public static String DateToStringyyyyMMddHHmm(Date date) {
        if (date == null)
            date = new Date();
        return yyyyMMddHHmm.format(date);
    }

    /**
     * Date 获取时间yyyyMMdd
     */
    public static Date StringToDate3(String date) {
        try {
            return yyyyMMdd.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date StringToDate4(String date) {
        try {
            return sdf2.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static String dateToString4(Date date) {
        try {
            return sdf2.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getHHmm(Date date) {
        try {
            return sdf3.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static Integer getWeekId(String date) {
        Date d = new Date();
        try {
            d = yyyyMMdd.parse(date);
        } catch (ParseException e) {
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        Integer i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return i;
    }

    /**
     * 获取周几
     *
     * @param date yyyyMMdd
     * @return
     */
    public static String getWeek(String date) {
        String week = "";
        switch (getWeekId(date)) {
            case 1:
                week = "周一";
                break;
            case 2:
                week = "周二";
                break;
            case 3:
                week = "周三";
                break;
            case 4:
                week = "周四";
                break;
            case 5:
                week = "周五";
                break;
            case 6:
                week = "周六";
                break;
            case 0:
                week = "周日";
                break;

            default:
                break;
        }
        return week;
    }

    /**
     * 时间计算（日）
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getDate(Date date, int n) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DAY_OF_MONTH, n);
        return gc.getTime();
    }

    public static Date getDate(Date date, int field, int n) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, n);
        return gc.getTime();
    }

    public static Date weekToDate(Number n) {
        Integer w = n.intValue();
        Calendar calendar = Calendar.getInstance();
        Integer t = calendar.get(Calendar.DAY_OF_WEEK);
        t = t - 1 == 0 ? 7 : t - 1;
        Integer i = w - t < 0 ? w - t + 7 : w - t;
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return calendar.getTime();
    }

    /**
     * 判断当前日期是星期几
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取当前的时间
     */
    public static String getCutTime() {
        Date date = new Date();
        String time = hh_mm_ss.format(date);
        return time;
    }

    /**
     * 获取当前的时间HH:mm
     */
    public static String getCutTimeHHmm() {
        Date date = new Date();
        String time = hh_mm.format(date);
        return time;
    }

    public static int getCurrentAgeByBirthdate(String brithday) {
        Date brithDay = StringToDate6(brithday);
        Calendar cal = Calendar.getInstance();
        if (cal.before(brithDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(brithDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            // 如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 时间比较，返回当前时间减去传入时间的差值，单位（秒）
     */
    public static Long DateCompare(Date mDate2) {
        Date mDate1 = new Date();
        return (mDate1.getTime() - mDate2.getTime()) / 1000;
    }

    /**
     * 时间比较，返回时间1减去时间2的差值，单位（秒）
     */
    public static Long DateCompare(Date mDate1, Date mDate2) {
        return (mDate1.getTime() - mDate2.getTime()) / 1000;
    }
}
