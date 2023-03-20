package org.example.entity;

import lombok.Data;

@Data
public class CollectInfo {
    private String user_id;
    private String course_id;
    public CollectInfo(String user_id,String course_id){
        this.user_id = user_id;
        this.course_id = course_id;
    }
}
