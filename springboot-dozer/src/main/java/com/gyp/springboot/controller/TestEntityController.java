package com.gyp.springboot.controller;

/*
 * @Author: Administrator
 * @Description : TODO
 * @Date : Create in 15:28 2020/7/28
 * @Version : 1.0
 */

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MapperModelContext;
import com.gyp.springboot.controller.vo.UserVO;
import com.gyp.springboot.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guoyapeng
 */
@Slf4j
@RestController
@RequestMapping(value = "/test")
@Api("测试实体类转换")
public class TestEntityController {

    @Autowired
    private Mapper mapper;

    @ApiOperation(value = "转换类型")
    @PostMapping(value = "change")
    public User toUser(@RequestBody UserVO userVO){
        User map = mapper.map(userVO, User.class);

        return map;
    }

    @ApiOperation(value = "转换类型UserVO")
    @PostMapping(value = "changeVO")
    public UserVO toUserVO(@RequestBody User user){
        UserVO map = mapper.map(user, UserVO.class);
        return map;
    }
}
