package org.example.controller;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.*;
import org.example.mapper.NoticeMapper;
import org.example.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private LoginService loginService;
    @Resource
    private PostService postService;
    @Resource
    private CourseService courseService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private MenuService menuService;
    @Resource
    private TestService testService;
    @Resource
    private SliderService sliderService;
    @Resource
    private HttpServletResponse response;
    private Result result;
    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password) {
        User user = loginService.login(email);
        if(user.getUser_pass().equals(password)) {
            if(user.getUser_role() == 0){
                //获取token
                Map<String, String> payload = new HashMap<>();
                payload.put("userid", user.getUser_id());
                String token = Utils.getToken(payload);
                response.addHeader("token", token);
                result =  new Result(user,"登录成功",200);
            }else{
                result =  new Result("","无权限访问！",404);
            }
        }else{
            result = new Result("","邮箱或密码错误",404);
        }
        return result;
    }
    @RequestMapping("/query/user")
    @ResponseBody
    public Result getAllUser() {
        List<User> list = loginService.getUserAll();
        result =  list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    @RequestMapping("/query/teacher")
    @ResponseBody
    public Result getAllTeacher() {
        List<Teacher> list = loginService.getTeacherAll();
        result =  list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    @RequestMapping("/post/cfield")
    @ResponseBody
    public Result insertCField(@RequestParam(value = "fieldname") String field_name) {
        CField cField = new CField(Utils.getId(),field_name);
        int res = courseService.insertCourseField(cField);
        result =  res == 1 ? new Result(cField,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    @RequestMapping("/query/cfield")
    @ResponseBody
    public Result getCField() {
        List list = courseService.queryCourseField();
        result =  list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    @RequestMapping("/query/notice")
    @ResponseBody
    public Result getNotice() {
        List list = noticeService.queryAllNotice();
        result =  list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    @RequestMapping("/post/cclass")
    @ResponseBody
    public Result insertCClass(
            @RequestParam(value = "fieldid") String field_id,
            @RequestParam(value = "classname") String class_name) {
        CClass cClass = new CClass(Utils.getId(),field_id,class_name,1);
        int res = courseService.insertCourseClass(cClass);
        result =  res == 1 ? new Result(cClass,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    @RequestMapping("/query/cclass")
    @ResponseBody
    public Result getCClass() {
        List<CClass> list = courseService.queryCourseClassAll();
        result =  list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    @RequestMapping("/query/course")
    @ResponseBody
    public Result getCourse() {
        List<Course> list = courseService.queryCourseAll();
        result =  list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //插入轮播图
    @RequestMapping("/insert/slider")
    @ResponseBody
    public Result getSlider(
            @RequestParam(value = "sliderid") String slider_id,
            @RequestParam(value = "sliderurl") String slider_url){
        Slider slider = new Slider(slider_id,slider_url);
        int res = sliderService.insertSlider(slider);
        return res == 1 ? new Result(slider,"操作成功",200):new Result("","操作失败",404);
    }
    //插入轮播图
    @RequestMapping("/query/slider")
    @ResponseBody
    public Result getSliderAll(){
        List<Slider> list = sliderService.getSliderAll();
        return list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",404);
    }
    //发布讲师守则
    @RequestMapping("/post/rule")
    @ResponseBody
    public Result postRule(@RequestParam(value = "rulecontent") String rule_content) {
        Rule rule = new Rule(Utils.getId(),rule_content);
        int res = postService.insertRule(rule);
        result = res != 0 ? new Result(rule,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询菜单
    @RequestMapping("/query/menu")
    @ResponseBody
    public Result queryMenu() {
        List<Menu> list = menuService.getMenuAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询菜单
    @RequestMapping("/query/test")
    @ResponseBody
    public Result queryTest() {
        List<Test> list = testService.queryTestAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
}
