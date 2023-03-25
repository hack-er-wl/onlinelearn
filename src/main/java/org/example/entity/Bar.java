package org.example.entity;

import lombok.Data;

@Data
public class Bar {
    private String bar_id;
    private String chapter_id;
    private int bar_order;
    private String bar_name;
    private String bar_url;
    public Bar(String bar_id,String chapter_id,int bar_order,String bar_name,String bar_url){
        this.bar_id = bar_id;
        this.chapter_id = chapter_id;
        this.bar_order = bar_order;
        this.bar_name = bar_name;
        this.bar_url = bar_url;
    }
}
