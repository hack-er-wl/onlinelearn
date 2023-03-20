<template>
  <el-container class="container">
    <!--头部区域-->
    <el-header>
      <Header/>
    </el-header>
    <!--内容区域-->
    <el-scrollbar>
      <el-main class="test_main" id="el-main">
        <el-container>
          <el-aside width="25%" style="padding-left: 25px;padding-right: 25px">
            <AnswerCard/>
          </el-aside>
          <el-main style="padding: 0 !important">
            <Paper/>
            <ButtonCard btn-a="提交" btn-b="退出" style="margin-bottom: 15px"/>
          </el-main>
          <el-aside width="25%" style="padding-left: 25px;padding-right: 25px">
            <Time/>
          </el-aside>
        </el-container>
      </el-main>
      <!--底部区域-->
      <el-footer>
        <Footer/>
      </el-footer>
    </el-scrollbar>
  </el-container>
</template>
<script setup>
import Header from "../../components/user/common/Header.vue";
import AnswerCard from "../../components/user/test/AnswerCard.vue";
import Paper from "../../components/user/test/Paper.vue";
import Time from "../../components/user/test/Time.vue";
import Footer from "../../components/user/common/Footer.vue";
import ButtonCard from "../../components/user/common/ButtonCard.vue";
</script>
<style lang="scss" scoped>
.test_main{
  --el-main-padding: 8px !important;
  background-color: rgb(242,242,245);
}
.container {
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
  padding:8px 0 0 0;
}
  .el-footer{
    --el-footer-padding:0;
    margin: 0 30px 0 25px;
  }
}
.el-header {
  --el-header-padding: 0 px;
  --el-header-height:0 px;
  padding: var(--el-header-padding);
  box-sizing: border-box;
  flex-shrink: 0;
  height: var(--el-header-height);
}
</style>