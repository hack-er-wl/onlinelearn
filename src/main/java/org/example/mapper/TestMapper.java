package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Choose;
import org.example.entity.Option;
import org.example.entity.Test;

import java.util.List;
@Mapper
public interface TestMapper {
    int insertTest(Test test);
    int insertOption(Option option);
    int insertChoose(Choose choose);
    Test queryTestById(String test_id);
    int updateTest(Test test);
    List queryTestByCourseId(String course_id);
    List queryChooseByTestId(String test_id);
    List queryOptionByTestId(String test_id);
    List queryTestAll();
    List queryChooseAll();
    List queryOptionAll();
}
