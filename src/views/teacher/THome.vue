<template>
  <el-container class="common-layout">
    <!-- 头部区域 -->
    <el-header>
      <Header />
    </el-header>
    <!-- 内容区 -->
    <el-scrollbar>
      <el-main id="el-main" style="background-color: rgb(242,242,245)">
        <TMain/>
      </el-main>
      <el-footer>
        <Footer/>
      </el-footer>
    </el-scrollbar>
  </el-container>
  <TeacherEditDialog v-model="store.state.layoutStore.tperHide"/>
  <AddCourseDialog v-model="store.state.layoutStore.addCouHide"/>
  <AddTestDialog v-model="store.state.layoutStore.addTeHide"/>
</template>

<script setup>
import Header from "../../components/user/common/Header.vue";
import Footer from "../../components/user/common/Footer.vue";
import TMain from "../../components/teacher/thome/TMain.vue";
import {useStore} from "vuex";
import TeacherEditDialog from "../../components/teacher/thome/TeacherEditDialog.vue";
import AddCourseDialog from "../../components/teacher/thome/AddCourseDialog.vue";
import AddTestDialog from "../../components/teacher/thome/AddTestDialog.vue";
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
    margin: 0 30px 0 25px;
  }
}
</style>
