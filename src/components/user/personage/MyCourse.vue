<template>
  <el-card class="person_box_card" shadow="hover">
    <template #header>
      <div class="person_card_header">
        <h4><i style="color: red" class="bi bi-journal-bookmark-fill"/> 我的课程</h4>
      </div>
    </template>
    <div style="display: flex;flex-direction: column;align-items: center">
      <CourseCard v-for="i in 3" :key="i" :isLast="i==3?true:false"/>
    </div>
    <div class="pagination">
      <el-pagination layout="prev, pager, next" :total="50" />
    </div>
  </el-card>
</template>

<script setup>
import CourseCard from "./CourseCard.vue"
</script>

<style scoped>
.pagination{
  margin-top: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>