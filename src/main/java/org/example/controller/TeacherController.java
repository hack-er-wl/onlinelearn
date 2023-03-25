package org.example.controller;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.Bar;
import org.example.entity.Chapter;
import org.example.entity.Course;
import org.example.entity.Teacher;
import org.example.service.ApplyService;
import org.example.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private PostService postService;
    @Resource
    private ApplyService applyService;
    private Result result;
    //发布课程小节
    @RequestMapping("/check")
    @ResponseBody
    public Result checkTeacher(@RequestParam(value = "userid") String user_id) {
        Teacher teacher = applyService.checkTeacher(user_id);
        result = teacher != null ? new Result(teacher,"操作成功",200):new Result("","你不是讲师，请先申请讲师！",500);
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
            @RequestParam(value = "coursebrief") String course_brief) {
        String course_id = Utils.getId();
        String course_cover = "http://localhost:8080/static/"+course_id+".png";
        Course course = new Course(course_id,class_id,teach_id,0,course_fee,course_name,course_brief,0,course_cover);
        int res = postService.postCourse(course);
        result = res == 1 ? new Result(course,"操作成功",200):new Result("","操作失败",404);
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
            @RequestParam(value = "barname") String bar_name) {
        String bar_id = Utils.getId();
        String bar_url = "http://localhost:8080/static/"+bar_id+".mp4";
        Bar bar = new Bar(bar_id,chapter_id,bar_order,bar_name,bar_url);
        int res = postService.postBar(bar);
        result = res == 1 ? new Result(bar,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
}
