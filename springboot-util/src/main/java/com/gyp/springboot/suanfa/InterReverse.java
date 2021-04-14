package com.gyp.springboot.suanfa;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 15:20 - 2020/12/11
 */
public class InterReverse {


    public static void main(String[] args) {
        int x = 1534236469 ;
        int reverse = reverse(x);
        int i = reverseStr(x);
        String substring = "16262666".substring(1);
        System.out.println(substring);
        System.out.println(reverse);
        System.out.println(i);
    }

    public static int reverse(int x) {

        int n =0 ;
        while( x != 0){
            n= n*10 + x%10;
            x = x /10;
        }
        return (int)n == n ? n : 0;
    }

    public static int reverseStr(int x){
        String s = Integer.toString(x);
        String reverse = s;
        int flag= 1;
        if(x < 0) {
            reverse = s.substring(1);
            flag = -1;
        }
        try{
            return Integer.valueOf(new StringBuffer(reverse).reverse().toString()) *flag;
        }catch (NumberFormatException e){
            return 0;
        }




    }
}
