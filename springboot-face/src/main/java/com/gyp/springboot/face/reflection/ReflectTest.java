package com.gyp.springboot.face.reflection;

import com.gyp.springboot.face.entity.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: guoyapeng
 * @TODO: 说明 反射
 * @Date: Created in 17:26 - 2021/3/31
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        Class<User> aClass = (Class<User>) Class.forName("com.gyp.springboot.face.entity.User");
        Method isEmpty = aClass.getMethod("toString");
        Constructor<?> constructor = aClass.getConstructor(String.class,Integer.class);
        constructor.setAccessible(true);
        Object o = constructor.newInstance("xxy",25);
        Object invoke = isEmpty.invoke(o);
        System.out.println(invoke);
    }
}
