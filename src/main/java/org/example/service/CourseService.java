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
    public List getCourseByClassId(String class_id) {
        return courseMapper.getCourseByClassId(class_id);
    }
    @Override
    public List getChapterByCourseId(String course_id) {
        return courseMapper.getChapterByCourseId(course_id);
    }

    @Override
    public List getBarByChapterId(String chapter_id) {
        return courseMapper.getBarByChapterId(chapter_id);
    }

    @Override
    public List getRecommendCourse(int num) {
        return courseMapper.getRecommendCourse(num);
    }
    @Override
    public List queryClassByClassName(String class_id) {
        return courseMapper.queryClassByClassName(class_id);
    }

    @Override
    public CClass queryClassByClassId(String class_id) {
        return courseMapper.queryClassByClassId(class_id);
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
    public List queryCourseClassAll() {
        return courseMapper.queryCourseClassAll();
    }

    @Override
    public List queryCourseAll() {
        return courseMapper.queryCourseAll();
    }

    @Override
    public CField queryCourseFieldById(String field_id) {
        return courseMapper.queryCourseFieldById(field_id);
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
    public List queryCClassByStatus(int status) {
        return courseMapper.queryCClassByStatus(status);
    }

    @Override
    public List getClassByClassName(String class_name) {
        return courseMapper.queryClassByClassName(class_name);
    }

    @Override
    public Teacher getTeacherByCourseId(String course_id) {
        return teacherMapper.getTeacherByCourseId(course_id);
    }

    @Override
    public User getUserById(String user_id) {
        return userMapper.getUserById(user_id);
    }

    @Override
    public List queryCourseTeacherPost(String teach_id) {
        return teacherMapper.queryCourse(teach_id);
    }

    @Override
    public List queryChapterAll() {
        return courseMapper.queryChapterAll();
    }

    @Override
    public List queryBarAll() {
        return courseMapper.queryBarAll();
    }

    @Override
    public Course queryCourseById(String course_id) {
        return courseMapper.queryCourseById(course_id);
    }

}
