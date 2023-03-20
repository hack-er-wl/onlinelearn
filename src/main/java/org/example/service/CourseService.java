package org.example.service;

import org.example.entity.*;
import org.example.mapper.CourseMapper;
import org.example.mapper.TeacherMapper;
import org.example.mapper.UserMapper;
import org.example.service.impl.CourseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
/*课程服务*/
public class CourseService implements CourseServiceImpl {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List getCourseByStatus(int course_status) {
        return courseMapper.getCourseByStatus(course_status);
    }

    @Override
    public List getRecommendCourse(int num) {
        return courseMapper.getRecommendCourse(num);
    }

    @Override
    public List getCourseByClassId(String class_id) {
        return courseMapper.getCourseByClassId(class_id);
    }

    @Override
    public Course getCourseByCourseId(String course_id) {
        return courseMapper.getCourseByCourseId(course_id);
    }

    @Override
    public int updateCourseSubNum(Course course) {
        return courseMapper.updateCourseSubNum(course);
    }

    @Override
    public int insertCourseField(CField cField) {
        return courseMapper.insertCourseField(cField);
    }

    @Override
    public List queryCourseField() {
        return courseMapper.queryCourseField();
    }

    @Override
    public int insertCourseClass(CClass cClass) {
        return courseMapper.insertCourseClass(cClass);
    }

    @Override
    public List queryCourseClass(String field_id) {
        return courseMapper.queryCourseClass(field_id);
    }

    @Override
    public Teacher getTeacherByCourseId(String course_id) {
        return teacherMapper.getTeacherByCourseId(course_id);
    }

    @Override
    public User getUserById(String user_id) {
        return userMapper.getUserById(user_id);
    }
}
