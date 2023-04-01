package org.example.service;

import org.example.entity.Avatar;
import org.example.entity.Slider;
import org.example.mapper.ResourceMapper;
import org.example.mapper.UserMapper;
import org.example.service.impl.ResourceServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResourceService implements ResourceServiceImpl {

    @Resource
    private ResourceMapper resourceMapper;
    @Override
    public int insertSlider(Slider slider) {
        return resourceMapper.insertSlider(slider);
    }

    @Override
    public int insertAvatar(Avatar avatar) {
        return resourceMapper.insertAvatar(avatar);
    }

    @Override
    public List getSlider(int start, int end) {
        return resourceMapper.getSlider(start,end);
    }

    @Override
    public List getSliderAll() {
        return resourceMapper.getSliderAll();
    }

    @Override
    public List getAvatarAll() {
        return resourceMapper.getAvatarAll();
    }
}
