package org.example.entity;

import lombok.Data;

@Data
//填空题
public class Option {
    private String option_id;
    private String test_id;
    private String option_answer;
    private String option_contain;
    public Option(String option_id,String test_id,String option_answer,String option_contain){
        this.option_id = option_id;
        this.test_id = test_id;
        this.option_answer = option_answer;
        this.option_contain = option_contain;
    }
}
