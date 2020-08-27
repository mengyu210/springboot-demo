package com.gyp.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 17:27 - 2020/8/24
 */
@RestController
public class MailController {
    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/index")
    public void sendMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        //发送者.
        message.setFrom("2352441187@qq.com");
        //接收者.
        message.setTo("1522591571@qq.com");
        //邮件主题.
        message.setSubject("测试邮件（邮件主题）");
        //邮件内容.
        message.setText("这是邮件内容");
        //发送邮件
        javaMailSender.send(message);
    }
}
