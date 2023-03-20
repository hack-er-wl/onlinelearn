package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.CClass;
import org.example.entity.CField;
import org.example.entity.Course;

import java.util.List;


@Mapper
public interface CourseMapper {
    List getCourseByStatus(int course_status);
    List getRecommendCourse(int num);
    List getCourseByClassId(String class_id);
    Course getCourseByCourseId(String course_id);
    int insertCourse(Course course);
    int updateCourseSubNum(Course course);
    int insertCourseField(CField cField);
    List queryCourseField();
    int insertCourseClass(CClass cClass);
    List queryCourseClass(String field_id);
}
