package org.example.controller;

import org.example.common.Result;
import org.example.common.Utils;
import org.example.entity.Course;
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
    private Result result;
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
        int res = (int)postService.postCourse(course);
        result = res == 1 ? new Result(course,"操作成功",200):new Result("","操作失败",404);
        return result;
    }
}
