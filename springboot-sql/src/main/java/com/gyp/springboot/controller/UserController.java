package com.gyp.springboot.controller;

import com.gyp.springboot.entity.User;
import com.gyp.springboot.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 23:19 - 2020/12/13
 */
@RestController
@RequestMapping(value = "/users")
@Api("用户查询")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/all")
    List<User> findAll(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/save")
    @ApiOperation(value = "添加")
    void AddList(){
        User user = new User(1, "xxy", "xxy", new Integer(15));
        userRepository.save(user);
    }

    void test(){
        Integer a =1555;

        a.intValue();
    }
}
