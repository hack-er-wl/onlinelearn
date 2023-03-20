<template>
  <el-container class="common-layout">
    <!-- 头部区域 -->
    <el-header>
      <Header />
    </el-header>
    <!-- 内容区 -->
    <el-scrollbar>
      <el-main style="background-color: rgb(242,242,245)" id="el-main">
        <Main/>
      </el-main>
      <el-footer>
        <Footer/>
      </el-footer>
    </el-scrollbar>
  </el-container>
</template>

<script setup>
import Header from "../../components/user/common/Header.vue";
import Footer from "../../components/user/common/Footer.vue";
import Main from "../../components/user/home/Main.vue";
import {onMounted, toRaw} from "vue";
import {useStore} from "vuex";
const store = useStore();
onMounted(async () => {
  store.state.layoutStore.hotCourse = [];
  store.state.layoutStore.newCourse = [];
  store.state.layoutStore.slider = [];
  store.state.layoutStore.fields = [];
  await store.dispatch("handleSlider", toRaw({start: 0, end: 5})).then((res) => {
    store.state.layoutStore.slider = res;
  })
  store.dispatch("handlequeryFields", toRaw({})).then(async (fields) => {
    for (let i in fields) {
      await store.dispatch("handlequeryClasses", toRaw({fieldid: fields[i].field_id})).then((classes) => {
        store.state.layoutStore.fields.push({field: fields[i], classes: classes});
      })
    }
  });
  await store.dispatch("handlequeryCourseByStatus", toRaw({status: 0})).then((res) => {
    store.state.layoutStore.hotCourse = res;
  })
  await store.dispatch("handlequeryCourseByStatus", toRaw({status: 1})).then((res) => {
    store.state.layoutStore.newCourse = res;
  })
})
</script>

<style lang="scss">
.common-layout {
  height: 100vh;

  .el-aside {
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
