package org.example.entity;

import lombok.Data;

@Data
public class Teacher {
    private String teach_id;
    private String user_id;
    private String user_tel;
    private int user_sex;
    private int user_age;
    private int user_tage;
    private String user_brief;
    public Teacher(String teach_id,String user_id,String tel,int user_sex,int user_age,int user_tage,String user_brief){
        this.teach_id = teach_id;
        this.user_id = user_id;
        this.user_tel = tel;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_tage = user_tage;
        this.user_brief = user_brief;
    }
}
