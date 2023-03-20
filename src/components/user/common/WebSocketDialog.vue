<template>
  <el-dialog class="elDialog" :show-close="false" :close-on-click-modal="false">
      <el-container>
        <el-header style="padding:20px;height: 40px;display: flex;align-items: center;justify-content:space-between;background-color: rgb(100,160,212);border-top-left-radius: 4px;border-top-right-radius: 4px">
          <div style="display: flex;align-items: center">
            <el-avatar
                :size="32"
                :src="getUser().user_head"/>
            <h6 id="name" style="margin-left: 10px;color: black">{{getUser().user_name}}</h6>
            <i class="bi bi-circle-fill" style="color:rgb(147,235,12);margin: 2px 0 0 10px;font-size: 5px"/><p style="margin-left:3px;color: gray;font-weight: bold;font-size: 5px">在线</p>
          </div>
            <el-tag type="info" round>
              <el-button style="margin-top: 2px;font-size: 10px" @click="close()" link><i class="bi bi-circle-fill" style="color:gray;margin-right: 2px"/>离线</el-button>
            </el-tag>
        </el-header>
        <el-main style="padding-bottom:20px;background-color: rgb(242,242,242);border-bottom-left-radius: 4px;border-bottom-right-radius: 4px">
          <!--展示区-->
          <el-scrollbar ref="myScrollbar">
            <div id="show" style="height: 318px;margin:0 10px 0 10px">
              <!--消息在这里-->
            </div>
          </el-scrollbar>
          <div style="display: flex;margin:10px 10px 0 10px">
            <el-input id="input" v-model="content" placeholder="请输入内容..." />
            <el-button icon="Promotion" id="sendBtn" type="primary" style="margin-left: 10px" @click="send()">发送</el-button>
          </div>
        </el-main>
      </el-container>
  </el-dialog>
</template>
<script  setup>
import {onMounted, ref, watch} from 'vue'
const content = ref("");
import {getUser} from "@/api/user";
import {useStore} from "vuex";
import moment from "moment";
import {data, initWebSocket} from "../../../api/socket";
import 'element-plus/es/components/message/style/css';
import {ElMessage} from "element-plus";

const connect = false;
const store = useStore();
function close(){
  store.state.layoutStore.callHide = false;
}
function send(){
  const time = moment().format('YYYY-MM-DD HH:mm:ss');
  if(this.content == "")
    ElMessage({message:"无内容!",type:'error'});
  else
    if(store.state.userStore.webSocket){
      setMessageInnerHTML_Me_Succeed(this.content,time);
      const message = {
        "message": this.content,
        "username": getUser().user_id,
        "to": '客服中心',
        "time": time
      };
      store.state.userStore.webSocket.send(JSON.stringify(message));
    }else{
      store.state.userStore.webSocket = initWebSocket();
      setTimeout(()=>{
        this.send();
      },500)
    }
}
function setMessageInnerHTML_Me_Fail(message,time) {
  document.getElementById("show").innerHTML += "<div style='display: flex;flex-direction: row-reverse;align-items:center;margin-top: 5px'><span class='el-avatar el-avatar--circle' data-v-0839fafa style='--el-avatar-size:32px'><img src='" + getUser().user_head + "' style='object-fit: cover'></span><p style='margin-right: 5px;padding: 4px;background-color: rgb(150,236,96);border-radius: 4px'>"+message+"</p><i class='bi bi-exclamation-circle-fill' style='color: red;margin-right:4px'></i><p style='margin-right: 5px;padding: 4px;color: gray;font-size: 1px'>" + time + "</p></div>";
  document.getElementById("input").value = "";
}
function setMessageInnerHTML_Me_Succeed(message,time) {
  document.getElementById("show").innerHTML += "<div style='display: flex;flex-direction: row-reverse;align-items:center;margin-top: 5px'><span class='el-avatar el-avatar--circle' data-v-0839fafa style='--el-avatar-size:32px'><img src='" + getUser().user_head + "' style='object-fit: cover'></span><p style='margin-right: 5px;padding: 4px;background-color: rgb(150,236,96);border-radius: 4px'>"+message+"</p><p style='margin-right: 5px;padding: 4px;color: gray;font-size: 1px'>" + time + "</p></div>";
  document.getElementById("input").value = "";
}
function setMessageInnerHTML_Other(message,time) {
  document.getElementById("show").innerHTML += "<div style='display: flex;margin-top: 5px'> <span class='el-avatar el-avatar--circle' data-v-0839fafa style='--el-avatar-size:32px'><img src='data:image/png;base64,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' style='object-fit: cover'></span> <p style='margin-left: 5px;padding: 4px;background-color: rgb(255,255,255);border-radius: 4px'>"+message+"</p><p style='margin-right: 5px;padding: 4px;color: gray;font-size: 1px'>" + time + "</p></div>";
}
onMounted(() => {
  watch(data.messages,() => {
    if(document.getElementById("show")){
      if(data.messages.length != 0){
        setMessageInnerHTML_Other(data.messages[0].textMessage,data.messages[0].time);
        data.messages.pop();
      }
    }
  })
})
</script>
<style lang="scss">
.el-card {
  --el-card-padding: 0px;
}
.elDialog {
  border-radius: 4px;
  .el-dialog__body{
    padding: 0px !important;
  }
  .el-dialog__header{
    padding: 0px;
    margin-right: 0px;
    border-bottom:none !important;
  }
 }
</style>