<template>
  <!--讲师信息区域-->
  <Detail style="margin:8px 25px 0 25px"/>
  <!--中间区域-->
  <div style="display: flex">
    <Operate style="margin:8px 4px 0 25px;width: 20%"/>
    <WeatherCard style="margin:8px 4px 0 4px;width: 20%"/>
    <RulesCard style="margin:8px 25px 0 4px;width: 60%"/>
  </div>
  <!--展示区域-->
  <MyPost style="margin:8px 25px 10px 25px"/>
</template>
<script setup>
import Detail from "./Detail.vue";
import Operate from "./Operate.vue";
import MyPost from "./MyPost.vue";
import WeatherCard from "./WeatherCard.vue";
import RulesCard from "./RulesCard.vue";
</script>
<style scoped>

</style>
