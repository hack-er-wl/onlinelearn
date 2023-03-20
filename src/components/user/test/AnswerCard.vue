<template>
  <el-card class="answer_box_card">
    <template #header>
      <div class="card-header">
        <h5><i style="color: gray" class="bi bi-search-heart-fill"/> 单选题</h5>
      </div>
    </template>
    <el-button size="large" style="margin:4px" v-for="o in 7" :key="o" circle >{{o}}</el-button>
  </el-card>
  <el-card class="answer_box_card">
    <template #header>
      <div class="card-header">
        <h5><i style="color: gray" class="bi bi-check-circle-fill"/> 判断题</h5>
      </div>
    </template>
    <el-button size="large" style="margin:4px" v-for="o in 4" :key="o" circle >{{o}}</el-button>
  </el-card>
</template>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.answer_box_card {
  width: 99%;
  margin-bottom: 15px;
}
</style>
