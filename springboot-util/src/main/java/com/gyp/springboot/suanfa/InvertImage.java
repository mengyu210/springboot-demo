package com.gyp.springboot.suanfa;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 9:51 - 2021/2/24
 */
public class InvertImage {

    public static void main(String[] args) {
        int[][] A = new int[][]{{1,1,0},{1,1,0},{0,0,0}};
        InvertImage invertImage = new InvertImage();
        int[][] ints = invertImage.flipAndInvertImage(A);
        System.out.println(ints);
        char l = 'a';
        char k= 'a';
        System.out.println(l == k);

        String a = "mississippi";
        String s = "sipp";
        int i = invertImage.strStr(a, s);
        System.out.println(i);


    }


    public int[][] flipAndInvertImage(int[][] A) {
        int ly = A.length;
        int lx = A[0].length;
        for(int i=0; i < ly; i++){
            int temp = 0;
            for (int j = 0; j < (lx/2 +1); j++) {
                temp= A[i][j];
               A[i][j] = A[i][lx - j -1];
                A[i][lx - j -1] = temp;
            }
            for (int j = 0; j < lx; j++) {
                A[i][j] = A[i][j] == 1 ? 0 : 1;
            }
        }
        return A;
    }

    public int strStr(String haystack, String needle) {
        boolean flag = false;
        if( null == needle || "".equals(needle)){
            return 0;
        }
        if( needle.length() > haystack.length()){
            return -1;
        }
        char[] chars = haystack.toCharArray();
        char[] chars1 = needle.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int c = 0;
            if(chars[i] == chars1[c] && i +  needle.length() <= haystack.length()){
                for (int j = i; j < i+ chars1.length; j++) {
                     if(chars[j] == chars1[c]){
                         flag = true;
                     }else{
                         flag = false;

                     }
                     c++;
                     if(!flag){
                         break;
                     }

                }
                if(flag){
                    return i;
                }
            }
        }
        return -1;
    }
}
