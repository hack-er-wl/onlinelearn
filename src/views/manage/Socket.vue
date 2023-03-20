<template>
  <div class="call">
    <el-container>
      <h1 style="display:flex;justify-content: center;margin: 20px"><p>客服问题回复中心</p></h1>
      <el-header style="height: 40px;display: flex;align-items: center;justify-content:space-between;background-color: rgb(100,160,212);border-top-left-radius: 4px;border-top-right-radius: 4px">
        <div style="display: flex;align-items: center">
          <el-avatar
              :size="32"
              :src="call"/>
          <h6 id="name" style="margin-left: 10px">客服中心</h6>
        </div>
        <div>
          <el-tag type="info" round>
            <el-button style="margin-top: 2px;font-size: 10px" @click="openWebSocket()" link><i class="bi bi-cursor-fill" style="color:rgb(147,235,12);margin-right: 2px"/>在线</el-button>
          </el-tag>
        </div>
      </el-header>
      <el-main style="padding-bottom: 20px;padding-top:10px;background-color: rgb(242,242,242);border-bottom-left-radius: 4px;border-bottom-right-radius: 4px">
        <!--展示区-->
        <el-scrollbar ref="myScrollbar">
          <div id="show" style="height: 318px;padding: 0px 20px;">
            <!--消息-->
          </div>
        </el-scrollbar>
        <div style="display: flex;margin-top: 10px;padding: 0px 20px">
          <el-input id="input" v-model="message" placeholder="请输入内容..." />
          <el-button id="sendBtn" type="primary" style="margin-left: 10px" @click="send()">发送</el-button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>
<script setup>
import { ref } from 'vue'
const message = ref('')
import call from "../../assets/img/logo.png"
import {ElMessage} from "element-plus"
import moment from "moment/moment";
let fromusername;
let webSocket;

function send(){
  if(fromusername){
    const time = moment().format('YYYY-MM-DD HH:mm:ss');
    setMessageInnerHTML_Me(this.message,time);
    const message = {
      "message": this.message,
      "username": document.getElementById('name').innerText,
      "to": fromusername,
      "time": time
    };
    webSocket.send(JSON.stringify(message));
  }else{
    ElMessage({message:"请等待用户上线!",type:"warning"})
  }
}

function setMessageInnerHTML_Me(message,time) {
  document.getElementById("show").innerHTML += "<div style='display: flex;flex-direction: row-reverse;margin-top: 5px'><span class='el-avatar el-avatar--circle' data-v-0839fafa style='--el-avatar-size:32px'><img src='/img/user.116c3e63.png' style='object-fit: cover'></span><p style='margin-right: 5px;padding: 4px;background-color: rgb(150,236,96);border-radius: 4px'>"+message+"</p><p style='margin-right: 5px;padding: 4px;color: gray;font-size: 1px'>" + time + "</p></div>";
  document.getElementById("input").value = "";
}
function setMessageInnerHTML_Other(message,time) {
  document.getElementById("show").innerHTML += "<div style='display: flex;margin-top: 5px'> <span class='el-avatar el-avatar--circle' data-v-0839fafa style='--el-avatar-size:32px'><img src='/img/user_1.687accbf.png' style='object-fit: cover'></span> <p style='margin-left: 5px;padding: 4px;background-color: rgb(255,255,255);border-radius: 4px'>"+message+"</p><p style='margin-right: 5px;padding: 4px;color: gray;font-size: 1px'>" + time + "</p></div>";
}
function  openWebSocket(){
  webSocket = new WebSocket("ws://localhost:8080/websocket/" + document.getElementById("name").innerText+"/")
  if (webSocket) {
    //连通之后的回调事件
    webSocket.onopen = function () {
      ElMessage({message:"已经连通了websocket!",type:"warning"})
    };
    //接收后台服务端的消息
    webSocket.onmessage = function (evt) {
      const received_msg = evt.data;
      const obj = JSON.parse(received_msg);
      if(obj.fromusername != "系统"){
        setMessageInnerHTML_Other(obj.textMessage,obj.time);
        fromusername = obj.fromusername;
        console.log(fromusername);
        ElMessage({message:obj.fromusername + "对" + obj.tousername + "说：" + obj.textMessage,type:"warning"});
      }
    };
    //连接关闭的回调事件
    webSocket.onclose = function () {
      ElMessage({message:"连接已经关闭....",type:"error"});
    };
  } else {
    // 浏览器不支持 WebSocket
    ElMessage({message:"您的浏览器不支持 WebSocket!",type:"error"});
  }
}
</script>
<style lang="scss" scoped>
.call{
  display:flex;
  align-items: center;/*垂直居中*/
  justify-content: center;
  margin:0 auto;
  width: 700px;
  height: 700px;
}
.el-card {
  --el-card-padding: 0;
}
.el-main{
  --el-main-padding: 0;
}
</style>
