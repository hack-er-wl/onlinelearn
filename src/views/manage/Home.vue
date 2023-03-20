<template>
    <el-container class="common-layout">
        <el-aside id="el-aside">
          <Menu/>
        </el-aside>
        <el-container class="right">
          <el-header>
            <Header/>
          </el-header>
         <el-scrollbar>
           <el-main id="el-main">
             <router-view/>
           </el-main>
         </el-scrollbar>
        </el-container>
    </el-container>
</template>

<script setup>
import Menu from "../../components/manage/common/Menu.vue";
import Header from "../../components/manage/common/Header.vue";
</script>

<style lang="scss" scoped>
.common-layout {
  height: 100vh;
  .el-aside {
    height: 100%;
    width: 16.4%;
  }
  .right{
    height: 100vh;
    .el-main {
      width: 100%;
      overflow: unset;
      background-color: rgb(240,242,245);
    }
    .el-header {
      display: flex;
      align-items: center;
      padding: 0;
    }
  }
}
</style>
