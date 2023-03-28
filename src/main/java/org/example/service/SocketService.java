package org.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.common.ApplicationHelper;
import org.example.common.Utils;
import org.example.entity.GetNoticeInfo;
import org.example.entity.Notice;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@ServerEndpoint("/websocket/{username}")
/*实时通信服务*/
public class SocketService {
    private NoticeService noticeService = (NoticeService) ApplicationHelper.getBean("noticeService");
    private  LoginService loginService = (LoginService) ApplicationHelper.getBean("loginService");
    //以用户的姓名为key，WebSocket为对象保存起来
    private static Map<String, SocketService> clients = new ConcurrentHashMap<>();
    //用来存在线连接数
    private static final Map<String, Session> sessions = new HashMap<>();
    /**
     * OnOpen 表示有浏览器链接过来的时候被调用
     * OnClose 表示浏览器发出关闭请求的时候被调用
     * OnMessage 表示浏览器发消息的时候被调用
     * OnError 表示有错误发生，比如网络断开了等等
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        try {
            clients.put(username,this);
            sessions.put(username,session);
            log.info("有新连接接入！ 当前在线人数" + clients.size());
        } catch (Exception e) {
            e.printStackTrace();
            log.info(username + "上线时发生了错误！");
        }
    }
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误！" + error.getMessage());
    }
    @OnClose
    public void onClose(@PathParam("username") String username) {
        clients.remove(username);
        log.info("有连接关闭！ 当前在线人数" + clients.size());
    }
    @OnMessage
    public void onMessage(String message,Session session) {
        log.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
        JSONObject jsonObject = JSON.parseObject(message);
        String textMessage = jsonObject.getString("message");
        String fromusername = jsonObject.getString("username");
        String tousername = jsonObject.getString("to");
        String time = jsonObject.getString("time");
        Map<String, Object> map1 = new HashMap<>();
        String id = Utils.getId();
        map1.put("noticeid",id);
        map1.put("textMessage", textMessage);
        map1.put("fromusername", fromusername);
        map1.put("tousername", tousername);
        map1.put("time", time);
        if(!fromusername.equals("0000000001")){
            User user = loginService.getUserById(fromusername);
            map1.put("head",user.getUser_head());
        }
        Notice notice = new Notice(id,textMessage,Utils.getTime(),fromusername,tousername);
        try {
            sendMessageTo(notice,JSON.toJSONString(map1),tousername);
        } catch (Exception e) {
            log.info("发生了错误了！");
        }
        log.info(sessions.toString());
    }
    //发送单播消息
    public void sendMessageTo(Notice notice,String message, String ToUserName) throws IOException {
        log.info("websocket服务端单播消息:" + message);
        noticeService.postNotice(notice);
        Session session = sessions.get(ToUserName);
        if(session != null){
            session.getBasicRemote().sendText(message);
            GetNoticeInfo getNoticeInfo = new GetNoticeInfo(ToUserName,notice.getNotice_id());
            noticeService.noticeGet(getNoticeInfo);
        }
    }
    //发送广播消息
    public void sendMessageAll(String message){
        Map<String, Object> map1 = new HashMap<>();
        String time = Utils.getTime();
        String id = Utils.getId();
        map1.put("noticeid",id);
        map1.put("textMessage", message);
        map1.put("fromusername","0000000000");
        map1.put("tousername","0000000002");
        map1.put("time",time);
        log.info("websocket服务端广播消息:" + JSON.toJSONString(map1));
        //生成一个通知id及对象,其中id：000000002代表所有人
        Notice notice = new Notice(id,message,time,"0000000000","0000000002");
        //插入数据库
        noticeService.postNotice(notice);
        //发送给当前在线用户
        for(Map.Entry<String,Session> session:sessions.entrySet()){
            try {
                if (session.getValue().isOpen() && !session.getKey().equals("0000000001")) {
                    session.getValue().getBasicRemote().sendText(JSON.toJSONString(map1));
                }
            } catch (Exception e) {
                log.info("广播时出现错误！");
            }
        }
    }
}