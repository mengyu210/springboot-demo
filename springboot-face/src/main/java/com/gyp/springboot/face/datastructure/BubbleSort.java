package com.gyp.springboot.face.datastructure;

/**
 * @Author: guoyapeng
 * @TODO: 说明 冒泡排序
 * @Date: Created in 22:44 - 2021/3/30
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = new int[]{1,6,7,4,5};
        bubbleSort(a);
    }

    public static void bubbleSort(int[] sort){
        for (int i = 0; i< sort.length ; i++){
            for (int j = 0 ; j< sort.length - 1 - i; j++){
                if(sort[j] >= sort[j+1]){
                    int temp = sort[j+1];
                    sort[j+1] = sort[j];
                    sort[j]= temp;
                }
            }
        }

        for (int s = 0; s < sort.length; s++) {
            System.out.println(sort[s]);
        }

    }
}
