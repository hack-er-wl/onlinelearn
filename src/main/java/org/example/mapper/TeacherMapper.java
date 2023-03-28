package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Rule;
import org.example.entity.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper {
    int insertTeacher(Teacher teacher);
    Teacher checkTeacher(String user_id);
    List queryCourse(String teach_id);
    Teacher getTeacherByCourseId(String course_id);
    int insertRule(Rule rule);
    List getRule();
}
