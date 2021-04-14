package com.gyp.springboot.face.datastructure;

/**
 * @Author: guoyapeng
 * @TODO: 说明 数据结构实现
 * @Date: Created in 21:15 - 2021/3/30
 */
public class Stack<T> {
    // 栈中的数据
    private Object[] stack;
    // 栈中的大小
    private int  size;

    public  Stack(){
        stack = new Object[10];
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0 ;
    }

    /**
     * 返回栈顶元素
     * @return
     */
    public T peek(){
        T t =null;
        if(size >0 ){
            t= (T)stack[size -1];
        }
        return t;
    }

    /**
     * 插入数据
     * @param t
     */
    public void push(T t){
        stack[size]= t;
        size++;
    }

    public T pop(){
        T t= peek();
        if(size >0){
            stack[size-1] = null;
            size-- ;
        }
        return t;
    }


    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        System.out.println(stringStack.isEmpty());
        stringStack.push("666");
        stringStack.push("xxy");
        String pop = stringStack.pop();
        System.out.println(pop);
    }





}
