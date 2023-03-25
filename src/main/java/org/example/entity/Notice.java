package org.example.entity;

import lombok.Data;

@Data
public class Notice {
    private String notice_id;
    private String notice_content;
    private String notice_time;
    private String from_user;
    private String to_user;
    public Notice(String notice_id,String notice_content,String notice_time,String from_user,String to_user){
        this.notice_id = notice_id;
        this.notice_content = notice_content;
        this.notice_time = notice_time;
        this.from_user = from_user;
        this.to_user = to_user;
    }
}
