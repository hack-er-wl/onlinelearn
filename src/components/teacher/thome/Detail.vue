<template>
  <el-card>
    <div class="main_detail">
      <div class="main_detail_left">
        <el-avatar
            class="mr-3"
            :size="50"
            :src="user"/>
        <div style="margin-left: 4px">
          <div style="display:flex">
            <h4>和大爷</h4>
            <el-button link style="padding:2px 0 0 10px" @click="store.state.layoutStore.tperHide = true">
              <el-icon size="large"><Edit/></el-icon>
            </el-button>
          </div>
          <div style="display:flex">
            <el-icon><Message /></el-icon>
            <h6 style="color: gray;margin-left: 4px">hedaye@qq.com</h6>
            <el-icon style="margin-left: 4px"><Iphone /></el-icon>
            <h6 style="color: gray">18187684139</h6>
          </div>
        </div>
      </div>
      <div class="main_detail_right">
        <el-tag size="large" style="margin-right: 5px"><i style="color: #08ff00" class="bi bi-shield-lock-fill"/> 签订协议</el-tag>
        <el-tag size="large" class="ml-2" type="warning" style="margin-right: 5px"><i style="color: #f5ae16" class="bi bi-shield-lock-fill"/> 填写资质信息</el-tag>
        <el-tag size="large" class="ml-2" type="danger"><i style="color: #fd0000" class="bi bi-shield-lock-fill"/> 审核资质信息</el-tag>
      </div>
    </div>
  </el-card>
</template>
<script setup>
import user from "../../../assets/img/user.png";
import {useStore} from "vuex";
const store = useStore();
</script>
<style scoped>
.main_detail{
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.main_detail_left{
  display: flex;
  align-items: center;
}
</style>
