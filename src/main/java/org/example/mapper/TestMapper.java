package org.example.mapper;

import org.example.entity.Test;

import java.util.List;

public interface TestMapper {
    int insertTest(Test test);
    List queryTestByCourseId(String course_id);
}
