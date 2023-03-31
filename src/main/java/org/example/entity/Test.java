package org.example.entity;

import lombok.Data;

@Data
public class Test {
    private String test_id;
    private String course_id;
    private String teach_id;
    private String test_name;
    private int ques_num;
    private String test_time;//发布时间
    private int use_time;//考试用时(分钟)
    public Test(String test_id,String course_id,String teach_id,String test_name,int ques_num,String test_time,int use_time){
        this.test_id = test_id;
        this.course_id = course_id;
        this.teach_id = teach_id;
        this.test_name = test_name;
        this.ques_num = ques_num;
        this.test_time = test_time;
        this.use_time = use_time;
    }
}
