package org.example.service.impl;

import org.example.entity.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestServiceImpl {
    int postTest(Test test);
    List queryTestByCourseId(String course_id);
}
