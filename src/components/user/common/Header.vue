<template>
    <div class="header">
    <!-- 右侧区域-->
    <div class="header_left">
      <div class="header_left_logo">
        <img src="../../../assets/img/logo.png"/>
      </div>
      <div class="header_left_title">
        <h3>{{$t('title')}}</h3>
      </div>
    </div>
    <!--中间区域-->
    <div class="header_center">
      <div class="header_center_menu">
        <el-menu
            class="el-menu-demo"
            mode="horizontal"
            router
            :default-active="this.$router.path">
          <el-menu-item index="/home">首页</el-menu-item>
          <el-menu-item index="/apply">讲师入住</el-menu-item>
          <el-menu-item index="/thome">我是讲师</el-menu-item>
        </el-menu>
      </div>
      <div class="header_center_search">
        <Search />
      </div>
    </div>
    <!-- 左侧区域-->
    <div class="header_right">
      <Theme />
      <Notify />
      <Language />
      <UserDropDown />
    </div>
  </div>
</template>

<script setup>
import { useStore } from "vuex";
import Search from "./Search.vue";
import Notify from "./Notify.vue";
import Language from "./Language.vue";
import UserDropDown from "./UserDropDown.vue";
import Theme from "./Theme.vue";
import {useI18n} from "vue-i18n";

const store = useStore();
const { t } = useI18n();
</script>

<style lang="scss" scoped>

.header {
  display: flex;
  justify-content: space-between;
  width: 100%;
  flex-wrap: wrap;
  position: relative;
  margin: 0 25px 0 25px;
  .header_left {
    display: flex;
    align-items: center;
  }
  .header_left_logo{
    align-items: center;
  }
  .header_left_logo img{
    height: 35px;
    padding-top: 5px;
  }
  .header_left_title{
    padding-left: 8px;
    text-align: center;
  }
  .header_center{
    display: flex;
    flex: fit-content;
    flex-direction: row;
    align-items: center;
    justify-content: center;
  }
  .header_center_menu{
    width: 36%;
  }
  .header_right {
    display: flex;
    align-items: center;
  }
  .el-menu--horizontal{
    border-bottom:none;
  }
}
</style>