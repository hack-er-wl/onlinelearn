<template>
  <el-container class="container">
    <!--头部区域-->
    <el-header>
      <Header/>
    </el-header>
    <!--内容区域-->
    <el-scrollbar>
      <el-main class="message_main" id="el-main">
        <div style="margin: 0 25px 0 25px;width: 25%">
          <NoticeLeft/>
        </div>
        <div style="margin: 0 25px 0 0px;width: 75%">
          <NoticeShow/>
        </div>
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
import Footer from "../../components/user/common/Footer.vue";
import NoticeLeft from "../../components/user/notice/NoticeLeft.vue";
import NoticeShow from "../../components/user/notice/NoticeShow.vue";
import {onMounted} from "vue";
import {useStore} from "vuex";
const store = useStore();
onMounted(()=>{
  store.state.layoutStore.isDot = false;
})
</script>
<style lang="scss" scoped>

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
  .message_main{
    display: flex;
    background-color: rgb(242,242,245);
    margin-bottom: 10px;
    height: 100vh;
  }
  .el-main {
    width: 100%;
    padding:8px 0 0 0;
  }
  .el-footer{
    --el-footer-padding:0;
    margin: 0 25px 0 25px;
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