package org.example.service.impl;

import org.example.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseServiceImpl {
    List getCourseByStatus(int course_status);
    List getCourseByClassId(String class_id);
    List getChapterByCourseId(String course_id);
    List getBarByChapterId(String chapter_id);
    List getRecommendCourse(int num);
    List queryClassByClassName(String class_id);
    CClass queryClassByClassId(String class_id);
    Course getCourseByCourseId(String course_id);
    int updateCourseSubNum(Course course);
    int insertCourseField(CField cField);
    List queryCourseField();
    List queryCourseClassAll();
    List queryCourseAll();
    CField queryCourseFieldById(String field_id);
    int insertCourseClass(CClass cClass);
    List queryCourseClass(String field_id);
    List queryCClassByStatus(int status);
    List getClassByClassName(String class_name);
    Teacher getTeacherByCourseId(String course_id);
    User getUserById(String user_id);
    List queryCourseTeacherPost(String teach_id);
}
