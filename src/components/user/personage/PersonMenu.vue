<template>
  <el-card class="person_box_card" shadow="hover" style="margin-left: 25px">
    <template #header>
      <div class="person_card_header">
        <div style="display: flex;flex-direction: column;align-items: center">
          <el-avatar :size="50" style="margin-top: 8px" icon="UserFilled" :src="getUser().user_head"/>
          <div style="display:flex;margin-top: 3px;margin-right: 4px">
            <i class="bi bi-person"/>
            <h4 style="color: white;margin-left: 4px">{{getUser().user_name}}</h4>
          </div>
          <div style="display:flex;margin-bottom: 12px">
            <el-icon><Message /></el-icon>
            <h6 style="color: white;margin-left: 4px">{{getUser().e_mail}}</h6>
          </div>
        </div>
      </div>
    </template>
    <div style="display: flex;flex-direction: column;align-items: center">
      <el-button round icon="Reading" @click="learnCenter()">学习中心</el-button>
      <el-button round icon="Collection" @click="myCourse()" style="margin-left: 0 !important;margin-top: 8px">我的课程</el-button>
      <el-button round icon="Star" @click="myFavorite()" style="margin-left: 0 !important;margin-top: 8px">我的收藏</el-button>
      <el-button round icon="Tickets" @click="myOrder()" style="margin-left: 0 !important;margin-top: 8px">我的订单</el-button>
      <el-button round icon="Edit" @click="store.state.layoutStore.perHide = true" style="margin-left: 0 !important;margin-top: 8px">编辑信息</el-button>
      <el-button round icon="Edit" @click="store.state.layoutStore.pasHide = true" style="margin-left: 0 !important;margin-top: 8px">修改密码</el-button>
    </div>
  </el-card>
</template>
<script setup>
import {useStore} from "vuex";
import {ElMessage} from "element-plus";
import {getUser} from "@/api/user";
const store = useStore();

function learnCenter(){
  store.state.layoutStore.show[0] = false;
  store.state.layoutStore.show[1] = true;
  store.state.layoutStore.show[2] = true;
}
function myCourse(){
  store.state.layoutStore.show[0] = true;
  store.state.layoutStore.show[1] = false;
  store.state.layoutStore.show[2] = true;
}
function myFavorite(){
  store.state.layoutStore.show[0] = true;
  store.state.layoutStore.show[1] = true;
  store.state.layoutStore.show[2] = false;
}
function myOrder(){
  ElMessage({message:"订单功能预计下周实现！",type:"warning"});
}
</script>
<style scoped>
  .person_card_header{
    background-color: rgb(64,158,255);
    border-radius: 4px;
  }
</style>
