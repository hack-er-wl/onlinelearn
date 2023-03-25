package org.example.service.impl;

import org.example.entity.CollectInfo;
import org.example.entity.Order;
import org.example.entity.SubscribeInfo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SubscribeServiceImpl {
    int subscribeCourse(String user_id,String course_id,String order_id);
    int collectCourse(String user_id,String course_id);
    int insertOrder(Order order);
    int cancelOrder(String order_id);
    List queryOrder(String user_id);
    SubscribeInfo querySubscribeCourse(String user_id, String course_id);
    CollectInfo queryCollectCourse(String user_id, String course_id);
    List queryCollectCourseByUid(String user_id);
    int cancelSubscribeCourse(String user_id,String course_id);
    int cancelCollectCourse(String user_id,String course_id);
}
