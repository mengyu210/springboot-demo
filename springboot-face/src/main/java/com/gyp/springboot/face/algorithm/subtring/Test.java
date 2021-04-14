package com.gyp.springboot.face.algorithm.subtring;

import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 0:00 - 2021/3/31
 */
public class Test {

    public static void main(String[] args) {
        String s = "ilikexxy";
        String c = "xxy";
        int i = s.indexOf(c);
        System.out.println(i);

        String st = "1566";
        String s1 = st.replaceAll("[^0-9a-zA-Z]", "");
        StringBuffer stringBuffer = new StringBuffer(s1);
        String s2 = stringBuffer.reverse().toString();
        s1.equals(s2);
        System.out.println(s1.length());

        String v = "A man, a plan, a canal: Panama";
        boolean palindrome = isPalindrome(v);
        System.out.println(palindrome);

        int a = 2^2^1;
        System.out.println(a);
    }


    public static boolean isPalindrome(String s) {
        String s1 = s.replaceAll("[^0-9a-zA-Z]", "");
        System.out.println(s1);
        byte[] bytes = s1.getBytes();
        byte aByte = bytes[0];
        System.out.println(aByte);
        System.out.println(s1.length());
        int i = s1.length();
        int c= 0;
        if(i == 0){
            return true;
        }

        StringBuffer stringBuffer = new StringBuffer(s1);
        String s2 = stringBuffer.reverse().toString();

        System.out.println(s2);
        return s1.toLowerCase().equals(s2.toLowerCase());

    }

    public void reverseString(char[] s) {

        int c = s.length-1;
        int t = 0;
        while(t<= c ){
            int temp = s[t];
            s[t] = s[c];
            s[c] = (char)temp;
            t++;
            c--;
        }

    }
}
