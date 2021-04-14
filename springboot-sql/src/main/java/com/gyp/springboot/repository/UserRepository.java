package com.gyp.springboot.repository;

import com.gyp.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 23:15 - 2020/12/13
 */
public interface UserRepository extends JpaRepository<User,Long> {
    
}
