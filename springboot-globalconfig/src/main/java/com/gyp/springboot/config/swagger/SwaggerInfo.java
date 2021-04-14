package com.gyp.springboot.config.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: GuoYPeng
 * @TODO: 说明
 * @Date: Created in 17:20 - 2020/9/23
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 分组信息
     */
    private String groupName;

    /**
     * 版本
     */
    private String version;

    /**
     * 扫描的包路径
     */
    private String basePackage;

    /**
     * 接口作者
     */
    private String author;
}
