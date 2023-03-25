package org.example.service;

import org.example.entity.Bar;
import org.example.entity.Chapter;
import org.example.entity.Course;
import org.example.mapper.CourseMapper;
import org.example.mapper.UserMapper;
import org.example.service.impl.PostServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostService implements PostServiceImpl {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public int postCourse(Course course) {
        return courseMapper.insertCourse(course);
    }

    @Override
    public int postChapter(Chapter chapter) {
        return courseMapper.insertChapter(chapter);
    }

    @Override
    public int postBar(Bar bar) {
        return courseMapper.insertBar(bar);
    }
}
