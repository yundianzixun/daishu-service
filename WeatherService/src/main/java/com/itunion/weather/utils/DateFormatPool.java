package com.itunion.weather.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatPool {
    private DateFormatPool() {}

    private static Map<String, SimpleDateFormat> formats = new HashMap<String, SimpleDateFormat>();

    /** 无分隔日期. */
    public static final String DATE_NO_DIVIDER = "yyyyMMdd";

    /** 无分隔精度为秒的时间. */
    public static final String DATETIME_SECOND_END_NO_DIVIDER = "yyyyMMddHHmmss";

    /** 无分隔精度为毫秒的时间. */
    public static final String DATETIME_MILLI_SECOND_END_NO_DIVIDER = "yyyyMMddHHmmssSSS";

    /** 横线分隔日期. */
    public static final String DATE_HORIZONTAL_LINE_DIVIDER = "yyyy-MM-dd";

    /** 横线分隔日期. */
    public static final String DATE_HORIZONTAL_LINE_DIVIDER_DATE = "yyyy-M-d";

    /** 斜线分隔日期. */
    public static final String DATE_OBLIQUE_LINE_DIVIDER = "yyyy/mm/dd";

    /** 中国地区常用时间. */
    public static final String DATETIME_CONVENTIONAL_CN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATETIME_CONVENTIONAL_ZW = "yyyy年MM月dd日";

    /**
     * @desc 根据格式返回格式器
     * @author byshu
     * @param pattern 日期格式
     * @return
     */
    public static SimpleDateFormat get(String pattern) {
        SimpleDateFormat df = formats.get(pattern);
        if (df == null) {
            synchronized (DateFormatPool.class) {
                if (df == null) {
                    df = new SimpleDateFormat(pattern);
                    formats.put(pattern, df);
                }
            }
        }
        return df;
    }

    /**
     * 获取指定时间指定格式的字符串
     */
    public static String formatDate(Date date, String pattern){
        if (date == null || StringUtils.isEmpty(pattern)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }


}
