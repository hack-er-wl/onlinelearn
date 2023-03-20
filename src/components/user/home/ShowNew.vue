<template>
  <el-container>
    <el-header style="padding: 0">
      <div class="bottom">
        <span>
          <h4 style="color: gray">
            最新课程
            <i class="bi bi-bookmark-check-fill" style="color: blue"/>
          </h4>
        </span>
        <span style="color: gray">
          更多<el-icon color="blue"><ArrowRightBold /></el-icon>
        </span>
      </div>
    </el-header>
    <el-main style="display: flex;flex-wrap: wrap">
      <Card v-motion
            :initial="{ opacity: 1, x:0,y:0}"
            :enter="{ opacity: 1, x: 0,y:0}"
            :hovered="{y:-3}"
            v-for="(n,i) in store.state.layoutStore.newCourse" :key="i"
            :isLast="(i+1) % 4 == 0 ? true : false"
            :isFirst="(i+1) <= 4 ? true : false"
            :course="n"
            @click="handleClick(n)"
            style="cursor: pointer;width: 23.8%"/>
    </el-main>
  </el-container>
</template>
<script setup>
import Card from "./Card.vue";
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import  qs from "qs";
const store = useStore();
const router = useRouter();
const handleClick = (course)=>{
  store.state.layoutStore.assesses = [];
  router.push({path: '/course', query: {key: qs.stringify(course)}})
}
</script>

<style>
.bottom{
  display: flex;
  flex: auto;
  align-items: center;
  justify-content: space-between;
}
</style>
