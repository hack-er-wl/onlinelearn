package org.example.entity;

import lombok.Data;

@Data
public class Menu {
    private String menu_id;
    private String menu_name;
    private String menu_path;
    private int role;//角色访问权限(0皆可访问1普通用户可访问2讲师可访问)
    public Menu(String menu_id,String menu_name,String menu_path,int role){
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_path = menu_path;
        this.role = role;
    }
}
