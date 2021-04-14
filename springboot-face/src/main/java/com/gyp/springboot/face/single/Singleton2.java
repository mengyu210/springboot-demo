package com.gyp.springboot.face.single;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 19:28 - 2021/4/6
 */
public class Singleton2 {

    private  static Singleton2 singleton = null;

    public static Singleton2 getInstance() {
        singleton = new Singleton2();
        return singleton ;
    }
}
