package org.example.service;

import org.example.entity.Slider;
import org.example.mapper.UserMapper;
import org.example.service.impl.SliderServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SliderService implements SliderServiceImpl {

    @Resource
    private UserMapper userMapper;
    @Override
    public int insertSlider(Slider slider) {
        return userMapper.insertSlider(slider);
    }

    @Override
    public List getSlider(int start, int end) {
        return userMapper.getSlider(start,end);
    }
}
