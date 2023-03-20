package org.example.controller;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.*;
import org.example.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private RegisterService registerService;
    private Result result;
    @Resource
    private LoginService loginService;
    @Resource
    private CourseService courseService;
    @Resource
    private ApplyService applyService;
    @Resource
    private SliderService sliderService;
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private AssessService assessService;
    @Resource
    private HttpServletResponse response;

    //发送邮件
    @RequestMapping("/email")
    @ResponseBody
    public Result sendSimpleMail(@RequestParam(value = "email") String email) {
        result = registerService.checkUser(email) ? new Result("","该邮箱号已注册",404) : (Result)registerService.sendEmail(email);
        return result;
    }
    //注册
    @RequestMapping("/regist")
    @ResponseBody
    public Result register(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "code") String code) {
        User user = new User(Utils.getId(),username,email,password,"","","");
        return result.getData().toString().equals(code)?(int)registerService.registUser(user) == 1? new Result("","操作成功",200):new Result("","操作失败",404):new Result("","验证码错误",500);
    }
    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam(value = "email") String email,
                      @RequestParam(value = "password") String password) {
        User user = (User)loginService.login(email);
        boolean bool = user.getUser_pass().equals(password);
        if(bool) {
            //获取token
            Map<String, String> payload = new HashMap<>();
            payload.put("userid", user.getUser_id());
            String token = Utils.getToken(payload);
            response.addHeader("token", token);
            result =  new Result(user,"登录成功",200);
        }else{
            result = new Result("","邮箱或密码错误",404);
        }
        return result;
    }
    //根据课程状态查询
    @RequestMapping("/query/course/bystatus")
    @ResponseBody
    public Result getCourseByStatus(@RequestParam(value = "status") int course_status){
        List list = courseService.getCourseByStatus(course_status);
        result = list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //获取推荐课程
    @RequestMapping("/query/course/recommend")
    @ResponseBody
    public Result getRecommendCourse(){
        List list = courseService.getRecommendCourse(4);
        result = list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //根据课程状态查询
    @RequestMapping("/query/course/bycourseid")
    @ResponseBody
    public Result getCourseById(@RequestParam(value = "courseid") String course_id){
        Course course = courseService.getCourseByCourseId(course_id);
        result = course != null ? new Result(course,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //根据课程类型查询
    @RequestMapping("/query/course/byclassid")
    @ResponseBody
    public Result getCourseByClassId(@RequestParam(value = "classid") String class_id){
        List list  = courseService.getCourseByClassId(class_id);
        result = list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //获取轮播图
    @RequestMapping("/query/slider")
    @ResponseBody
    public Result getSlider(@RequestParam(value = "start") int start,
                            @RequestParam(value = "end") int end){
        Object obj = sliderService.getSlider(start,end);
        result = obj != null ? new Result(obj,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //根据课程id获取教师信息
    @RequestMapping("/query/teacher")
    @ResponseBody
    public Result getTeacher(@RequestParam(value = "courseid") String course_id){
        Teacher teacher = courseService.getTeacherByCourseId(course_id);
        User user = courseService.getUserById(teacher.getUser_id());
        Map<Object,Object> map = new HashMap();
        map.put("teach_id",teacher.getTeach_id());
        map.put("user_id",teacher.getUser_id());
        map.put("user_tel",teacher.getUser_tel());
        map.put("user_sex",teacher.getUser_sex());
        map.put("user_age",teacher.getUser_age());
        map.put("user_tage",teacher.getUser_tage());
        map.put("user_brief",teacher.getUser_brief());
        map.put("user_name",user.getUser_name());
        map.put("e_mail",user.getE_mail());
        map.put("user_head",user.getUser_head());
        result = teacher != null ? new Result(map,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //查询领域
    @RequestMapping("/query/cfield")
    @ResponseBody
    public Result getCField() {
        List list = courseService.queryCourseField();
        result =  list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //查询领域
    @RequestMapping("/query/cclass")
    @ResponseBody
    public Result getCClass(@RequestParam(value = "fieldid") String field_id) {
        List list = courseService.queryCourseClass(field_id);
        result =  list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //订阅课程
    @RequestMapping("/subscribe")
    @ResponseBody
    public Result subscribeCourse(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "courseid") String course_id) {
        //生成订单id
        String order_id = Utils.getId();
        //查询课程
        Course course = courseService.getCourseByCourseId(course_id);
        course.setSubscribe_num(course.getSubscribe_num()+1);
        //生成订单
        Order order = new Order(order_id,course.getCourse_name(),course.getCourse_fee(),Utils.getTime(),null,0);
        //插入订单和订阅信息
        int orderRes = subscribeService.insertOrder(order);
        if(orderRes == 1){
            int subRes = subscribeService.subscribeCourse(user_id,course_id,order_id);
            int subNum = courseService.updateCourseSubNum(course);
            if(subRes == 1 && subNum == 1){
                result = new Result(order,"操作成功",200);
            }else{
                subscribeService.cancelOrder(order_id);//订阅失败删除订单消息
                result = new Result("","操作失败",404);
            }
        }else{
            result = new Result("","订单插入失败",404);
        }
        return result;
    }
    //查询订阅信息
    @RequestMapping("/query/subscribe")
    @ResponseBody
    public Result querySubscribe(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "courseid") String course_id) {
        SubscribeInfo info = subscribeService.querySubscribeCourse(user_id,course_id);
        result = info != null ? new Result(info,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //取消订阅信息
    @RequestMapping("/cancel/subscribe")
    @ResponseBody
    public Result cancelSubscribe(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "courseid") String course_id) {
        String order_id = subscribeService.querySubscribeCourse(user_id,course_id).getOrder_id();
        Course course = courseService.getCourseByCourseId(course_id);
        course.setSubscribe_num(course.getSubscribe_num() - 1);
        int canSubRes = subscribeService.cancelSubscribeCourse(user_id,course_id);
        if(canSubRes == 1){
            int canOrderRes = subscribeService.cancelOrder(order_id);
            int canSubNum = courseService.updateCourseSubNum(course);
            if(canOrderRes == 1 && canSubNum == 1){
                result = new Result(canOrderRes,"操作成功",200);
            }else{
                result = new Result("","操作失败",404);
            }
        }else{
            result = new Result("","删除订单失败",404);
        }
        return result;
    }
    //收藏课程
    @RequestMapping("/collect")
    @ResponseBody
    public Result collectCourse(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "courseid") String course_id) {
        int res = subscribeService.collectCourse(user_id,course_id);
        result = res == 1 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //查询收藏课程
    @RequestMapping("/query/collect")
    @ResponseBody
    public Result queryCollectCourse(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "courseid") String course_id) {
        CollectInfo info = subscribeService.queryCollectCourse(user_id,course_id);
        result = info != null ? new Result(info,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //取消收藏课程
    @RequestMapping("/cancel/collect")
    @ResponseBody
    public Result cancelCollectCourse(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "courseid") String course_id) {
        int res = subscribeService.cancelCollectCourse(user_id,course_id);
        result = res == 1 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //发表评论
    @RequestMapping("/assess")
    @ResponseBody
    public Result postAssess(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "courseid") String course_id,
            @RequestParam(value = "content") String assess_content,
            @RequestParam(value = "rate") float assess_rate) {
        Assess assess = new Assess(Utils.getId(),course_id,user_id,assess_content,assess_rate,Utils.getTime(),0);
        int res = assessService.postAssess(assess);
        result = res == 1 ? new Result(assess,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //查询评论
    @RequestMapping("/query/assess")
    @ResponseBody
    public Result queryAssess(@RequestParam(value = "courseid") String course_id) {
        List<Assess> list = assessService.queryAssess(course_id);
        List<Map> res = new ArrayList<>();
        for(Assess assess:list){
            Map<Object,Object> map = new HashMap();
            User user = assessService.queryUserById(assess.getUser_id());
            map.put("user_name",user.getUser_name());
            map.put("assess_id",assess.getAssess_id());
            map.put("course_id",assess.getCourse_id());
            map.put("user_id",assess.getUser_id());
            map.put("assess_content",assess.getAssess_content());
            map.put("assess_rate",assess.getAssess_rate());
            map.put("assess_time",assess.getAssess_time());
            map.put("assess_pointer",assess.getAssess_pointer());
            map.put("user_head",user.getUser_head());
            res.add(map);
        }
        result = res != null ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //发表回复
    @RequestMapping("/reply")
    @ResponseBody
    public Result postReply(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "assessid") String assess_id,
            @RequestParam(value = "acceptid") String accept_id,
            @RequestParam(value = "content") String reply_content) {
        Reply reply = new Reply(Utils.getId(),user_id,assess_id,accept_id,reply_content,Utils.getTime(),0);
        int res = assessService.postReply(reply);
        result = res == 1 ? new Result(reply,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //查询回复
    @RequestMapping("/query/reply")
    @ResponseBody
    public Result queryReply(@RequestParam(value = "assessid") String assess_id) {
        List<Reply> list = assessService.queryReply(assess_id);
        List<Map> res = new ArrayList<>();
        for(Reply reply:list){
            Map<Object,Object> map = new HashMap();
            User user = assessService.queryUserById(reply.getUser_id());
            User accept = assessService.queryUserById(reply.getAccept_id());
            map.put("user_name",user.getUser_name());
            map.put("reply_id",reply.getReply_id());
            map.put("user_id",reply.getUser_id());
            map.put("assess_id",reply.getAssess_id());
            map.put("reply_content",reply.getReply_content());
            map.put("reply_time",reply.getReply_time());
            map.put("reply_pointer",reply.getReply_pointer());
            map.put("user_head",user.getUser_head());
            map.put("accept_name",accept.getUser_name());
            res.add(map);
        }
        result = res != null ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //申请成为讲师
    @RequestMapping("/apply")
    @ResponseBody
    public Result userApply(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "usertel") String user_tel,
            @RequestParam(value = "usersex") int user_sex,
            @RequestParam(value = "userage") int user_age,
            @RequestParam(value = "usertage") int user_tage,
            @RequestParam(value = "userbrief") String user_brief){
        Teacher teacher = new Teacher(Utils.getId(),user_id,user_tel,user_sex,user_age,user_tage,user_brief);
        int res = (int)applyService.applyForTeacher(teacher);
        result = res == 1 ? new Result(teacher,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
}

