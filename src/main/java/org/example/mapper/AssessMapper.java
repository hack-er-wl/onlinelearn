package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Assess;
import org.example.entity.PointerInfo;
import org.example.entity.Reply;

import java.util.List;

@Mapper
public interface AssessMapper {
    int postAssess(Assess assess);
    int postReply(Reply reply);
    int pointAssess(PointerInfo pointerInfo);
    int pointReply(PointerInfo pointerInfo);
    int cancelAssess(String user_id,String assess_id);
    int cancelReply(String user_id,String reply_id);
    int cancelPointAssess(String user_id,String assess_id);
    int cancelPointReply(String user_id,String reply_id);
    List queryAssess(String course_id);
    List queryReply(String assess_id);
    Assess queryAssessById(String assess_id);
    Reply queryReplyById(String reply_id);
    PointerInfo queryAssessPoint(PointerInfo pointerInfo);
    PointerInfo queryReplyPoint(PointerInfo pointerInfo);
    int assessPointCancel(PointerInfo pointerInfo);
    int replyPointCancel(PointerInfo pointerInfo);
    int updateAssessPointerNum(Assess assess);
    int updateReplyPointerNum(Reply reply);
}
