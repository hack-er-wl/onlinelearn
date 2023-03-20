package org.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.common.MessageBox;
import org.example.common.Utils;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@ServerEndpoint("/websocket/{username}")
/*实时通信服务*/
public class SocketService {
    //以用户的姓名为key，WebSocket为对象保存起来
    private static Map<String, SocketService> clients = new ConcurrentHashMap<>();
    //用来存在线连接数
    private static final Map<String, Session> sessions = new HashMap<>();
    //离线列表
    private static MessageBox messageBox = new MessageBox();
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
            //若有广播消息
            if(messageBox.hasKey("allPeople")){
                for (Object message:messageBox.get("allPeople")) {
                    sendMessageTo(JSON.toJSONString(message),username);
                }
            }
            //若有单播消息
            if(messageBox.hasKey(username)) {
                for(Object message:messageBox.get(username)) {
                    log.info("open"+message);
                    sendMessageTo(JSON.toJSONString(message),username);
                    messageBox.move(username);
                }
            }
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
        try {
            log.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromusername = jsonObject.getString("username");
            String tousername = jsonObject.getString("to");
            String time = jsonObject.getString("time");
            Map<String, Object> map1 = new HashMap<>();
            map1.put("textMessage", textMessage);
            map1.put("fromusername", fromusername);
            map1.put("tousername", tousername);
            map1.put("time", time);
            if(clients.containsKey(tousername))
                sendMessageTo(JSON.toJSONString(map1), tousername);
            else
                messageBox.add(tousername,map1);
        } catch (Exception e) {
            log.info("发生了错误了！");
        }
        log.info(clients.toString());
    }
    //发送单播消息
    public void sendMessageTo(String message, String ToUserName) throws IOException {
        log.info("websocket服务端单播消息:" + message);
        if(clients.get(ToUserName) != null){
            synchronized(sessions.get(ToUserName)){
                sessions.get(ToUserName).getBasicRemote().sendText(message);
            }
        }
    }
    //发送广播消息
    public void sendMessageAll(String message){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("textMessage", message);
        map1.put("fromusername","系统");
        map1.put("tousername","allPeople");
        map1.put("time", Utils.getTime());
        log.info("websocket服务端广播消息:" + JSON.toJSONString(map1));
        messageBox.add("allPeople",map1);
        for(Map.Entry<String,Session> session:sessions.entrySet()){
            try {
                if (session.getValue().isOpen()) {
                    session.getValue().getBasicRemote().sendText(JSON.toJSONString(map1));
                }
            } catch (Exception e) {
                log.info("广播时出现错误！");
            }
        }
    }
}