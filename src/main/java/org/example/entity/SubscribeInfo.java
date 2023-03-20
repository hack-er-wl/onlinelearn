package org.example.entity;

import lombok.Data;

@Data
public class SubscribeInfo {
    private String user_id;
    private String course_id;
    private String order_id;
    public SubscribeInfo(String user_id,String course_id,String order_id){
        this.user_id = user_id;
        this.course_id = course_id;
        this.order_id = order_id;
    }
}
