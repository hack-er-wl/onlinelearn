package org.example.service.impl;

import org.example.entity.*;
import org.springframework.stereotype.Service;


@Service
public interface UpdateServiceImpl {
    int updateUser(User user);
    int updateUserPass(User user);
    int updateTeacher(Teacher teacher);
    int updateCourseCheck(Course course);
    int updateCourseStatus(Course course);
    int updateClassStatus(CClass cClass);
    Teacher queryTeacherById(String teach_id);
    int updateUserRole(User user);
    int updateRule(Rule rule);
    int deleteRule(String rule_id);
    int updateUserMoney(User user);
    int updateUserName(User user);
    int updateTeacherInfo(Teacher teacher);
}
