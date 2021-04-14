package com.gyp.springboot.face.single;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 19:32 - 2021/4/6
 */
public class Singleton3 {

    private static volatile Singleton3 singleton3;

    private  Singleton3(){}


    public Singleton3 getInstance(){
       if(singleton3 == null){
           synchronized (this){
               if(singleton3 == null){
                   return new Singleton3();
               }
           }
       }
       return singleton3;
    }
}
