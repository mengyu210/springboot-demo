package com.gyp.springboot.face.datastructure;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 23:42 - 2021/4/6
 */
class MinStack {

    /** initialize your data structure here. */
    private int[] stack;
    private int size= 0 ;

    public MinStack() {
        stack = new int[10];
    }

    public void push(int val) {
        stack[size] = val;
        size++;

    }

    public void pop() {
        if(size>0){
            stack[size-1] = Integer.parseInt(null);
            size--;
        }

    }

    public int top() {

        return stack[size-1];
    }

    public int getMin() {
        int temp = stack[0];
        for(int c=0; c < size; c++){
            if(stack[c] < temp){
                temp =stack[c];
            }
        }
        return temp;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
