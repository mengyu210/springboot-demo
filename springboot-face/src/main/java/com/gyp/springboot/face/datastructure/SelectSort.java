package com.gyp.springboot.face.datastructure;

/**
 * @Author: guoyapeng
 * @TODO: 说明 选择排序
 * @Date: Created in 22:21 - 2021/4/4
 */
public class SelectSort {

    /**
     * 选择排序的流程 就是 一层循环 与第二层循环进行比较 的进行大小比较
     * 直接选择排序同样是两个嵌套的for循环，每一轮将第一个索引位置的值依次与后面的比较若比后面位置的大则交换位置。里面一层循环控制第一个索引的值依次与后面比较并判断是否交换位置，保证每轮比较将最小的值交换到最前面。外面一层循环控制比较的轮次没比较一轮索引位置前移动一位。
     * @param array
     */
    public static void selectSort(int[] array){
        for (int i = 0; i < array.length -1; i++) {
            for (int j = i+1; j < array.length ; j++) {
                if(array[i] > array[j]){
                    int temp = array[i];
                    array[i]= array[j];
                    array[j] = temp;
                }
            }
        }

        for (int i:
             array) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{5,7,3,6,9};
        selectSort(a);

    }

}
