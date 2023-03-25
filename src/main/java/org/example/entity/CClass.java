package org.example.entity;

import lombok.Data;

@Data
public class CClass {
    private String class_id;
    private String field_id;
    private String class_name;
    private int class_status;
    public CClass(String class_id,String field_id,String class_name,int class_status){
        this.class_id = class_id;
        this.field_id = field_id;
        this.class_name = class_name;
        this.class_status = class_status;
    }
}
