package com.gyp.springboot;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 10:40 - 2021/2/19
 */
public class LindListTest {

    public static void main(String[] args) {
        LinkedList<Menu> menus = new LinkedList<>();
        Menu menu = new Menu();
        menu.setIcons("1");
        Menu menu1 = new Menu();
        menu1.setIcons("2");
        Menu menu2 = new Menu();
        menu2.setIcons("3");
        menus.add(menu);
        menus.add(menu2);

        System.out.println(menus);
        String s ="sgh";

        int  c = s.codePointAt(0);
        System.out.println(c);

        Stack<Character> stack = new Stack<Character>();
        stack.push('{');
        stack.push('{');
        System.out.println(stack);


        LindListTest lindListTest = new LindListTest();
        int[] customers=new int[]{3};
        int[] grumpy =new int[]{1};
        int x=1;
        int i = lindListTest.maxSatisfied(customers, grumpy, x);
        System.out.println(i);



    }


    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int cc = customers.length;
        int sum = 0 ;
        for(int i = 0 ; i< cc; i++){
            if(grumpy[i] == 0){
                sum += customers[i];
                customers[i] = 0;
            }
        }

        int max = 0;
        int i = 0;
        int maxt=0;
        for( ; i < cc ; i++){
            if(X+i <= cc){
                for(int y= i; y < i+X ;y++){
                    maxt+=customers[y];
                }
                max = Math.max(max,maxt);
                maxt= 0;
            }

        }
        return sum + max;
    }
}
