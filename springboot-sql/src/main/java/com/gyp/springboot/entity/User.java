package com.gyp.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 22:40 - 2020/12/13
 */


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private Integer age;

}
