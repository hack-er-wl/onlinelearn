package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.GetNoticeInfo;
import org.example.entity.Notice;

import java.util.List;

@Mapper
public interface NoticeMapper {
    int insertNotice(Notice notice);
    int insertNoticeGet(GetNoticeInfo noticeInfo);
    //根据用户id查询该用户收到的信息
    List queryAcceptMessageById(String tousername);
    //根据用户id查询该用户发送出去的信息
    List querySendMessageById(String fromusername);
    //查询所有的消息
    List queryAllNotice();
    //查询该用户是否收到过该系统通知
    GetNoticeInfo queryGetNoticeById(GetNoticeInfo getNoticeInfo);
}
