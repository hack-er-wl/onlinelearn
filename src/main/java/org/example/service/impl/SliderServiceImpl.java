package org.example.service.impl;

import org.example.entity.Slider;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SliderServiceImpl {
    int insertSlider(Slider slider);
    List getSlider(int start, int end);
    List getSliderAll();
}
