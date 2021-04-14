package com.gyp.springboot.config;


import com.gyp.springboot.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: guoyapeng
 * @TODO:  官方推荐 implements WebMvcConfigurer
 * @Date: Created in 16:46 - 2020/9/1
 */
//@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {

    private static List<String> EXCLUDE_PATH = Arrays.asList("/","/login","/js/**","/css/**","/images/**");


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/**").addResourceLocations("classpath:/my/","classpath:/static/");

    }

    /**
     * addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用户排除拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);

    }
}
