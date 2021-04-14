package com.gyp.springboot.face.algorithm;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 22:51 - 2021/4/6
 */
public class JieCheng {

    public static int jieCheng(int n){
        if(n<=1){
            return n;
        }
        return n*jieCheng(n-1);
    }

    public static void main(String[] args) {
        int i = jieCheng(15);


        System.out.println(i);

        int i1 = 17 / 5;
        Integer c = 1;

        System.out.println(i1);
    }
}
