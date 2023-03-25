package org.example.service;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.impl.UpdateServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UpdateService implements UpdateServiceImpl {
    @Resource
    private UserMapper userMapper;
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserPass(User user) {
        return userMapper.updateUserPass(user);
    }
}
