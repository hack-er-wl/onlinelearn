package org.example.service.impl;

import org.example.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface UpdateServiceImpl {
    int updateUser(User user);
    int updateUserPass(User user);
}
