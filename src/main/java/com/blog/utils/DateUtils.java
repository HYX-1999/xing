package com.blog.utils;

import java.util.Date;

public class DateUtils {

    public final static String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

}
