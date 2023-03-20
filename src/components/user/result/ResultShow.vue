<template>
  <div class="resultContainer">
      <el-breadcrumb separator-icon="ArrowRight">
        <el-breadcrumb-item>{{my_route.query.key}}</el-breadcrumb-item>
        <el-breadcrumb-item>{{my_route.query.value}}</el-breadcrumb-item>
      </el-breadcrumb>
      <el-empty class="empty" v-if="store.state.layoutStore.results.length == 0" description="空空如也~" style="height:500px"/>
      <div v-else style="display: flex; flex-wrap: wrap;margin-top: 15px">
          <Card
                  v-motion
                  :initial="{ opacity: 1, x:0,y:0}"
                  :enter="{ opacity: 1, x: 0,y:0}"
                  :hovered="{y:-3}"
                  v-for="(n,i) in store.state.layoutStore.results" :key="i"
                  :isLast="(i+1) % 4 == 0 ? true : false"
                  :isFirst="(i+1) <= 4 ? true : false"
                  :course="n"
                  @click="handleClick(n)"
                  style="cursor: pointer;width: 23.8%"/>
    </div>
   <div class="pagination">
     <el-pagination layout="prev, pager, next" :total="50" />
   </div>
  </div>
</template>

<script setup>
import Card from "../home/Card.vue"
import router from "../../../router";
import {useRoute} from "vue-router";
import {useStore} from "vuex";
import qs from "qs";
import {toRaw} from "vue";
import {getUser} from "@/api/user";
const my_route = useRoute();
const store = useStore();
console.log(my_route.query);
const handleClick = async (course) => {
  await store.dispatch("handleQueryCollect", toRaw({
    userid: getUser().user_id,
    courseid: course.course_id
  })).then((res) => {
    if (res)
      store.state.Like = true
    else
      store.state.Like = false
  })
  store.state.layoutStore.assesses = [];
  await router.push({path: '/course', query: {key: qs.stringify(course)}})
}
</script>

<style scoped>
.pagination{
  margin-top: 8%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>