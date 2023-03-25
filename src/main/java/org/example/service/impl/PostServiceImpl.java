package org.example.service.impl;

import org.example.entity.Bar;
import org.example.entity.Chapter;
import org.example.entity.Course;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServiceImpl {
    int postCourse(Course course);
    int postChapter(Chapter chapter);
    int postBar(Bar bar);
}
