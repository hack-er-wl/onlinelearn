<template>
  <el-autocomplete
      v-model="value"
      :fetch-suggestions="search"
      popper-class="my-autocomplete"
      placeholder="请输入"
      @select="my_router.push({path:'/result',query:{key:'IT行业',value:value}})">
    <template #suffix>
      <el-icon style="cursor: pointer" @click="search_icon(value)">
        <Search />
      </el-icon>
    </template>
  </el-autocomplete>
</template>

<script setup>
import {onMounted,ref} from "vue";
import {useRouter} from "vue-router";
const my_router = useRouter();
const data = [{value:"数据结构与算法"},{value:"Java程序设计"}];
import {useRoute} from "vue-router";
import {ElMessage} from "element-plus";
const my_route = useRoute();
const value = ref(my_route.query.value === null ? my_route.query.value:'');

const datas = ref([])

onMounted(() => {
  datas.value = data
})
const createFilter = (queryString) => {
  return (datas) => {
    return (
        datas.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
    )
  }
}
function search(queryString,cb){
  const results = queryString ? datas.value.filter(createFilter(queryString)) : datas.value
  cb(results)
}
function search_icon(value){
  if(value == ''){
    ElMessage({message:'请输入值!',type:'warning'});
  }else{
    my_router.push({path:'/result',query:{value:value}})
  }
}
</script>

<style>
.my-autocomplete li {
  line-height: normal;
  padding: 7px;
}
.my-autocomplete li .name {
  text-overflow: ellipsis;
  overflow: hidden;
}
.my-autocomplete li .addr {
  font-size: 12px;
  color: #b4b4b4;
}
.my-autocomplete li .highlighted .addr {
  color: #ddd;
}
</style>
