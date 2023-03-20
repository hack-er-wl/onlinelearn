<template>
  <el-card class="summary_box_card">
    <template #header>
      <div class="card-header">
        <h4><i style="color: red" class="bi bi-binoculars-fill"/> 课程简介</h4>
        <h6>
          <el-button class="like_btn" @click="handleCollect" round size="small">
            <h5 style="color: gray;margin-right: 2px">收藏</h5>
            <i :class="store.state.Like?like_fill:like_no_fill" :style="{color:store.state.Like?'#ff8800':''}"/>
          </el-button>
          <el-tag :style="`margin:${margin}px`" type="info"><el-icon><User /></el-icon>{{store.state.layoutStore.course.subscribe_num}}人订阅</el-tag>
          <el-tag :type="store.state.layoutStore.course.course_fee == 0 ? '':'danger'" :style="`margin:${margin}px`">{{store.state.layoutStore.course.course_fee == 0 ? "免费":store.state.layoutStore.course.course_fee+"￥"}}</el-tag>
          <el-tag :style="`margin:${margin}px`"  :type="store.state.userStore.Subscribe ? '':'warning'">{{store.state.userStore.Subscribe ? '已订阅':'未订阅'}}</el-tag>
        </h6>
      </div>
    </template>
    <div>{{store.state.layoutStore.course.course_brief}}</div>
  </el-card>
</template>
<script setup>
  import {useStore} from "vuex";
  import {toRaw} from "vue";
  import {getUser} from "@/api/user";
  const margin = 3;
  const store = useStore();
  const like_no_fill ="bi bi-star";
  const like_fill = "bi bi-star-fill";
  const handleCollect = () => {
    if(store.state.Like == false){
      store.dispatch("handleAddCollect", toRaw({userid:getUser().user_id,courseid:store.state.layoutStore.course.course_id})).then((res)=>{
        console.log(res);
        if(res)
          store.state.Like = true
      })
    }else{
      store.dispatch("handleCancelCollect", toRaw({userid:getUser().user_id,courseid:store.state.layoutStore.course.course_id})).then((res)=>{
        console.log(res);
        if(res)
          store.state.Like = false
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
.summary_box_card {
  width: 100%;
}
.like_btn{
  margin: 0 3px 4px 0;
  justify-self: center;
}
</style>
