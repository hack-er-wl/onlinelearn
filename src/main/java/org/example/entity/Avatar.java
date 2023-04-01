package org.example.entity;

import lombok.Data;

@Data
public class Avatar {
    private String avatar_id;
    private String avatar_url;
    public Avatar(String avatar_id,String avatar_url){
        this.avatar_id = avatar_id;
        this.avatar_url = avatar_url;
    }
}
