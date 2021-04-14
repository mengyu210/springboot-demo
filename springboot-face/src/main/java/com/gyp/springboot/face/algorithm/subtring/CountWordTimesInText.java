package com.gyp.springboot.face.algorithm.subtring;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 0:03 - 2021/3/31
 */
public class CountWordTimesInText {

    public static void main(String[] args) {

        int i = countWordTimeBySubString("axxygeggxxyhhxxy", "xxy");
        int j = countWordTimeBySubString1("axxygeggxxyhhxxy", "xxy");

        System.out.println(i+j);

    }

    public static int countWordTimeBySubString(String text,String word){
        int count= 0 ;
        if(!isEmpty(text) && !isEmpty(word)){
            String subText = text;
            int index = -1;
            while (subText.length() >= word.length() && (index = subText.indexOf(word)) > -1){
                subText = subText.substring(index + word.length());
                count++;
            }
        }
        return  count;
    }

    private static boolean isEmpty(String str){
        return null == str || str.length() == 0 ;
    }

    public static int countWordTimeBySubString1(String text,String word){
        int count = 0;
        if (!(isEmpty(text) && !(isEmpty(word)))) {
            String text1= text;
            int index = -1;
            if(text.length() >= word.length() && (index = text.indexOf(word)) > -1){
                text1 = text1.substring(index + word.length());
                count++;
            }
        }
        return count;
    }


}
