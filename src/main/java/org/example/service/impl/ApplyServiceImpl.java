package org.example.service.impl;

import org.example.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface ApplyServiceImpl {
    Object applyForTeacher(Teacher teacher);
}
