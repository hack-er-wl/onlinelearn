package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Rule;
import org.example.entity.Teacher;

import java.util.List;

@Mapper
public interface TeacherMapper {
    int insertTeacher(Teacher teacher);
    int updateTeacher(Teacher teacher);
    int updateTeacherInfo(Teacher teacher);
    Teacher checkTeacher(String user_id);
    Teacher queryTeacherById(String teach_id);
    List queryCourse(String teach_id);
    Teacher getTeacherByCourseId(String course_id);
    int insertRule(Rule rule);
    int updateRule(Rule rule);
    int deleteRule(String rule_id);
    Rule getRuleById(String rule_id);
    List getRule();
}
