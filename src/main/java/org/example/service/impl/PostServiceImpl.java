package org.example.service.impl;

import org.example.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServiceImpl {
    int postCourse(Course course);
    int postChapter(Chapter chapter);
    int postBar(Bar bar);
    int insertRule(Rule rule);
    List getRule();
}
