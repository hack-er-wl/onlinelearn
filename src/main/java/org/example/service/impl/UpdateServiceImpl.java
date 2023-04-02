package org.example.service.impl;

import org.example.entity.Teacher;
import org.example.entity.User;
import org.springframework.stereotype.Service;


@Service
public interface UpdateServiceImpl {
    int updateUser(User user);
    int updateUserPass(User user);
    int updateTeacher(Teacher teacher);
    Teacher queryTeacherById(String teach_id);
}
