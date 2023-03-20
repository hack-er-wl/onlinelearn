<template>
  <el-card>
    <template #header>
      <div class="person_card_header">
        <h4><i style="color:gray" class="bi bi-gear-fill"/> 课程操作</h4>
      </div>
    </template>
    <div class="operate">
      <el-button type="primary" plain round @click="store.state.layoutStore.addCouHide = true">发布课程</el-button>
      <el-button type="primary" plain round style="margin-top: 8px"  @click="store.state.layoutStore.addTeHide = true">发布测试</el-button>
      <el-button type="primary" plain round style="margin-top: 8px" >发布成绩</el-button>
    </div>
  </el-card>
</template>

<script setup>
import {useStore} from "vuex";

const store = useStore();
</script>

<style scoped>
.operate{
  display: flex;
  flex-direction: column;
}
 .el-button+.el-button {
   margin-left: 0px;
 }
</style>