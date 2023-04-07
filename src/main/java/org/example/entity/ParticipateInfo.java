package org.example.entity;

import lombok.Data;

@Data
public class ParticipateInfo {
    private String user_id;
    private String test_id;
    private int score;
    public ParticipateInfo(String user_id,String test_id,int score){
        this.user_id = user_id;
        this.test_id = test_id;
        this.score = score;
    }
}
