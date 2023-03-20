<template>
  <!--轮播图区域-->
  <div class="main_carousel">
    <Carousel/>
  </div>
  <!--课程类型区域-->
  <div class="main_sort_table">
    <SortTable/>
  </div>
  <!--展示区域-->
  <div class="main_show">
    <ShowHot/>
    <ShowNew/>
  </div>
</template>
<script setup>
import Carousel from "./Carousel.vue";
import ShowHot from "./ShowHot.vue";
import ShowNew from "./ShowNew.vue";
import SortTable from "./SortTable.vue";
</script>
<style scoped>
.main_carousel{
  margin-top: 8px;
  padding-left:25px;
  padding-right: 25px;
}
.main_sort_table{
  margin:15px 25px 0 25px;
}
.main_show{
  padding-left: 25px;
  padding-right: 25px;
  margin-bottom: 10px;
}
</style>
