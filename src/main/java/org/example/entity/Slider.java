package org.example.entity;

import lombok.Data;

@Data
public class Slider {
    private String slider_id;
    private String slider_url;
    public Slider(String slider_id,String slider_url){
        this.slider_id = slider_id;
        this.slider_url = slider_url;
    }
}
