<template>
    <el-container class="container">
      <!--头部区域-->
      <el-header>
        <Header/>
      </el-header>
      <!--内容区域-->
      <el-scrollbar>
        <el-main class="course_main" id="el-main">
          <el-container style="margin-bottom: 3px">
            <el-aside width="70%" style="margin-left: 26px;margin-right: 10px">
              <Videoplayer :video-url="require('../../assets/video/test.mp4')"/>
            </el-aside>
            <el-main style="padding: 0 !important;margin-right: 23px">
              <Chapters/>
            </el-main>
          </el-container>
          <div style="display: flex;margin-left: 25px;margin-right: 23px">
            <Summary style="margin-right: 5px"/>
            <Author/>
          </div>
          <div style="margin-left: 25px;margin-right: 25px;margin-bottom: 10px">
            <AssessCard/>
          </div>
        </el-main>
        <el-footer>
          <Footer/>
        </el-footer>
      </el-scrollbar>
    </el-container>
  <AssessDialog v-model="store.state.layoutStore.Hide"/>
  <ReplyDialog v-model="store.state.layoutStore.isHide"/>
</template>
<script setup>
import Header from "../../components/user/common/Header.vue";
import Videoplayer from "../../components/user/course/VideoPlayer.vue";
import Chapters from "../../components/user/course/Chapters.vue";
import Author from "../../components/user/course/Author.vue";
import Summary from "../../components/user/course/Summary.vue";
import AssessCard from "../../components/user/course/AssessCard.vue";
import AssessDialog from "../../components/user/course/AssessDialog.vue";
import ReplyDialog from "../../components/user/course/ReplyDialog.vue";
import {useStore} from "vuex";
import Footer from "../../components/user/common/Footer.vue";
import {onMounted, toRaw} from "vue";
import {getUser} from "@/api/user";
import {useRoute} from "vue-router";
import qs from "qs";
const store = useStore();
const route = useRoute();
const course = qs.parse(route.query.key);
onMounted(async () => {
  store.state.layoutStore.course = course;
  await store.dispatch("handleQueryTeacherByCourse", toRaw({courseid: course.course_id})).then((res) => {
    store.state.layoutStore.teacher = res;
  })
  await store.dispatch("handleQueryCollect", toRaw({userid: getUser().user_id, courseid: course.course_id})).then((res) => {
    if (res)
      store.state.Like = true
    else
      store.state.Like = false
  })
  await store.dispatch("handleQuerySubscribeCourse", toRaw({
    userid: getUser().user_id,
    courseid: course.course_id
  })).then((res) => {
    if (res)
      store.state.userStore.Subscribe = true;
    else
      store.state.userStore.Subscribe = false;
  })
  store.state.layoutStore.assesses = [];
  await store.dispatch("handleQueryAssess", toRaw({courseid: course.course_id})).then(async (assesses) => {
    console.log(assesses)
    for (let i in assesses) {
      await store.dispatch("handleQueryReply", toRaw({assessid: assesses[i].assess_id})).then((replies) => {
        store.state.layoutStore.assesses.push({assess: assesses[i], replies: replies});
      })
    }
  })
})
</script>
<style lang="scss" scoped>
.container{
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
    margin: 0 25px 0 25px;
  }
}
.course_main{
  --el-main-padding: 8px !important;
  background-color: rgb(242,242,245);
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