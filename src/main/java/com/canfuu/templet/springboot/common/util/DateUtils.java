package com.canfuu.templet.springboot.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 获取系统当前时间
     * @return yyyy-MM-dd HH:mm:ss SS
     */
    public static String getSystemTime (String pattern){

        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static Date strToDate(String str) throws ParseException {
        return sdf.parse(str);
    }
    public static String dateToStr(Date date){
        return sdf.format(date);
    }
}
