<template>
  <el-card class="person_box_card" shadow="hover">
    <template #header>
      <div class="person_card_header">
        <h4><i style="color: red" class="bi bi-star-half"/> 我的收藏</h4>
      </div>
    </template>
    <div style="display: flex;height: 500px">
      <FavoriteCard v-for="i in 3" :key="i" :isLast="i==3?true:false" @click="router.push('/course')" style="cursor: pointer"/>
    </div>
    <div class="pagination">
      <el-pagination layout="prev, pager, next" :total="50" />
    </div>
  </el-card>
</template>

<script setup>
import FavoriteCard from "./FavoriteCard.vue";
import router from "../../../router"
</script>

<style scoped>
.pagination{
  margin-top: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>