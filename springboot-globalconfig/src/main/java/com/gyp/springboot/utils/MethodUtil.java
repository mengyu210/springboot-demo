package com.gyp.springboot.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 16:31 - 2020/9/24
 */
@Slf4j
public class MethodUtil {

    private MethodUtil(){

    }

    public static String getLineInfo(){
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return stackTraceElement.getFileName()+"->"+stackTraceElement.getLineNumber();
    }
}
