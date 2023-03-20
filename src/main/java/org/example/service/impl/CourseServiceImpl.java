package org.example.service.impl;

import org.example.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseServiceImpl {
    List getCourseByStatus(int course_status);
    List getRecommendCourse(int num);
    List getCourseByClassId(String class_id);
    Course getCourseByCourseId(String course_id);
    int updateCourseSubNum(Course course);
    int insertCourseField(CField cField);
    List queryCourseField();
    int insertCourseClass(CClass cClass);
    List queryCourseClass(String field_id);
    Teacher getTeacherByCourseId(String course_id);
    User getUserById(String user_id);
}
