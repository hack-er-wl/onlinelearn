package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Teacher;

@Mapper
public interface TeacherMapper {
    int insertTeacher(Teacher teacher);
    Teacher checkTeacher(String user_id);
    Teacher getTeacherByCourseId(String course_id);
}
