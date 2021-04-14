package com.gyp.springboot.controller;

/*
 * @Author: Administrator
 * @Description : TODO
 * @Date : Create in 9:20 2020/7/30
 * @Version : 1.0
 */


import com.gyp.springboot.controller.vo.UserVO;
import com.gyp.springboot.entity.User;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoyapeng
 */
@Slf4j
@RestController
@RequestMapping(value = "/orika")
@Api("orika测试实体类转换")
public class TestOrikaController {

    @Autowired
    private MapperFactory mapperFactory;





    @GetMapping(value = "/toUser")
    public User toUser(@RequestBody UserVO userVO){
        User map = mapperFactory.getMapperFacade().map(userVO, User.class);
        return map;

    }
}
