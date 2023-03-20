package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Assess;
import org.example.entity.Reply;

import java.util.List;

@Mapper
public interface AssessMapper {
    int postAssess(Assess assess);
    int postReply(Reply reply);
    int pointAssess(String user_id, String assess_id);
    int pointReply(String user_id,String reply_id);
    int cancelAssess(String user_id,String assess_id);
    int cancelReply(String user_id,String reply_id);
    int cancelPointAssess(String user_id,String assess_id);
    int cancelPointReply(String user_id,String reply_id);
    List queryAssess(String course_id);
    List queryReply(String assess_id);
    Object queryAssessPoint(String user_id,String assess_id);
    Object queryReplyPoint(String user_id,String reply_id);
}
