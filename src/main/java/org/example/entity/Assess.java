package org.example.entity;

import lombok.Data;

@Data
public class Assess {
    private String assess_id;
    private String course_id;
    private String user_id;
    private String assess_content;
    private float assess_rate;
    private String assess_time;
    private int assess_pointer;
    public Assess(String assess_id,String course_id,String user_id,String assess_content,float assess_rate,String assess_time,int assess_pointer){
        this.assess_id = assess_id;
        this.course_id = course_id;
        this.user_id = user_id;
        this.assess_content = assess_content;
        this.assess_rate = assess_rate;
        this.assess_time = assess_time;
        this.assess_pointer =assess_pointer;
    }
}
