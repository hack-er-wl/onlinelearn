package org.example.service;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
/*注册服务*/
public class RegisterService implements RegisterServiceImpl {
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private UserMapper userMapper;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public Object sendEmail(String email) {
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件发送者
        message.setFrom(from);
        // 设置邮件接收者
        message.setTo(email);
        // 设置邮件的主题
        message.setSubject("注册验证码");
        // 设置邮件的正文
        StringBuilder code = Utils.getCode();
        String text = "您的验证码为：" + code + ",请勿泄露给他人！";
        message.setText(text);
        // 发送邮件
        Result result;
        try {
            javaMailSender.send(message);
            result = new Result(code,"发送成功",200);
        } catch (MailException e) {
            System.out.println(e);
            result = new Result(new StringBuilder(""),"发送失败",404);
        }
        return result;
    }

    //插入用户信息
    @Override
    public Object registUser(User user) {
        return userMapper.insertUser(user);
    }

    //判断用户是否已注册
    @Override
    public boolean checkUser(String email) {
        return userMapper.getUserByEmail(email) != null ? true : false;
    }
}
