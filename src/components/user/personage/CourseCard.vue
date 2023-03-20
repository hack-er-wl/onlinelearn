<template>
  <el-card
      :body-style="{padding: '0px',display:'flex'}"
      shadow="hover"
      class="course_card"
      style="width: 100%"
      :style="{marginBottom:isLast.isLast.value?'0px':'4px'}">
    <img src="../../../assets/img/cover.png" class="image" :style="{height:'180px',width:'30%',borderRadius:'4px'}"/>
    <div style="padding:3px;width: 100%">
      <div style="margin-left: 8px;margin-right:8px;margin-top: 16px;display: flex;justify-content: space-between">
        <h4>数据结构与算法</h4>
        <el-tag>免费</el-tag>
      </div>
      <div style="margin-left: 8px;margin-right:8px;margin-top: 80px;display: flex;justify-content: space-between">
        <div class="left">
          <div style="display: flex">
            <h6 style="color:gray;margin-right: 24px">主讲老师：和大爷</h6>
            <h6 style="color:gray;margin-right: 24px">课程时常：2h</h6>
            <h6 style="color:gray">已有120人学习</h6>
          </div>
          <div style="margin-top: 4px;width: 400px">
            <el-progress :percentage="10" :format="(percentage) => (percentage === 10 ? '1/10' : `${percentage}%`)"/>
          </div>
        </div>
        <div class="right">
          <el-button type="success" @click="router.push('/course')" round icon="Reading">继续学习</el-button>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import router from "../../../router"
import {toRefs} from "vue";
import {defineProps} from "vue";
const props = defineProps({
  isLast:Boolean
})
const isLast = toRefs(props)
</script>

<style scoped>

</style>