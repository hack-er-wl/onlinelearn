<template>
  <el-container class="common-layout">
    <!-- 头部区域 -->
    <el-header>
      <Header />
    </el-header>
    <!-- 内容区 -->
    <el-scrollbar>
      <el-main style="background-color: rgb(242,242,245);" id="el-main">
        <el-container style="margin-top: 8px">
          <el-aside>
            <PersonMenu/>
          </el-aside>
          <el-main style="padding: 0;margin-left: 4px;margin-right: 25px;height: 100vh">
            <PersonShow/>
          </el-main>
        </el-container>
      </el-main>
      <el-footer style="border-top: solid 1px var(--el-menu-border-color)">
        <Footer/>
      </el-footer>
    </el-scrollbar>
  </el-container>
  <PersonEditDialog v-model="store.state.layoutStore.perHide"/>
  <EditPassWord v-model="store.state.layoutStore.pasHide"/>
</template>

<script setup>
import Header from "../../components/user/common/Header.vue";
import Footer from "../../components/user/common/Footer.vue";
import PersonMenu from "../../components/user/personage/PersonMenu.vue";
import PersonShow from "../../components/user/personage/PersonShow.vue";
import {useStore} from "vuex";
import EditPassWord from "../../components/user/personage/EditPassWord.vue";
const store = useStore();
</script>

<style lang="scss" scoped>
.common-layout {
  height: 100vh;

  .el-aside {
    transition: width 0.3s ease-out;

    .logo {
      height: 60px;
      line-height: 60px;
      color: #fff;
      font-size: 26px;
      font-weight: bold;
      text-align: center;
    }

    .el-menu-vertical-demo:not(.el-menu--collapse) {
      width: 200px;
      min-height: 400px;
    }
  }

  .el-header {
    display: flex;
    align-items: center;
    padding: 0;
  }

  .el-main {
    width: 100%;
    padding:4px 0 0 0;
  }
  .el-footer{
    --el-footer-padding:0;
    margin: 0 26px 0 25px;
  }
}
</style>
