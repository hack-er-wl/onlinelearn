<template>
  <el-card style="padding: 0 !important;margin-top: 4px">
    <template #header>
      <div class="card-header">
        <h4><i class="bi bi-pie-chart"/> 当前进度</h4>
      </div>
    </template>
    <div style="display:flex">
      <p style="font-size: 15px">你已完成</p>
      <el-progress style="padding-left: 5px;width: 70%" :percentage="100" :format="format"/>
    </div>
  </el-card>
</template>
<script setup>
const format = (percentage) => (percentage === 100 ? '11/11' : `${percentage}%`)
</script>
