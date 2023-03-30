package org.example.entity;

import lombok.Data;

@Data
//选择题
public class Choose {
    private String choose_id;
    private String test_id;
    private int option_key;
    private String option_answer;
    private String option_contain;
    private String option_a;
    private String option_b;
    private String option_c;
    private String option_d;
    public Choose(String choose_id,String test_id,int option_key,String option_answer,String option_contain,String option_a,String option_b,String option_c,String option_d){
        this.choose_id = choose_id;
        this.test_id = test_id;
        this.option_key = option_key;
        this.option_answer = option_answer;
        this.option_contain = option_contain;
        this.option_a= option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
    }
}
