package org.example.controller;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.*;
import org.example.service.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    private UpdateService updateService;
    @Resource
    private OrderService orderService;
    @Resource
    private ResourceService resourceService;
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
    public Result getSlider(@RequestParam(value = "sliderurl") String slider_url){
        String slider_id = slider_url.substring(29,39);
        Slider slider = new Slider(slider_id,slider_url);
        int res = resourceService.insertSlider(slider);
        return res == 1 ? new Result(slider,"操作成功",200):new Result("","操作失败",404);
    }
    //插入轮播图
    @RequestMapping("/query/slider")
    @ResponseBody
    public Result getSliderAll(){
        List<Slider> list = resourceService.getSliderAll();
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
    //查询讲师守则
    @RequestMapping("/query/rule")
    @ResponseBody
    public Result queryRules() {
        List<Rule> list = postService.getRule();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询所有菜单
    @RequestMapping("/query/menu")
    @ResponseBody
    public Result queryMenu() {
        List<Menu> list = menuService.getMenuAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //插入菜单
    @RequestMapping("/insert/menu")
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
    //查询所有测试
    @RequestMapping("/query/test")
    @ResponseBody
    public Result queryTest() {
        List<Test> list = testService.queryTestAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询所有选择
    @RequestMapping("/query/choose")
    @ResponseBody
    public Result queryChoose() {
        List<Test> list = testService.queryChooseAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询所有判断
    @RequestMapping("/query/option")
    @ResponseBody
    public Result queryOption() {
        List<Test> list = testService.queryOptionAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询所有判断
    @RequestMapping("/query/chapter")
    @ResponseBody
    public Result queryChapter() {
        List<Test> list = courseService.queryChapterAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询所有小节
    @RequestMapping("/query/bar")
    @ResponseBody
    public Result queryBar() {
        List<Test> list = courseService.queryBarAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询所有订单
    @RequestMapping("/query/order")
    @ResponseBody
    public Result queryOderAll() {
        List<Order> list = orderService.queryOrderAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询所有头像
    @RequestMapping("/query/avatar")
    @ResponseBody
    public Result queryAvatar() {
        List<Test> list = resourceService.getAvatarAll();
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //插入头像
    @RequestMapping("/insert/avatar")
    @ResponseBody
    public Result insertAvatar(@RequestParam(value = "avatarurl") String avatar_url) {
        String avatar_id = avatar_url.substring(29,39);
        Avatar avatar = new Avatar(avatar_id,avatar_url);
        int res = resourceService.insertAvatar(avatar);
        result = res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //审核讲师
    @RequestMapping("/update/teacher/status")
    @ResponseBody
    public Result updateCheckStatus(
            @RequestParam(value = "teachid") String teach_id,
            @RequestParam(value = "code") int check_status) {
        Teacher teacher = updateService.queryTeacherById(teach_id);
        teacher.setCheck_status(check_status);
        int res = updateService.updateTeacher(teacher);
        if(res == 1){
            User user = loginService.getUserById(teacher.getUser_id());
            user.setUser_role(2);
            int res_u = updateService.updateUserRole(user);
            result = res_u == 1 ? new Result(teacher,"审核成功",200):new Result("","审核失败",404);
        }else{
            result = new Result("","操作失败",505);
        }
        return result;
    }
    //审核课程
    @RequestMapping("/update/course/check")
    @ResponseBody
    public Result updateCourseCheck(
            @RequestParam(value = "courseid") String course_id,
            @RequestParam(value = "code") int course_check) {
        Course course = courseService.queryCourseById(course_id);
        course.setCourse_check(course_check);
        int res = updateService.updateCourseCheck(course);
        result =  res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //更新课程状态
    @RequestMapping("/update/course/status")
    @ResponseBody
    public Result updateCourseStatus(
            @RequestParam(value = "courseid") String course_id,
            @RequestParam(value = "code") int course_status) {
        Course course = courseService.queryCourseById(course_id);
        course.setCourse_status(course_status);
        int res = updateService.updateCourseStatus(course);
        result =  res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //更新课程分类状态
    @RequestMapping("/update/class/status")
    @ResponseBody
    public Result updateClassStatus(
            @RequestParam(value = "classid") String class_id,
            @RequestParam(value = "code") int class_status) {
        CClass cClass =courseService.queryClassByClassId(class_id);
        cClass.setClass_status(class_status);
        int res = updateService.updateClassStatus(cClass);
        result =  res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //更新讲师守则
    @RequestMapping("/update/rule")
    @ResponseBody
    public Result updateRule(
            @RequestParam(value = "ruleid") String rule_id,
            @RequestParam(value = "rulecontent") String rule_content) {
        Rule rule = postService.getRuleById(rule_id);
        rule.setRule_content(rule_content);
        int res = updateService.updateRule(rule);
        result =  res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //删除讲师守则
    @RequestMapping("/delete/rule")
    @ResponseBody
    public Result deleteRule(@RequestParam(value = "ruleid") String rule_id) {
        int res = updateService.deleteRule(rule_id);
        result =  res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //上传图片
    @RequestMapping("/upload/img")
    @ResponseBody
    public String uploadImage(MultipartFile file) {
        String path = "D:/projects/java/onlineLearn/src/main/resources/static";//本地资源路径
        String file_name = Utils.getId()+".png";
        try {
            file.transferTo(new File(path, file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:8080/static/" + file_name;//返回路径
    }
}
