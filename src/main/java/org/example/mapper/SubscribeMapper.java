package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.CollectInfo;
import org.example.entity.Order;
import org.example.entity.SubscribeInfo;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    int subscribeCourse(SubscribeInfo subscribeInfo);
    int collectCourse(String user_id,String course_id);
    SubscribeInfo querySubscribeCourse(String user_id, String course_id);
    CollectInfo queryCollectCourse(String user_id, String course_id);
    List queryCollectCourseByUid(String user_id);
    int cancelSubscribeCourse(String user_id,String course_id);
    int cancelCollectCourse(String user_id,String course_id);
    List querySubscriber(String course_id);
    List querySubscribeCourseAll(String user_id);
}
