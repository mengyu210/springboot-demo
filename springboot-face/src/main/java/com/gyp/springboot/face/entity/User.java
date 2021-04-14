package com.gyp.springboot.face.entity;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 17:36 - 2021/3/31
 */
public class User {
    private String name ;
    private Integer age;
    private User user;

    public User(){}

    public User(String name,Integer age){
        this.age= age;
        this.name= name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static void main(String[] args) {
        User xxy = new User("xxy", 25);
        User xxy1 = new User("xxy", 25);
        xxy.setUser(xxy1);
        System.out.println(xxy.user);
    }
}
