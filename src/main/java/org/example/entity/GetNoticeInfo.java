package org.example.entity;

import lombok.Data;

@Data
public class GetNoticeInfo {
    private String user_id;
    private String notice_id;
    public GetNoticeInfo(String user_id,String notice_id){
        this.user_id = user_id;
        this.notice_id = notice_id;

    }
}
