package org.example.controller;

import org.example.common.Result;
import org.example.service.SocketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
public class BroadController {
    @Resource
    private SocketService socketService;
    private Result result;
    @RequestMapping("/broad")
    @ResponseBody
    public Result broadcast(@RequestParam(value = "message") String message){
        socketService.sendMessageAll(message);
        result = new Result(message,"广播成功",200);
        return result;
    }
}
