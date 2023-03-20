<template>
  <div class="userDropDown">
    <el-dropdown trigger="click" @command="handleCommand">
      <!-- 用户名 -->
      <span class="el-dropdown-link">
        <el-avatar :size="25" :src="user"/>
      </span>
      <!-- 菜单项 -->
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item v-if="is_login" icon="User" command="userInfo">个人中心</el-dropdown-item>
          <el-dropdown-item v-if="is_login" icon="Service" command="call">客服中心</el-dropdown-item>
          <el-dropdown-item v-if="is_login" icon="SwitchButton" command="logout">退出登录</el-dropdown-item>
          <el-dropdown-item v-if="!is_login" icon="Opportunity" command="goto-login">登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
  <WebSocketDialog v-model="store.state.layoutStore.callHide"/>
</template>

<script setup>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import {getUser} from "@/api/user";
import WebSocketDialog from "./WebSocketDialog.vue";
import header from "@/assets/img/user.png"
const is_login = localStorage.getItem('VUE_ADMIN_ISLOGIN') != null ? true:false
const user = getUser() ? getUser().user_head : header
const store = useStore();
const router = useRouter();

function handleCommand(val) {
  if (val === "userInfo") {
    router.push("/personage");
  }else if(val === "call"){
    store.state.layoutStore.callHide = true;
  }else if(val === "logout"){
    store.commit("logout");
    router.push("/login");
  }else if(val == "goto-login"){
    router.push("/login");
  }
}
</script>

<style lang="scss" scoped>
@import "../../../style/mixin";

.userDropDown {
  cursor: pointer;
  height: 100%;
  padding:0 5px;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;

  &:hover {
    @include hover_bg_color();
  }

  .el-dropdown-link {
    display: flex;
    align-items: center;
  }
}
</style>