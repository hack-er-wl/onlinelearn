package org.example.service;

import org.example.entity.Test;
import org.example.mapper.TestMapper;
import org.example.service.impl.TestServiceImpl;

import javax.annotation.Resource;
import java.util.List;

public class TestService implements TestServiceImpl {
    @Resource
    private TestMapper testMapper;
    @Override
    public int postTest(Test test) {
        return testMapper.insertTest(test);
    }

    @Override
    public List queryTestByCourseId(String course_id) {
        return testMapper.queryTestByCourseId(course_id);
    }
}
