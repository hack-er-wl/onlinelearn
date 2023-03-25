package org.example.service.impl;

import org.example.entity.Teacher;
import org.springframework.stereotype.Service;

@Service
public interface ApplyServiceImpl {
    int applyForTeacher(Teacher teacher);
    Teacher checkTeacher(String user_id);
}
