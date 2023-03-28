package org.example.entity;

import lombok.Data;

@Data
public class Rule {
    private String rule_id;
    private String rule_content;
    public Rule(String rule_id,String rule_content){
        this.rule_id = rule_id;
        this.rule_content = rule_content;
    }
}
