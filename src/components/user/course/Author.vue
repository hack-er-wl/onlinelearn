<template>
  <el-card class="author_box_card">
    <template #header>
      <div class="card-header">
        <div style="display: flex;align-items: center">
          <el-avatar :size="50" :src="store.state.layoutStore.teacher.user_head"/>
          <div style="margin-left: 4px">
            <h4>{{store.state.layoutStore.teacher.user_name}}</h4>
            <div style="display:flex;">
              <el-icon><Message /></el-icon>
              <h6 style="color: gray;margin-left: 4px">{{store.state.layoutStore.teacher.e_mail}}</h6>
              <el-icon style="margin-left: 4px"><Iphone /></el-icon>
              <h6 style="color: gray">{{store.state.layoutStore.teacher.user_tel}}</h6>
            </div>
          </div>
        </div>
        <div>
          <el-button :type="store.state.userStore.Subscribe ? 'warning':'primary'" icon="Pointer" @click="subscribeCourse">{{store.state.userStore.Subscribe ? '取消订阅':'订阅课程'}}</el-button>
        </div>
      </div>
    </template>
    <div>{{store.state.layoutStore.teacher.user_brief}}</div>
  </el-card>
</template>
<script setup>
  import { useI18n } from "vue-i18n";
  import useNotification from "@/hooks/useNotification";
  import {useStore} from "vuex";
  import {toRaw} from "vue";
  import {getUser} from "@/api/user";
  const { t } = useI18n();
  const store = useStore();

  const subscribeCourse = () => {
    if(store.state.userStore.Subscribe == false){
      store.dispatch("handleSubscribeCourse", toRaw({userid:getUser().user_id,courseid:store.state.layoutStore.course.course_id})).then(async (res) => {
        console.log(res);
        if (res) {
          useNotification('success', '系统通知', t("addCourseSuccess"));
          store.state.userStore.Subscribe = true;
          await store.dispatch("handlequeryCourseByCourseId", toRaw({courseid: store.state.layoutStore.course.course_id})).then((result) => {
            store.state.layoutStore.course = result;
          })
        } else {
          useNotification('success', '系统通知', t("addCourseFailed"));
        }
      })
    }else{
      store.dispatch("handleCancelSubscribeCourse", toRaw({userid:getUser().user_id,courseid:store.state.layoutStore.course.course_id})).then(async (res) => {
        console.log(res);
        if (res) {
          useNotification('success', '系统通知', t("cancelCourseSuccess"));
          store.state.userStore.Subscribe = false;
          await store.dispatch("handlequeryCourseByCourseId", toRaw({courseid: store.state.layoutStore.course.course_id})).then((result) => {
            store.state.layoutStore.course = result;
          })
        } else {
          useNotification('success', '系统通知', t("cancelCourseFailed"));
        }
      })
    }
  }
</script>
<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.author_box_card {
  width: 100%;
}
</style>
