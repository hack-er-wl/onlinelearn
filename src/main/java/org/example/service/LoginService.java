package org.example.service;

import org.example.mapper.UserMapper;
import org.example.service.impl.LoginServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
/*登录服务*/
public class LoginService implements LoginServiceImpl {

    @Resource
    private UserMapper userMapper;
    @Override
    public Object login(String email) {
        return userMapper.getUserByEmail(email);
    }
}
