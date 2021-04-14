package com.gyp.springboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 16:23 - 2020/12/3
 */
public class DateUtil {

    /**
     * 默认格式化模式
     */
    private static final String DEFAULT_FOM = "yyyy-MM-dd HH:mm:ss";

    /**
     * 全局sdf
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat();


    /**
     * 使用默认的 pattren
     * @param date
     * @return
     */
    public static String format(Date date){
        return format(date, DEFAULT_FOM);
    }

    /**
     * 根据传入的pattern设置
     * @param date
     * @param pattern
     * @return
     */
    public static synchronized String format(Date date, String pattern){
        if(pattern == null || "".equals(pattern)){
            throw new IllegalArgumentException("pattern is not allow null or '' ");
        }
        return sdf.format(date);
    }
}
