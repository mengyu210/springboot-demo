package com.gyp.springboot.face.single;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 19:43 - 2021/4/6
 */
public class Singleton5 {

    private static Singleton5 singleton5 ;
    private static ThreadLocal<Singleton5> threadLocal = new ThreadLocal<>();

    public static Singleton5 getInstance(){
        if(threadLocal.get() == null){
            synchronized (Singleton5.class){
                if(singleton5 == null){
                    return new Singleton5();
                }
            }
            threadLocal.set(singleton5);
        }
        return threadLocal.get();
    }
}
