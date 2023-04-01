package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Avatar;
import org.example.entity.Slider;

import java.util.List;
@Mapper
public interface ResourceMapper {
    int insertSlider(Slider slider);
    int insertAvatar(Avatar avatar);
    List getSlider(int start, int end);
    List getSliderAll();
    List getAvatarAll();
}
