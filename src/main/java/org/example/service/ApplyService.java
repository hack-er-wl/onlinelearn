package org.example.service;

import org.example.entity.Teacher;
import org.example.mapper.TeacherMapper;
import org.example.service.impl.ApplyServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
/*申请服务*/
public class ApplyService implements ApplyServiceImpl {
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public int applyForTeacher(Teacher teacher) {
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Teacher checkTeacher(String user_id) {
        return teacherMapper.checkTeacher(user_id);
    }
}
