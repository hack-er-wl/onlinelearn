package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.*;

import java.util.List;


@Mapper
public interface CourseMapper {
    List getCourseByStatus(int course_status);
    List getRecommendCourse(int num);
    List getCourseByClassId(String class_id);
    List getChapterByCourseId(String course_id);
    List getBarByChapterId(String chapter_id);
    CClass queryClassByClassId(String class_id);
    List queryClassByClassName(String class_name);
    Course getCourseByCourseId(String course_id);
    int insertCourse(Course course);
    int insertChapter(Chapter chapter);
    int insertBar(Bar bar);
    int updateCourseSubNum(Course course);
    int insertCourseField(CField cField);
    List queryCourseField();
    List queryCourseClassAll();
    List queryCourseAll();
    CField queryCourseFieldById(String field_id);
    int insertCourseClass(CClass cClass);
    List queryCourseClass(String field_id);
    List queryCClassByStatus(int status);
}
