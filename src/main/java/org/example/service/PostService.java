package org.example.service;

import org.example.entity.Course;
import org.example.mapper.CourseMapper;
import org.example.service.impl.PostServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PostService implements PostServiceImpl {
    @Resource
    private CourseMapper courseMapper;
    @Override
    public int postCourse(Course course) {
        return courseMapper.insertCourse(course);
    }
}
