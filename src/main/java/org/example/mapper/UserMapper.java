package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Slider;
import org.example.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByEmail(String email);
    User getUserById(String user_id);
    List getUserAll();
    List getTeacherAll();
    int insertUser(User user);
    int insertSlider(Slider slider);
    List getSlider(int start, int end);
    List getSliderAll();
    int updateUser(User user);
    int updateUserPass(User user);
}
