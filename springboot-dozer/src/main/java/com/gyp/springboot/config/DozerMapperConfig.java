package com.gyp.springboot.config;

/*
 * @Author: Administrator
 * @Description : TODO  ozer对象关系映射
 * @Date : Create in 17:13 2020/7/28
 * @Version : 1.0
 */

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DozerMapperConfig {

    @Bean
    public Mapper dozerMapper(){
        return  DozerBeanMapperBuilder.buildDefault();
    }
}
