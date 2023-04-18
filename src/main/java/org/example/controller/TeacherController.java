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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private PostService postService;
    @Resource
    private CourseService courseService;
    @Resource
    private ApplyService applyService;
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private TestService testService;
    @Resource
    private UpdateService updateService;
    @Resource
    private LoginService loginService;
    private Result result;
    //讲师身份判断
    @RequestMapping("/check")
    @ResponseBody
    public Result checkTeacher(@RequestParam(value = "userid") String user_id) {
        Teacher teacher = applyService.checkTeacher(user_id);
        if(teacher != null){
            User user = courseService.getUserById(teacher.getUser_id());
            Map<Object,Object> map = new HashMap();
            map.put("teach_id",teacher.getTeach_id());
            map.put("user_id",teacher.getUser_id());
            map.put("user_tel",teacher.getUser_tel());
            map.put("user_sex",teacher.getUser_sex());
            map.put("user_age",teacher.getUser_age());
            map.put("teach_field",teacher.getTeach_field());
            map.put("teach_class",teacher.getTeach_class());
            map.put("user_tage",teacher.getUser_tage());
            map.put("user_brief",teacher.getUser_brief());
            map.put("user_name",user.getUser_name());
            map.put("e_mail",user.getE_mail());
            map.put("user_head",user.getUser_head());
            result = map != null ? new Result(map,"操作成功",200):new Result("","操作失败",500);
        }else{
            result = new Result("","你不是讲师，请先申请讲师！",200);
        }
        return result;
    }
    //更新呢讲师信息
    @RequestMapping("/update")
    @ResponseBody
    public Result updateTeacherInfo(
            @RequestParam(value = "teachid") String teach_id,
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "username") String user_name,
            @RequestParam(value = "usertel") String user_tel,
            @RequestParam(value = "teachfield") String teach_field,
            @RequestParam(value = "teachclass") String teach_class,
            @RequestParam(value = "usersex") int user_sex,
            @RequestParam(value = "userage") int user_age,
            @RequestParam(value = "usertage") int user_tage,
            @RequestParam(value = "userbrief") String user_brief) {
        Teacher teacher = updateService.queryTeacherById(teach_id);
        User user = loginService.getUserById(user_id);
        teacher.setUser_tel(user_tel);
        teacher.setTeach_field(teach_field);
        teacher.setTeach_class(teach_class);
        teacher.setUser_sex(user_sex);
        teacher.setUser_age(user_age);
        teacher.setUser_tage(user_tage);
        teacher.setUser_brief(user_brief);
        user.setUser_name(user_name);
        int res_u = updateService.updateUserName(user);
        if(res_u == 1){
            int res_t = updateService.updateTeacherInfo(teacher);
            result = res_t != 0 ? new Result(res_t,"操作成功",200):new Result("","操作失败",500);
        }else{
            result = new Result("","操作失败",404);
        }
        return result;
    }
    //查询该讲师发布的课程
    @RequestMapping("/query/course")
    @ResponseBody
    public Result queryCourse(@RequestParam(value = "teachid") String teach_id) {
        List<Course> list = courseService.queryCourseTeacherPost(teach_id);
        List<Course> checked = new ArrayList<>();
        List<Course> checking = new ArrayList<>();
        List<Course> uncheck = new ArrayList<>();
        Map<String,List> map = new HashMap<>();
        for (Course course:list) {
            switch (course.getCourse_check()){
                case 0:
                    checking.add(course);break;
                case 1:
                    checked.add(course);break;
                case 2:
                    uncheck.add(course);break;
            }
        }
        map.put("checked",checked);
        map.put("checking",checking);
        map.put("uncheck",uncheck);
        result = map.size() != 0 ? new Result(map,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //查询该讲师发布的测试
    @RequestMapping("/query/test")
    @ResponseBody
    public Result queryTest(@RequestParam(value = "teachid") String teach_id) {
        List<Course> list = testService.queryTestByTeachId(teach_id);
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",500);
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
    //发布课程
    @RequestMapping("/post/course")
    @ResponseBody
    public Result postCourse(
            @RequestParam(value = "classid") String class_id,
            @RequestParam(value = "teachid") String teach_id,
            @RequestParam(value = "coursefee") int course_fee,
            @RequestParam(value = "coursename") String course_name,
            @RequestParam(value = "coursebrief") String course_brief,
            @RequestParam(value = "coursecover") String course_cover) {
        String course_id = course_cover.substring(29,39);
        Course course = new Course(course_id,class_id,teach_id,1,course_fee,course_name,course_brief,0,course_cover,0);
        int res = postService.postCourse(course);
        result = res == 1 ? new Result(course,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //发布测试
    @RequestMapping("/post/test")
    @ResponseBody
    public Result postTest(
            @RequestParam(value = "teachid") String teach_id,
            @RequestParam(value = "courseid") String course_id,
            @RequestParam(value = "testname") String test_name,
            @RequestParam(value = "usetime") int use_time) {
        String test_id = Utils.getId();
        Test test = new Test(test_id,course_id,teach_id,test_name,0,Utils.getTime(),use_time);
        int res = testService.postTest(test);
        result = res == 1 ? new Result(test,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //发布判断题
    @RequestMapping("/post/option")
    @ResponseBody
    public Result postOption(
            @RequestParam(value = "testid") String test_id,
            @RequestParam(value = "opkey") int option_key,
            @RequestParam(value = "opanswer") String option_answer,
            @RequestParam(value = "opcontain") String option_contain) {
        String option_id = Utils.getId();
        Option option = new Option(option_id,test_id,option_key,option_answer,option_contain);
        int res = testService.postOption(option);
        if(res == 1){
            Test test = testService.queryTestById(test_id);
            test.setQues_num(test.getQues_num()+1);
            int t_res = testService.updateTest(test);
            result = t_res == 1 ? new Result(option,"操作成功",200):new Result(option,"更新数据失败",404);
        }else{
            result = new Result("","操作失败",404);
        }
        return result;
    }
    //发布选择题
    @RequestMapping("/post/choose")
    @ResponseBody
    public Result postChoose(
            @RequestParam(value = "testid") String test_id,
            @RequestParam(value = "opkey") int option_key,
            @RequestParam(value = "opanswer") String option_answer,
            @RequestParam(value = "opcontain") String option_contain,
            @RequestParam(value = "optiona") String option_a,
            @RequestParam(value = "optionb") String option_b,
            @RequestParam(value = "optionc") String option_c,
            @RequestParam(value = "optiond") String option_d) {
        String choose_id = Utils.getId();
        Choose choose = new Choose(choose_id,test_id,option_key,option_answer,option_contain,option_a,option_b,option_c,option_d);
        int res = testService.postChoose(choose);
        if(res == 1){
            Test test = testService.queryTestById(test_id);
            test.setQues_num(test.getQues_num()+1);
            int t_res = testService.updateTest(test);
            result = t_res == 1 ? new Result(choose,"操作成功",200):new Result(choose,"更新数据失败",404);
        }else{
            result = new Result("","操作失败",404);
        }
        return result;
    }
    //发布课程章节
    @RequestMapping("/post/course/chapter")
    @ResponseBody
    public Result postCourseChapter(
            @RequestParam(value = "courseid") String course_id,
            @RequestParam(value = "chapterorder") int chapter_order,
            @RequestParam(value = "chaptername") String chapter_name) {
        String chapter_id = Utils.getId();
        Chapter chapter = new Chapter(chapter_id,course_id,chapter_order,chapter_name);
        int res = postService.postChapter(chapter);
        result = res == 1 ? new Result(chapter,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //发布课程小节
    @RequestMapping("/post/course/bar")
    @ResponseBody
    public Result postCourseBar(
            @RequestParam(value = "chapterid") String chapter_id,
            @RequestParam(value = "barorder") int bar_order,
            @RequestParam(value = "barname") String bar_name,
            @RequestParam(value = "barurl") String bar_url) {
        String bar_id = bar_url.substring(29,39);
        Bar bar = new Bar(bar_id,chapter_id,bar_order,bar_name,bar_url);
        int res = postService.postBar(bar);
        result = res == 1 ? new Result(bar,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //查询该课程的订阅者
    @RequestMapping("/query/subscriber")
    @ResponseBody
    public Result postSubscriber(@RequestParam(value = "courseid") String course_id) {
        List<User> list = subscribeService.querySubscriber(course_id);
        List<Map> res = new ArrayList<>();
        for(User user:list){
            Map<String,Object> map = new HashMap<>();
            map.put("user_name",user.getUser_name());
            map.put("user_like",user.getUser_like());
            map.put("user_head",user.getUser_head());
            res.add(map);
        }
        result = new Result(res,"操作成功",200);
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
    //上传视频
    @RequestMapping("/upload/video")
    @ResponseBody
    public String uploadVideo(MultipartFile file) {
        String path = "D:/projects/java/onlineLearn/src/main/resources/static";//本地资源路径
        String file_name = Utils.getId()+".mp4";
        try {
            file.transferTo(new File(path, file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:8080/static/" + file_name;//返回路径
    }
}
