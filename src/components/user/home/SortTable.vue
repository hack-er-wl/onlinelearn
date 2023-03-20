<template>
  <el-container>
    <el-header style="padding: 0">
      <h4 style="color: gray">
        课程分类
        <i class="bi bi-house-door-fill" style="color:purple"/>
      </h4>
    </el-header>
    <el-main>
      <div class="main">
        <div class="sortTable">
          <div class="classes" v-for="(n,i) in store.state.layoutStore.fields" :key="i">
            <p style="width: 50px;color: gray;display:flex;align-items: center;justify-content:center;font-weight: bold;font-size: 13px">{{n.field.field_name}}</p>
            <el-divider style="height: auto" direction="vertical"/>
            <el-button v-for="(t,i) in n.classes" :key="i" style="font-size: 13px;color: gray" round @click="handleClick(n.field.field_name,t)">
              {{t.class_name}}
            </el-button>
          </div>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script setup>
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {toRaw} from "vue";
const store = useStore();
const my_router = useRouter();
async function handleClick(field_name,t) {
  await store.dispatch("handlequeryCourseByClassId", toRaw({classid: t.class_id})).then((res) => {
    store.state.layoutStore.results = res;
  })
  await my_router.push({path: '/result', query: {key: field_name, value: t.class_name}});
}
</script>

<style lang="scss" scoped>
.sortTable{
  display: flex;
  flex-direction: column;
  .classes{
    display: flex;
    margin: 2px 0 2px 0;
    height: 30px;
  }
  .el-button+.el-button {
    margin-left: 5px;
  }
}
</style>