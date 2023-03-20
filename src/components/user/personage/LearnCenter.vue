<template>
  <el-card class="person_box_card" shadow="hover">
    <template #header>
      <div class="person_card_header">
        <h4><i style="color: red" class="bi bi-book-half"/> 你可能感兴趣</h4>
        <el-button round size="small" icon="Refresh" style="margin-right: 10px" @click="handleChange">换一批</el-button>
      </div>
    </template>
    <div style="display: flex;height: 100%;flex-wrap: wrap">
      <Card v-for="(n,i) in store.state.layoutStore.recCourses" :key="i"
            :isLast="(i+1) % 4 == 0 ? true:false"
            :isFirst="(i+1) <= 4 ? true : false"
            @click="handleClick(n)"
            :course="n"
            style="cursor: pointer;width: 23.5%"/>
    </div>
    <div class="pagination">
      <el-pagination layout="prev, pager, next" :total="50" />
    </div>
  </el-card>
</template>

<script setup>
import Card from "../home/Card.vue";
import router from "../../../router";
import {onMounted, toRaw} from "vue";
import {useStore} from "vuex";
import qs from "qs";
const store = useStore();
const handleChange = async () => {
  store.state.layoutStore.recCourses = [];
  await store.dispatch("handleRecommend", toRaw({})).then((res) => {
    store.state.layoutStore.recCourses = res;
  })
}
const handleClick = (course) => {
  store.state.layoutStore.assesses = [];
  router.push({path: '/course', query: {key: qs.stringify(course)}})
}
onMounted(async () => {
  store.state.layoutStore.recCourses = [];
  await store.dispatch("handleRecommend", toRaw({})).then((res) => {
    console.log(res);
    store.state.layoutStore.recCourses = res;
  })
})
</script>

<style scoped>
.person_card_header{
  border-radius: 4px;
  display: flex;
  justify-content: space-between;
}
.pagination{
  margin-top: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>