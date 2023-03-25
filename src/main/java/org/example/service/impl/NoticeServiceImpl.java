package org.example.service.impl;

import org.example.entity.GetNoticeInfo;
import org.example.entity.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeServiceImpl {
    int postNotice(Notice notice);
    int noticeGet(GetNoticeInfo noticeInfo);
    List queryAcceptMessageById(String tousername);
    //根据用户id查询该用户发送出去的信息
    List querySendMessageById(String fromusername);
    //查询所有通知
    List queryAllNotice();
    //查询系统通知
    List querySystemNotice();
    //查询该用户是否收到过该系统通知
    GetNoticeInfo queryGetNoticeById(GetNoticeInfo getNoticeInfo);
}
