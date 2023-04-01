package org.example.service;

import org.example.entity.CollectInfo;
import org.example.entity.Order;
import org.example.entity.SubscribeInfo;
import org.example.mapper.SubscribeMapper;
import org.example.service.impl.SubscribeServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubscribeService implements SubscribeServiceImpl {
    @Resource
    private SubscribeMapper subscribeMapper;
    @Override
    public int subscribeCourse(String user_id, String course_id, String order_id) {
        return subscribeMapper.subscribeCourse(user_id,course_id,order_id);
    }

    @Override
    public int collectCourse(String user_id, String course_id) {
        return subscribeMapper.collectCourse(user_id,course_id);
    }

    @Override
    public SubscribeInfo querySubscribeCourse(String user_id, String course_id) {
        return subscribeMapper.querySubscribeCourse(user_id,course_id);
    }

    @Override
    public CollectInfo queryCollectCourse(String user_id, String course_id) {
        return subscribeMapper.queryCollectCourse(user_id,course_id);
    }

    @Override
    public List queryCollectCourseByUid(String user_id) {
        return subscribeMapper.queryCollectCourseByUid(user_id);
    }

    @Override
    public int cancelSubscribeCourse(String user_id, String course_id) {
        return subscribeMapper.cancelSubscribeCourse(user_id,course_id);
    }

    @Override
    public int cancelCollectCourse(String user_id, String course_id) {
        return subscribeMapper.cancelCollectCourse(user_id,course_id);
    }

    @Override
    public List querySubscriber(String course_id) {
        return subscribeMapper.querySubscriber(course_id);
    }

    @Override
    public List querySubscribeCourseAll(String user_id) {
        return subscribeMapper.querySubscribeCourseAll(user_id);
    }
}
