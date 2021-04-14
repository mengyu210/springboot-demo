package com.gyp.springboot.suanfa;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 9:43 - 2020/12/31
 */
public class HunWenShu {
    public boolean isPalindrome(int x) {
        StringBuffer stringBuffer = new StringBuffer(Integer.toString(x));
        return stringBuffer.reverse().toString().equals(Integer.toString(x)) ;

    }

    public static void main(String[] args) {
        HunWenShu hunWenShu = new HunWenShu();
        System.out.println("sa".startsWith("t"));
    }



    public int removeElement(int[] nums, int val) {
        int s =0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != val){
                nums[s] = nums[i];
                s++;
            }
        }
        return s;
    }

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        if(strs.length == 1){
            return strs[0];
        }
        for (int i = 0; i < strs.length; i++) {
          //  strs[i].
        }
        return "";
    }

    public int searchInsert(int[] nums, int target) {
        int s =0;
        if(target < nums[0]){
            return 0;
        }
        if(target > nums[nums.length-1]){
            return nums.length;
        }
        for(int i =0; i< nums.length ; i++){
            if(nums[i] == target){
                s= i;
                break;
            }
            if(target <= nums[i] ){
                s= i;
                break;

            }
        }
        return s;
    }
}
