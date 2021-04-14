package com.gyp.springboot.algorithm.sort;

import java.util.*;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 16:29 - 2021/3/5
 */
public class BubbleSort {

    public static void main(String[] args) {
       int[] arr =  new int[]{2,5,7,3,9};
       bubbleSort(arr);
        for (int i:
             arr) {
            System.out.println(i);
        }

        int x = 6 >> 1 ;
        System.out.println(x);
        ArrayList<Object> objects = new ArrayList<>();
        System.out.println(objects.size());



    }

    /**
     * 从小到大排序
     * @param arr 数组
     */
    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

}
