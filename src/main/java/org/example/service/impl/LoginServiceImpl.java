package org.example.service.impl;

import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginServiceImpl {
    User login(String email);
    User getUserById(String user_id);
    List getUserAll();
    List getTeacherAll();
}
