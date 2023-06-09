package org.example.entity;

import lombok.Data;

@Data
public class Course {
    private String course_id;
    private String class_id;
    private String teach_id;
    private int course_status;
    private int course_fee;
    private String course_name;
    private String course_brief;
    private int subscribe_num;
    private String course_cover;
    private int course_check;//课程审核状态(0审核中,1通过，2未通过)
    public Course(String course_id,String class_id,String teach_id,int course_status,int course_fee,String course_name,String course_brief,int subscribe_num,String course_cover,int course_check){
        this.course_id = course_id;
        this.class_id = class_id;
        this.teach_id = teach_id;
        this.course_status = course_status;
        this.course_fee = course_fee;
        this.course_name = course_name;
        this.course_brief = course_brief;
        this.subscribe_num = subscribe_num;
        this.course_cover = course_cover;
        this.course_check = course_check;
    }
}
