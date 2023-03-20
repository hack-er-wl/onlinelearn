<template>
  <div class="data">
    <div style="display: flex">
      <DataCard v-for="(n,i) in data" :key="i"
                :title="n.title"
                :value="n.value"
                :content="n.content"
                :foot="n.foot"
                :color="n.color"
                :num="n.num"/>
    </div>
    <UserEchartCard/>
  </div>
</template>

<script setup>
import DataCard from "./DataCard.vue";
import UserEchartCard from "./UserEchartCard.vue";
const data = [{
  title:"注册用户",
  value:1000,
  content:"所有注册用户数量",
  foot:"比昨天增加",
  num:24,
  color:"rgb(238,102,102)"
},
  {
    title:"讲师用户",
    value:500,
    content:"申请并成为讲师的用户数量",
    foot:"比昨天增加",
    num:10,
    color:"rgb(250,200,88)"
  },{
    title:"访问游客",
    value:1000,
    content:"访问却没有注册的用户数量",
    foot:"比昨天增加",
    num:20,
    color:"rgb(145,204,117)"
  },{
    title:"其他游客",
    value:100,
    content:"其他用户数量",
    foot:"比昨天增加",
    num:5,
    color: "rgb(84,112,198)"
  }]
</script>

<style scoped>
.data{
  height: 128.5vh;
}
</style>