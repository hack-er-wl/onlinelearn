package org.example.entity;

import lombok.Data;

@Data
public class User {
    private String user_id;
    private String user_name;
    private String e_mail;
    private String user_pass;
    private String user_like;//兴趣
    private String user_add;//地址
    private String user_head;//头像
    private int user_money;//余额
    private int user_role;//角色(0管理员/1普通用户/2讲师)

    public User(String user_id, String user_name, String e_mail, String user_pass, String user_like, String user_add, String user_head,int user_money,int user_role) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.e_mail = e_mail;
        this.user_pass = user_pass;
        this.user_like = user_like;
        this.user_add = user_add;
        this.user_head = user_head;
        this.user_money = user_money;
        this.user_role = user_role;
    }
}
