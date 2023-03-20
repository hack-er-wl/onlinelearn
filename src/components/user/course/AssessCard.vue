<template>
  <el-card class="assess_box_card">
    <template #header>
      <div class="card-header">
        <h4><i style="color: gray" class="bi bi-chat-dots-fill"/> 课程评论</h4>
        <el-button type="primary" icon="ChatDotRound" @click="handleClick">评论</el-button>
      </div>
    </template>
    <div v-for="(n,i) in store.state.layoutStore.assesses" :key="i">
        <Assess :assess="n.assess"/>
        <div :id="'replay' + i"  style="background-color: rgb(247,247,247);border-radius: 4px;margin-left: 40px">
          <Reply v-for="(t,i) in n.replies" :key="i" :reply="t"/>
        </div>
    </div>
  </el-card>
</template>
<script setup>
import {useStore} from "vuex";
import Reply from "./Reply.vue";
import Assess from "@/components/user/course/Assess.vue";
const store = useStore();
const handleClick = () => {
  store.state.layoutStore.Hide = true
}
</script>
<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.assess_box_card {
  width: 100%;
  margin-top: 8px;
}
.el-button .is-link{
  padding: 0px;
}
</style>
