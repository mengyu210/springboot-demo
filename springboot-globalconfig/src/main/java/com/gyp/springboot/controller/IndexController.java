package com.gyp.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 11:08 - 2020/9/1
 */
@RestController
public class IndexController {

    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);



    @RequestMapping(value = "/name")
    public String  getName(){
        logger.info("kafak");
        return "gyp";
    }
}
