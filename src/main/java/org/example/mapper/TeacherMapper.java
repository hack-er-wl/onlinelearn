package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Teacher;

@Mapper
public interface TeacherMapper {
    int insertTeacher(Teacher teacher);
    Teacher getTeacherByCourseId(String course_id);
}
