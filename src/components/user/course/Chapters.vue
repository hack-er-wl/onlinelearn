<template>
  <el-card
      :body-style="{ padding: '0px',height:'548px'}">
    <el-scrollbar>
      <div class="chapter">
        <span class="title">{{str}}总章节</span>
        <ul class="chapter_list">
          <li v-for="item in 15" :key="item" class="scrollbar-demo-item">
            <el-button link @click="store.state.layoutStore.learn_num++">第{{item}}章&emsp;什么是数据结构</el-button>
          </li>
          <li class="scrollbar-demo-item">
            <el-button type="success" size="small" :disabled="store.state.layoutStore.learn_num >= 5?false:true" @click="router.push('/test')">参加测试</el-button>
          </li>
        </ul>
      </div>
    </el-scrollbar>
  </el-card>
</template>

<script setup>
import {useStore} from "vuex";
import router from "../../../router";

const str = "数据结构与算法";
const store = useStore();
</script>

<style scoped>
.title{
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 20px;
  font-weight: bold;
}
.scrollbar-demo-item{
  display: flex;
  align-items: center;
  justify-content: center;
  height: 30px;
  margin: 10px 20px 10px 20px;
  text-align: center;
  border-radius: 10px;
}
.scrollbar-demo-item-title{
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px 20px 10px 20px;
  text-align: center;
  border-radius: 4px;
}
</style>
