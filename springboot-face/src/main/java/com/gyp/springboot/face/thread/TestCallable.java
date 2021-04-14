package com.gyp.springboot.face.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 23:22 - 2021/4/1
 */
public class TestCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "xxy";
    }

    public static void main(String[] args) {
        TestCallable testCallable = new TestCallable();

    }
}
