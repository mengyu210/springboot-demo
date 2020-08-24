package com.gyp.springboot.config;

/*
 * @Author: Administrator
 * @Description : TODO   Orika 关系对象映射
 * @Date : Create in 9:01 2020/7/30
 * @Version : 1.0
 */


import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaMapperConfig {


    @Bean
    public MapperFactory mapperFactory(){
        return new DefaultMapperFactory.Builder().build();
    }
}
