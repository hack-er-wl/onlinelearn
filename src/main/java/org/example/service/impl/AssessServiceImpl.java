package org.example.service.impl;

import org.example.entity.Assess;
import org.example.entity.PointerInfo;
import org.example.entity.Reply;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssessServiceImpl {
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
    PointerInfo queryAssessPoint(PointerInfo pointerInfo);
    PointerInfo queryReplyPoint(PointerInfo pointerInfo);
    int assessPointCancel(PointerInfo pointerInfo);
    int replyPointCancel(PointerInfo pointerInfo);
    User queryUserById(String user_id);
    int updateAssessPointerNum(Assess assess);
    int updateReplyPointerNum(Reply reply);
    Assess queryAssessById(String assess_id);
    Reply queryReplyById(String reply_id);
}
