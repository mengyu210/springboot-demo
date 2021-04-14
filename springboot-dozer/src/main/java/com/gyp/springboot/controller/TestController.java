package com.gyp.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 14:14 - 2020/10/23
 */
@RestController
public class TestController {

    @RequestMapping(value = "/xy")
    public String User(){
        return "雪艳";
    }
}
