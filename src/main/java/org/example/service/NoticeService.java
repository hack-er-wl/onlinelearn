package org.example.service;

import org.example.entity.GetNoticeInfo;
import org.example.entity.Notice;
import org.example.mapper.NoticeMapper;
import org.example.service.impl.NoticeServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeService implements NoticeServiceImpl {
    @Resource
    private NoticeMapper noticeMapper;
    @Override
    public int postNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public int noticeGet(GetNoticeInfo noticeInfo) {
        return noticeMapper.insertNoticeGet(noticeInfo);
    }

    @Override
    public List queryAcceptMessageById(String tousername) {
        return noticeMapper.queryAcceptMessageById(tousername);
    }

    @Override
    public List querySendMessageById(String fromusername) {
        return noticeMapper.querySendMessageById(fromusername);
    }

    @Override
    public List queryAllNotice() {
        return noticeMapper.queryAllNotice();
    }

    @Override
    public List querySystemNotice() {
        return noticeMapper.querySendMessageById("0000000000");
    }

    @Override
    public GetNoticeInfo queryGetNoticeById(GetNoticeInfo getNoticeInfo) {
        return noticeMapper.queryGetNoticeById(getNoticeInfo);
    }
}
