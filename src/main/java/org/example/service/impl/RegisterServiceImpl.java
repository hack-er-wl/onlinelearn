package org.example.service.impl;

import org.example.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface RegisterServiceImpl {
    Object sendEmail(String email);
    Object registUser(User user);
    boolean checkUser(String email);
}
