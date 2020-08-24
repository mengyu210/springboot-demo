package com.gyp.springboot.entity;

/*
 * @Author: Administrator
 * @Description : TODO
 * @Date : Create in 15:35 2020/7/28
 * @Version : 1.0
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String password;
    private Integer age;

}
