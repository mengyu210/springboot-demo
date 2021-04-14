package com.gyp.springboot.face.single;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 19:24 - 2021/4/6
 */
public class Singleton1 {



    private final static Singleton1 singleton1  = new Singleton1();

    public Singleton1() {

    }

    public static Singleton1 getInstance(){
        return singleton1;
    }
}
