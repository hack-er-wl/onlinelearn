package org.example.service;

import org.example.entity.Assess;
import org.example.entity.PointerInfo;
import org.example.entity.Reply;
import org.example.entity.User;
import org.example.mapper.AssessMapper;
import org.example.mapper.UserMapper;
import org.example.service.impl.AssessServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssessService implements AssessServiceImpl {
    @Resource
    private AssessMapper assessMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public int postAssess(Assess assess) {
        return assessMapper.postAssess(assess);
    }

    @Override
    public int postReply(Reply reply) {
        return assessMapper.postReply(reply);
    }

    @Override
    public int pointAssess(PointerInfo pointerInfo) {
        return assessMapper.pointAssess(pointerInfo);
    }

    @Override
    public int pointReply(PointerInfo pointerInfo) {
        return assessMapper.pointReply(pointerInfo);
    }

    @Override
    public int cancelAssess(String user_id, String assess_id) {
        return assessMapper.cancelAssess(user_id,assess_id);
    }

    @Override
    public int cancelReply(String user_id, String reply_id) {
        return assessMapper.cancelReply(user_id,reply_id);
    }

    @Override
    public int cancelPointAssess(String user_id, String assess_id) {
        return assessMapper.cancelPointAssess(user_id,assess_id);
    }

    @Override
    public int cancelPointReply(String user_id, String reply_id) {
        return assessMapper.cancelPointReply(user_id,reply_id);
    }

    @Override
    public List queryAssess(String course_id) {
        return assessMapper.queryAssess(course_id);
    }

    @Override
    public List queryReply(String assess_id) {
        return assessMapper.queryReply(assess_id);
    }

    @Override
    public PointerInfo queryAssessPoint(PointerInfo pointerInfo) {
        return assessMapper.queryAssessPoint(pointerInfo);
    }

    @Override
    public PointerInfo queryReplyPoint(PointerInfo pointerInfo) {
        return assessMapper.queryReplyPoint(pointerInfo);
    }

    @Override
    public int assessPointCancel(PointerInfo pointerInfo) {
        return assessMapper.assessPointCancel(pointerInfo);
    }

    @Override
    public int replyPointCancel(PointerInfo pointerInfo) {
        return assessMapper.replyPointCancel(pointerInfo);
    }

    @Override
    public User queryUserById(String user_id) {
        return userMapper.getUserById(user_id);
    }

    @Override
    public int updateAssessPointerNum(Assess assess) {
        return assessMapper.updateAssessPointerNum(assess);
    }

    @Override
    public int updateReplyPointerNum(Reply reply) {
        return assessMapper.updateReplyPointerNum(reply);
    }

    @Override
    public Assess queryAssessById(String assess_id) {
        return assessMapper.queryAssessById(assess_id);
    }

    @Override
    public Reply queryReplyById(String reply_id) {
        return assessMapper.queryReplyById(reply_id);
    }
}
