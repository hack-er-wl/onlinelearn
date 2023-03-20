package org.example.controller;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.*;
import org.example.service.CourseService;
import org.example.service.LoginService;
import org.example.service.PostService;
import org.example.service.SliderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    private SliderService sliderService;
    private Result result;
    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam(value = "email") String email,
                        @RequestParam(value = "password") String password) {
        User user = (User)loginService.login(email);
        result =  user.getUser_pass().equals(password) ? new Result(user,"登录成功",200):new Result("","邮箱或密码错误",404);
        return result;
    }
    @RequestMapping("/post/course")
    @ResponseBody
    public Result insertCourse(@RequestParam(value = "classid") String class_id,
                                @RequestParam(value = "teachid") String teach_id,
                                @RequestParam(value = "coursefee") int course_fee,
                                @RequestParam(value = "coursename") String course_name,
                                @RequestParam(value = "coursebrief") String course_brief) {
        String course_id = Utils.getId();
        String course_cover = "http://localhost:8080/static/"+course_id+".png";
        Course course = new Course(course_id,class_id,teach_id,0,course_fee,course_name,course_brief,0,course_cover);
        int res = (int)postService.postCourse(course);
        result = res == 1 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
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
    @RequestMapping("/post/cclass")
    @ResponseBody
    public Result insertCClass(@RequestParam(value = "fieldid") String field_id,
                               @RequestParam(value = "classname") String class_name) {
        CClass cClass = new CClass(Utils.getId(),field_id,class_name);
        int res = courseService.insertCourseClass(cClass);
        result =  res == 1 ? new Result(cClass,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //插入轮播图
    @RequestMapping("/insert/slider")
    @ResponseBody
    public Result getSlider(@RequestParam(value = "sliderid") String slider_id,
                            @RequestParam(value = "sliderurl") String slider_url){
        Slider slider = new Slider(slider_id,slider_url);
        int res = sliderService.insertSlider(slider);
        return res == 1 ? new Result(slider,"操作成功",200):new Result("","操作失败",404);
    }
}