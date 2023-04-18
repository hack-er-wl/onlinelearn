package org.example.service;

import org.example.entity.*;
import org.example.mapper.CourseMapper;
import org.example.mapper.TeacherMapper;
import org.example.mapper.UserMapper;
import org.example.service.impl.UpdateServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UpdateService implements UpdateServiceImpl {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int updateUserPass(User user) {
        return userMapper.updateUserPass(user);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public int updateCourseCheck(Course course) {
        return courseMapper.updateCourseCheck(course);
    }

    @Override
    public int updateCourseStatus(Course course) {
        return courseMapper.updateCourseStatus(course);
    }

    @Override
    public int updateClassStatus(CClass cClass) {
        return courseMapper.updateClassStatus(cClass);
    }

    @Override
    public Teacher queryTeacherById(String teach_id) {
        return teacherMapper.queryTeacherById(teach_id);
    }

    @Override
    public int updateUserRole(User user) {
        return userMapper.updateUserRole(user);
    }

    @Override
    public int updateRule(Rule rule) {
        return teacherMapper.updateRule(rule);
    }

    @Override
    public int deleteRule(String rule_id) {
        return teacherMapper.deleteRule(rule_id);
    }

    @Override
    public int updateUserMoney(User user) {
        return userMapper.updateUserMoney(user);
    }

    @Override
    public int updateUserName(User user) {
        return userMapper.updateUserName(user);
    }

    @Override
    public int updateTeacherInfo(Teacher teacher) {
        return teacherMapper.updateTeacherInfo(teacher);
    }
}
