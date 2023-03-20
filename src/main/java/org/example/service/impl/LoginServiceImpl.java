package org.example.service.impl;

import org.springframework.stereotype.Service;

@Service
public interface LoginServiceImpl {
    Object login(String email);
}
