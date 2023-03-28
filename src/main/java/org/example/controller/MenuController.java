package org.example.controller;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.Course;
import org.example.entity.Menu;
import org.example.entity.Teacher;
import org.example.service.ApplyService;
import org.example.service.LoginService;
import org.example.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "menu")
public class MenuController {
    @Resource
    private ApplyService applyService;
    @Resource
    private MenuService menuService;
    private Result result;
    @RequestMapping("/query")
    @ResponseBody
    public Result queryMenu(@RequestParam(value = "userid") String user_id) {
        Teacher teacher = applyService.checkTeacher(user_id);
        List<Menu> list = new ArrayList<>();
        list.addAll(menuService.getMenu(0));
        if(teacher != null){
            list.addAll(menuService.getMenu(2));
        }else{
            list.addAll(menuService.getMenu(1));
        }
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    @RequestMapping("/insert")
    @ResponseBody
    public Result insertMenu(
            @RequestParam(value = "menuname") String menu_name,
            @RequestParam(value = "menupath") String menu_path,
            @RequestParam(value = "role") int role) {
        Menu menu = new Menu(Utils.getId(),menu_name,menu_path,role);
        int res = menuService.insertMenu(menu);
        result = res != 0 ? new Result(menu,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
}
