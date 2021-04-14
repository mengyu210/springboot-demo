package com.gyp.springboot.face.algorithm.subtring;

/**
 * @Author: guoyapeng
 * @TODO: 说明 字符串反转
 * @Date: Created in 22:22 - 2021/4/6
 */
public class Reverse {

    /**
     * 递归反转字符串
     */
    public static String reverseDg(String s){
       if(s.length() <= 1){
           return s;
       }
       return reverseDg(s.substring(1)) + s.charAt(0);
    }

    /**
     * StringBuffer 线程安全
     * @param s
     * @return
     */
    public static String reverse(String s){
        StringBuffer stringBuffer = new StringBuffer(s);
        return stringBuffer.reverse().toString();
    }
}
