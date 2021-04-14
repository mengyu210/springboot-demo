package com.gyp.springboot.face.datastructure;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 23:57 - 2021/4/1
 */
public class Linked<T> {
    private class Node{
        private T t;
        private Node next;
        public Node(T t,Node next){
            this.t = t ;
            this.next=next;
        }

        public Node(T t){
            this.t= t;
        }
    }

    private Node head;
    private int size;
    public Linked(){
        this.head=null;
        this.size= 0;
    }

    public void addFirst(T t){
        Node node = new Node(t);
        node.next = this.head;
        this.head = node;
    }

    public static void main(String[] args) {
        Linked<String> stringLinked = new Linked<>();
        stringLinked.addFirst("yy");
        System.out.println(stringLinked.head.next);
    }



}
