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
          <el-dropdown-item icon="SwitchButton" command="logout">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import user from "../../../assets/img/user.png"

const store = useStore();
const router = useRouter();

function handleCommand() {
  store.commit("logout");
  router.push("/mlogin");
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