package com.gyp.springboot.suanfa;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 16:32 - 2021/2/26
 */
public class submissions {

    public static void main(String[] args) {
        int[] a= new int[]{9};
        submissions submissions = new submissions();
        int[] ints = submissions.plusOne(a);
        System.out.println(ints.length);
        BigInteger
    }


    public int[] plusOne(int[] digits) {
        int x = digits.length;
        boolean flag = true;
        if( digits[x-1] < 9){
            digits[x-1] = digits[x-1] + 1;
            return digits;
        }else{
            int[] plus = new int[x+1];
            for( int c= x-1 ; c>=0; c-- ){
                if(digits[c] ==9){
                    digits[c] =0;
                }else{
                    flag = false;
                    digits[c] +=1;
                    break;
                }
            }
            if(flag){
                plus[0] =1;
                for(int i=0; i < x ; i++){
                    plus[i+1] = digits[i];
                }
            }
            return plus;
        }

    }
}
