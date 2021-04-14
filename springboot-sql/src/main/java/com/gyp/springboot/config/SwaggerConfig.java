package com.gyp.springboot.config;

/*
 * @Author: Administrator
 * @Description : TODO
 * @Date : Create in 15:21 2020/7/28
 * @Version : 1.0
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author guoyapeng
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dozer api")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gyp.springboot.controller"))
                .build();
    }



}
