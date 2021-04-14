package com.gyp.springboot.face.single;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 19:40 - 2021/4/6
 */
public class Singleton4 {

    private static Singleton4 singleton4;

    private Singleton4(){

    }

    public static synchronized Singleton4 getInstance(){
        if(singleton4 == null){
            return new Singleton4();
        }
        return singleton4;
    }
}
