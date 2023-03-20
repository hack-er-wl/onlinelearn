package org.example.entity;

import lombok.Data;

@Data
public class CField {
    private String field_id;
    private String field_name;
    public CField(String field_id,String field_name){
        this.field_id = field_id;
        this.field_name = field_name;
    }
}
