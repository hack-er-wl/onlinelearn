package org.example.service.impl;

import org.example.entity.Course;
import org.springframework.stereotype.Service;

@Service
public interface PostServiceImpl {
    int postCourse(Course course);
}
