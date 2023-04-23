package org.example.service;

import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.service.impl.LoginServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
/*登录服务*/
public class LoginService implements LoginServiceImpl {

    @Resource
    private UserMapper userMapper;
    @Override
    public User login(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserById(String user_id) {
        return userMapper.getUserById(user_id);
    }

    @Override
    public List getUserByLike(User user) {
        return userMapper.getUserByLike(user);
    }

    @Override
    public List getUserAll() {
        return userMapper.getUserAll();
    }

    @Override
    public List getTeacherAll() {
        return userMapper.getTeacherAll();
    }
}
