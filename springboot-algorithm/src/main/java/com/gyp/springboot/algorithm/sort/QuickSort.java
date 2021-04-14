package com.gyp.springboot.algorithm.sort;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 10:10 - 2021/3/12
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{8,7,5,9,2};
        quickSort(arr,0, arr.length-1);
        for (int a:
             arr) {
            System.out.println(a);
        }
        String a= "a";



    }

    /**
     * 基本思想
     * 1.选定Pivot中心轴
     * 2.将大于Pivot的数字放在Pivot的右边
     * 3.将小于Pivot的数字放在Pivot的左边
     * 4.分别对左右子序列重复前三步操作
     * @param arr 数组
     * @param low 数组左边
     * @param high 数组右边
     */
    private static void quickSort(int[] arr,int low, int high){
        int left = low,right = high;
        int piv = arr[low];
        if(low >= high){
            return;
        }

        while(left < right){
            while (left < right && arr[right]>= piv){
                right--;
                System.out.println("right--"+right);
            }
            if(left< right){
                arr[left] = arr[right];
                System.out.println("right"+right);

            }

            while(left < right && arr[left] <= piv){
                left++;
                System.out.println("left--"+left);
            }
            if(left < right){
                arr[right] =arr[left];
                System.out.println("left"+left);
            }
            if(left >=right){
                arr[left] = piv;
                System.out.println("end");
            }

        }
        quickSort(arr, low,high-1 );
        quickSort(arr, left+1, high);

    }
}
