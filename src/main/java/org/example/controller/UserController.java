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
    private UpdateService updateService;
    @Resource
    private CourseService courseService;
    @Resource
    private ApplyService applyService;
    @Resource
    private ResourceService resourceService;
    @Resource
    private SubscribeService subscribeService;
    @Resource
    private AssessService assessService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private TestService testService;
    @Resource
    private OrderService orderService;
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
        User user = new User(Utils.getId(),username,email,password,"","","",0,1);
        return result.getData().toString().equals(code)?(int)registerService.registUser(user) == 1? new Result("","操作成功",200):new Result("","操作失败",404):new Result("","验证码错误",500);
    }
    //登录
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam(value = "email") String email,
                      @RequestParam(value = "password") String password) {
        User user = loginService.login(email);
        if(user.getUser_pass().equals(password)) {
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
    //完善用户信息
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestParam(value = "userid") String user_id,
                         @RequestParam(value = "address") String user_add,
                        @RequestParam(value = "like") String user_like) {
        User user = courseService.getUserById(user_id);
        user.setUser_add(user_add);
        user.setUser_like(user_like);
        int res = updateService.updateUser(user);
        result = res == 1 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //修改密码
    @RequestMapping("/update/password")
    @ResponseBody
    public Result updatePass(@RequestParam(value = "userid") String user_id,
                         @RequestParam(value = "oldpass") String old_pass,
                         @RequestParam(value = "newpass") String new_pass) {
        User user = courseService.getUserById(user_id);
        if(user.getUser_pass().equals(old_pass)){
            user.setUser_pass(new_pass);
            int res = updateService.updateUserPass(user);
            result = new Result(res,"操作成功",200);
        }else{
            result = new Result("","操作失败",404);
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
    //根据课程类型id查询
    @RequestMapping("/query/course/byclassid")
    @ResponseBody
    public Result getCourseByClassId(@RequestParam(value = "classid") String class_id){
        List<Course> list  = courseService.getCourseByClassId(class_id);
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //根据课程类型名称查询
    @RequestMapping("/query/course/byclassname")
    @ResponseBody
    public Result getCourseByClassName(@RequestParam(value = "classname") String class_name){
        List<CClass> list  = courseService.queryClassByClassName(class_name);
        List<Course> res = new ArrayList<>();
        if(list.size() != 0){
            for(CClass cClass:list){
                res.addAll(courseService.getCourseByClassId(cClass.getClass_id()));
            }
        }
        result = res.size() != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //获取轮播图
    @RequestMapping("/query/slider")
    @ResponseBody
    public Result getSlider(
            @RequestParam(value = "start") int start,
            @RequestParam(value = "end") int end){
        Object obj = resourceService.getSlider(start,end);
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
        List<CField> list = courseService.queryCourseField();
        List<Map> res = new ArrayList<>();
        for(CField cField:list){
            Map<String,Object> map = new HashMap<>();
            List<CClass> classes = courseService.queryCourseClass(cField.getField_id());
            map.put("field_id",cField.getField_id());
            map.put("field_name",cField.getField_name());
            map.put("classes",classes);
            res.add(map);
        }
        result =  res != null ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //根据状态查询类型
    @RequestMapping("/query/cclass/status")
    @ResponseBody
    public Result getCClassByStatus(@RequestParam(value = "status") int class_status) {
        List<CClass> list = courseService.queryCClassByStatus(class_status);
        List<Map> res = new ArrayList<>();
        if(list.size() != 0){
            for(CClass cClass:list){
                Map<String,Object> map = new HashMap();
                map.put("class_id",cClass.getClass_id());
                CField cField = courseService.queryCourseFieldById(cClass.getField_id());
                map.put("field_name",cField.getField_name());
                map.put("class_name",cClass.getClass_name());
                map.put("class_status",cClass.getClass_status());
                res.add(map);
            }
        }
        result = res.size() != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
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
        Order order = new Order(order_id,course.getCourse_name(),course.getCourse_fee(),Utils.getTime(),null,0,"微信");
        //插入订单和订阅信息
        int orderRes = orderService.insertOrder(order);
        if(orderRes == 1){
            int subRes = subscribeService.subscribeCourse(user_id,course_id,order_id);
            int subNum = courseService.updateCourseSubNum(course);
            if(subRes == 1 && subNum == 1){
                result = new Result(order,"操作成功",200);
            }else{
                orderService.cancelOrder(order_id);//订阅失败删除订单消息
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
    //查询订阅课程
    @RequestMapping("/query/subscribe/course")
    @ResponseBody
    public Result querySubscribeCourse(@RequestParam(value = "userid") String user_id) {
        List<SubscribeInfo> list = subscribeService.querySubscribeCourseAll(user_id);
        List<Course> res = new ArrayList<>();
        for(SubscribeInfo subscribeInfo:list){
            Course course = courseService.queryCourseById(subscribeInfo.getCourse_id());
            res.add(course);
        }
        result = res.size() != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
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
            int canOrderRes = orderService.cancelOrder(order_id);
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
    //查询订单
    @RequestMapping("/query/order")
    @ResponseBody
    public Result queryOrder(@RequestParam(value = "userid") String user_id) {
        List<SubscribeInfo> subscribeInfos = subscribeService.querySubscribeCourseAll(user_id);
        List<Map> list = new ArrayList<>();
        for(SubscribeInfo subscribeInfo:subscribeInfos){
            Map<String,Object> map = new HashMap<>();
            Course courses = courseService.queryCourseById(subscribeInfo.getCourse_id());
            Order order = orderService.queryOrder(subscribeInfo.getOrder_id());
            map.put("order_head",courses.getCourse_cover());
            map.put("order_id",order.getOrder_id());
            map.put("order_title",order.getOrder_title());
            map.put("order_fee",order.getOrder_fee());
            map.put("create_time",order.getCreate_time());
            map.put("pay_time",order.getPay_time());
            map.put("pay_status",order.getPay_status());
            map.put("pay_way",order.getPay_way());
            list.add(map);
        }
        result = list.size() != 0 ? new Result(list,"操作成功",200):new Result("","操作失败",404);
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
    //通过userid查询所有的收藏课程
    @RequestMapping("/query/collect/byuid")
    @ResponseBody
    public Result queryCollectCourseByUid(
            @RequestParam(value = "userid") String user_id) {
        List<CollectInfo> infos = subscribeService.queryCollectCourseByUid(user_id);
        List<Map> list = new ArrayList<>();
        for (CollectInfo info:infos) {
            Map<String,Object> map = new HashMap<>();
            Course course = courseService.getCourseByCourseId(info.getCourse_id());
            Teacher teacher = courseService.getTeacherByCourseId(info.getCourse_id());
            map.put("course_id",course.getCourse_id());
            map.put("class_id",course.getClass_id());
            map.put("teach_id",course.getTeach_id());
            map.put("course_status",course.getCourse_status());
            map.put("course_fee",course.getCourse_fee());
            map.put("course_name",course.getCourse_name());
            map.put("course_brief",course.getCourse_brief());
            map.put("subscribe_num",course.getSubscribe_num());
            map.put("course_cover",course.getCourse_cover());
            map.put("teach_name",courseService.getUserById(teacher.getUser_id()).getUser_name());
            list.add(map);
        }
        result = list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
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
            List<Reply> replies = assessService.queryReply(assess.getAssess_id());
            List<Map> replies_res = new ArrayList<>();
            for(Reply reply:replies){
                Map<Object,Object> replies_map = new HashMap();
                User send = assessService.queryUserById(reply.getUser_id());
                User accept = assessService.queryUserById(reply.getAccept_id());
                replies_map.put("user_name",send.getUser_name());
                replies_map.put("reply_id",reply.getReply_id());
                replies_map.put("user_id",reply.getUser_id());
                replies_map.put("assess_id",reply.getAssess_id());
                replies_map.put("reply_content",reply.getReply_content());
                replies_map.put("reply_time",reply.getReply_time());
                replies_map.put("reply_pointer",reply.getReply_pointer());
                replies_map.put("user_head",send.getUser_head());
                replies_map.put("accept_name",accept.getUser_name());
                replies_res.add(replies_map);
            }
            map.put("user_name",user.getUser_name());
            map.put("assess_id",assess.getAssess_id());
            map.put("course_id",assess.getCourse_id());
            map.put("user_id",assess.getUser_id());
            map.put("assess_content",assess.getAssess_content());
            map.put("assess_rate",assess.getAssess_rate());
            map.put("assess_time",assess.getAssess_time());
            map.put("assess_pointer",assess.getAssess_pointer());
            map.put("user_head",user.getUser_head());
            map.put("replies",replies_res);
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
    //申请成为讲师
    @RequestMapping("/apply")
    @ResponseBody
    public Result userApply(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "usertel") String user_tel,
            @RequestParam(value = "field") String teach_field,
            @RequestParam(value = "class") String teach_class,
            @RequestParam(value = "usersex") int user_sex,
            @RequestParam(value = "userage") int user_age,
            @RequestParam(value = "usertage") int user_tage,
            @RequestParam(value = "userbrief") String user_brief){
        Teacher teacher = new Teacher(Utils.getId(),user_id,user_tel,teach_field,teach_class,user_sex,user_age,user_tage,user_brief);
        Teacher isTeacher = applyService.checkTeacher(user_id);
        if(isTeacher == null){
            int res = applyService.applyForTeacher(teacher);
            result = res == 1 ? new Result(teacher,"操作成功",200):new Result("","操作失败",404);
        }else{
            result =  new Result("","你已经是讲师，请进入'我是讲师'界面操作！",500);
        }
        return result;
    }
    //查询聊天信息
    @RequestMapping("/query/message")
    @ResponseBody
    public Result userQueryMessage(@RequestParam(value = "userid") String user_id){
        List<Notice> accept = noticeService.queryAcceptMessageById(user_id);
        List<Notice> send = noticeService.querySendMessageById(user_id);
        Map<String,List> map = new HashMap<>();
        map.put("accept",accept);
        map.put("send",send);
        result = map != null ? new Result(map,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //查询系统通知
    @RequestMapping("/query/notice")
    @ResponseBody
    public Result userQueryNotice(@RequestParam(value = "userid") String user_id){
        List<Notice> notices = noticeService.querySystemNotice();
        List<Notice> accept = new ArrayList<>();
        List<Notice> no_accept = new ArrayList<>();
        for(Notice notice:notices){
            GetNoticeInfo noticeInfo = new GetNoticeInfo(user_id,notice.getNotice_id());
            if(noticeService.queryGetNoticeById(noticeInfo) != null){
                accept.add(notice);
            }else{
                no_accept.add(notice);
            }
        }
        Map<String,List> map = new HashMap<>();
        map.put("accept",accept);
        map.put("no_accept",no_accept);
        result = map != null ? new Result(map,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //将系统通知设置为已收到
    @RequestMapping("/notice/set")
    @ResponseBody
    public Result userSetNoticeAccept(
            @RequestParam(value = "noticeid") String notice_id,
            @RequestParam(value = "userid") String user_id){
        GetNoticeInfo noticeInfo = new GetNoticeInfo(user_id,notice_id);
        int res = noticeService.noticeGet(noticeInfo);
        result = res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //根据课程id查询课程章节
    @RequestMapping("/query/chapter")
    @ResponseBody
    public Result queryChapter(@RequestParam(value = "courseid") String course_id){
        List<Chapter> list = courseService.getChapterByCourseId(course_id);
        List<Map> res = new ArrayList<>();
        for(Chapter chapter:list){
            Map<String,Object> map = new HashMap<>();
            List<Bar> bars = courseService.getBarByChapterId(chapter.getChapter_id());
            map.put("chapter_id",chapter.getChapter_id());
            map.put("course_id",chapter.getCourse_id());
            map.put("chapter_order",chapter.getChapter_order());
            map.put("chapter_name",chapter.getChapter_name());
            map.put("bars",bars);
            res.add(map);
        }
        result = res.size() != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //通过courseid查询所有的测试
    @RequestMapping("/query/test")
    @ResponseBody
    public Result queryTestByCourseId(
            @RequestParam(value = "courseid") String course_id) {
        List<Test> tests = testService.queryTestByCourseId(course_id);
        List<Map> list = new ArrayList<>();
        for (Test test:tests) {
            Map<String,Object> map = new HashMap<>();
            List<Choose> chooses = testService.queryChooseByTestId(test.getTest_id());
            List<Choose> options = testService.queryOptionByTestId(test.getTest_id());
            map.put("test_id",test.getTest_id());
            map.put("teach_id",test.getTeach_id());
            map.put("ques_num",test.getQues_num());
            map.put("test_time",test.getTest_time());
            map.put("use_time",test.getUse_time());
            map.put("course_id",test.getCourse_id());
            map.put("test_name",test.getTest_name());
            map.put("test_chooses",chooses);
            map.put("test_options",options);
            list.add(map);
        }
        result = list != null ? new Result(list,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
    //点赞评论
    @RequestMapping("/point/assess")
    @ResponseBody
    public Result userPointAssess(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "assessid") String assess_id){
        PointerInfo pointerInfo = new PointerInfo(user_id,assess_id);
        int res = assessService.pointAssess(pointerInfo);
        if(res == 1){
            Assess assess = assessService.queryAssessById(assess_id);
            assess.setAssess_pointer(assess.getAssess_pointer()+1);
            int res_up = assessService.updateAssessPointerNum(assess);
            result = res_up == 1 ? new Result(res_up,"操作成功",200):new Result(res_up,"更新数据失败",404);
        }else{
            result = new Result(res,"操作失败",404);
        }
        return result;
    }
    //点赞评论
    @RequestMapping("/point/reply")
    @ResponseBody
    public Result userPointReply(
            @RequestParam(value = "userid") String user_id,
            @RequestParam(value = "replyid") String reply_id){
        PointerInfo pointerInfo = new PointerInfo(user_id,reply_id);
        int res = assessService.pointReply(pointerInfo);
        if(res == 1){
            Reply reply = assessService.queryReplyById(reply_id);
            reply.setReply_pointer(reply.getReply_pointer()+1);
            int rep_up = assessService.updateReplyPointerNum(reply);
            result = rep_up == 1 ? new Result(rep_up,"操作成功",200):new Result(rep_up,"更新数据失败",404);
        }else{
            result = new Result(res,"操作失败",404);
        }
        return result;
    }
    //根据id查询是否点赞过评论
    @RequestMapping("/query/assess/point")
    @ResponseBody
    public Result queryAssessPointerById(
            @RequestParam(value = "assessid") String assess_id,
            @RequestParam(value = "userid") String user_id) {
        PointerInfo pointerInfo = new PointerInfo(user_id,assess_id);
        PointerInfo res = assessService.queryAssessPoint(pointerInfo);
        result = new Result(res,"操作成功",200);
        return result;
    }
    //取消点赞评论
    @RequestMapping("/cancel/assess/point")
    @ResponseBody
    public Result cancelAssessPointerById(
            @RequestParam(value = "assessid") String assess_id,
            @RequestParam(value = "userid") String user_id) {
        PointerInfo pointerInfo = new PointerInfo(user_id,assess_id);
        int res = assessService.assessPointCancel(pointerInfo);
        result = res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
    //根据id查询是否点赞过评论回复
    @RequestMapping("/query/reply/point")
    @ResponseBody
    public Result queryReplyPointerById(
            @RequestParam(value = "replyid") String reply_id,
            @RequestParam(value = "userid") String user_id) {
        PointerInfo pointerInfo = new PointerInfo(user_id,reply_id);
        PointerInfo res = assessService.queryReplyPoint(pointerInfo);
        result = new Result(res,"操作成功",200);
        return result;
    }
    //取消点赞回复
    @RequestMapping("/cancel/reply/point")
    @ResponseBody
    public Result cancelReplyPointerById(
            @RequestParam(value = "replyid") String reply_id,
            @RequestParam(value = "userid") String user_id) {
        PointerInfo pointerInfo = new PointerInfo(user_id,reply_id);
        int res = assessService.replyPointCancel(pointerInfo);
        result = res != 0 ? new Result(res,"操作成功",200):new Result("","操作失败",500);
        return result;
    }
}

