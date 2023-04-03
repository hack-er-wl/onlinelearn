package org.example.service;

import org.example.entity.Course;
import org.example.entity.Teacher;
import org.example.entity.User;
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
    public int updateCourseStatus(Course course) {
        return courseMapper.updateCourseStatus(course);
    }

    @Override
    public Teacher queryTeacherById(String teach_id) {
        return teacherMapper.queryTeacherById(teach_id);
    }
}
