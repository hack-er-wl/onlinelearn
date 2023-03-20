<template>
   <el-carousel :interval="2000"
                type="card"
                height="380px"
                indicator-position="none"
                :autoplay="true"
                :loop="true">
     <el-carousel-item v-for="(n,i) in store.state.layoutStore.slider" :key="i">
       <img style="width: 100%;height: 100%;" :src="n.slider_url"/>
     </el-carousel-item>
   </el-carousel>
</template>
<script setup>
import {useStore} from "vuex";
const store = useStore();
</script>
<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
