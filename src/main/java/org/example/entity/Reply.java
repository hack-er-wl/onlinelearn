package org.example.entity;

import lombok.Data;

@Data
public class Reply {
    private String reply_id;
    private String user_id;
    private String assess_id;
    private String accept_id;
    private String reply_content;
    private String reply_time;
    private int reply_pointer;
    public Reply(String reply_id,String user_id,String assess_id,String accept_id,String reply_content,String reply_time,int reply_pointer){
        this.reply_id = reply_id;
        this.user_id = user_id;
        this.assess_id = assess_id;
        this.accept_id = accept_id;
        this.reply_content = reply_content;
        this.reply_time = reply_time;
        this.reply_pointer  =reply_pointer;
    }
}
