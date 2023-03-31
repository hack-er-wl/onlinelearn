package org.example.service;

import org.example.entity.Choose;
import org.example.entity.Option;
import org.example.entity.Test;
import org.example.mapper.TestMapper;
import org.example.service.impl.TestServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TestService implements TestServiceImpl {
    @Resource
    private TestMapper testMapper;
    @Override
    public int postTest(Test test) {
        return testMapper.insertTest(test);
    }

    @Override
    public int postOption(Option option) {
        return testMapper.insertOption(option);
    }

    @Override
    public int postChoose(Choose choose) {
        return testMapper.insertChoose(choose);
    }

    @Override
    public Test queryTestById(String test_id) {
        return testMapper.queryTestById(test_id);
    }

    @Override
    public int updateTest(Test test) {
        return testMapper.updateTest(test);
    }

    @Override
    public List queryTestByCourseId(String course_id) {
        return testMapper.queryTestByCourseId(course_id);
    }

    @Override
    public List queryChooseByTestId(String test_id) {
        return testMapper.queryChooseByTestId(test_id);
    }

    @Override
    public List queryOptionByTestId(String test_id) {
        return testMapper.queryOptionByTestId(test_id);
    }

    @Override
    public List queryTestAll() {
        return testMapper.queryTestAll();
    }

    @Override
    public List queryChooseAll() {
        return testMapper.queryChooseAll();
    }

    @Override
    public List queryOptionAll() {
        return testMapper.queryOptionAll();
    }
}
