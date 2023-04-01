package org.example.entity;

import lombok.Data;

@Data
//这个对象被点赞评论和点赞回复两个业务共用
public class PointerInfo {
    private String user_id;
    private String point_id;
    public PointerInfo(String user_id,String point_id){
        this.user_id = user_id;
        this.point_id = point_id;
    }
}
