package com.gyp.springboot.face.algorithm;

/**
 * @Author: guoyapeng
 * @TODO: 说明 爬楼梯问题其实质就是斐波那契数列
 * @Date: Created in 22:29 - 2021/4/6
 */
public class ClimbTheStairs {
    public static int total;

    /**
     *
     * 递归
     * @param n
     * @return
     */
    public static int fun(int n ){
        if(n == 1 || n== 2 || n == 3){
            total = n;
        }else {
            total = fun(n-1)+fun(n-2)+fun(n-3);
        }
        return total;
    }

    public static int mu3(int n){
        return (n==1 || n == 2) ? n : mu3(n-1)+mu3(n-2);
    }

    public static void main(String[] args) {
         int n  =2;
        int fun = fun(n);
        int i = mu3(10);
        System.out.println(i);
        System.out.println(fun);
    }

}
