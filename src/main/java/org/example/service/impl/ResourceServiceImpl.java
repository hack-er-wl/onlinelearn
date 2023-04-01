package org.example.service.impl;

import org.example.entity.Avatar;
import org.example.entity.Slider;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ResourceServiceImpl {
    int insertSlider(Slider slider);
    int insertAvatar(Avatar avatar);
    List getSlider(int start, int end);
    List getSliderAll();
    List getAvatarAll();
}
