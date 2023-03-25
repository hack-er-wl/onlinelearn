package org.example.entity;

import lombok.Data;

@Data
public class Chapter {
    private String chapter_id;
    private String course_id;
    private int chapter_order;
    private String chapter_name;
    public Chapter(String chapter_id,String course_id,int chapter_order,String chapter_name){
        this.chapter_id = chapter_id;
        this.course_id = course_id;
        this.chapter_order = chapter_order;
        this.chapter_name = chapter_name;
    }
}
