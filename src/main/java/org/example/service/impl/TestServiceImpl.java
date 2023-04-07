package org.example.service.impl;

import org.example.entity.Choose;
import org.example.entity.Option;
import org.example.entity.ParticipateInfo;
import org.example.entity.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestServiceImpl {
    int postTest(Test test);
    int postOption(Option option);
    int postChoose(Choose choose);
    Test queryTestById(String test_id);
    int updateTest(Test test);
    int participateTest(ParticipateInfo participateInfo);
    List queryTestByCourseId(String course_id);
    List queryTestByTeachId(String teach_id);
    List queryChooseByTestId(String test_id);
    List queryOptionByTestId(String test_id);
    List queryRank(String test_id);
    ParticipateInfo queryParticipate(String user_id,String test_id);
    List queryTestAll();
    List queryChooseAll();
    List queryOptionAll();
}
