<template>
  <el-card>
    <template #header>
      <div class="person_card_header">
        <h4><i style="color:#11cef8" class="bi bi-cloud-arrow-up-fill"/> 我发布的课程</h4>
      </div>
    </template>
    <div style="display: flex;height: 500px">
      <FavoriteCard v-for="i in 3" :key="i" :isLast="i==3?true:false"/>
    </div>
    <div class="pagination">
      <el-pagination layout="prev, pager, next" :total="50" />
    </div>
  </el-card>
</template>

<script setup>

</script>

<style scoped>
.pagination{
  margin-top: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>