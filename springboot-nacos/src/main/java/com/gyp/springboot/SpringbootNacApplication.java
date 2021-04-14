package com.gyp.springboot;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 15:11 - 2021/1/8
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example" ,autoRefreshed = true)
public class SpringbootNacApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacApplication.class,args);
    }
}
