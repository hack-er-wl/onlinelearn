package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Test;

import java.util.List;
@Mapper
public interface TestMapper {
    int insertTest(Test test);
    List queryTestByCourseId(String course_id);
}
