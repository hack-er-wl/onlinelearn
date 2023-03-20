<template>
  <el-card
      :body-style="{padding: '0px'}"
      shadow="hover"
      class="card"
      :style="{margin:data.isFirst.value?data.isLast.value?'0':'0 1.4% 0 0':data.isLast.value?'1% 0 0 0':'1% 1.4% 0 0'}">
    <img :src="data.course.value.course_cover" class="image" :style="{height:'180px',width:'100%',borderRadius:'4px'}"/>
    <div class="tags" style="padding:3px">
      <h5>{{data.course.value.course_name}}</h5>
      <div class="card_bottom">
          <el-tag type="info"><el-icon><User /></el-icon>{{data.course.value.subscribe_num}}人订阅</el-tag>
          <el-tag :type="data.course.value.course_fee == 0 ? '':'danger'">{{data.course.value.course_fee == 0 ? "免费":data.course.value.course_fee + "￥"}}</el-tag>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import {toRefs,defineProps} from "vue";

const props = defineProps({
  isLast:Boolean,
  isFirst:Boolean,
  course:Object
})
const data = toRefs(props)
</script>

<style>
.card{
  height:15%;
  width:19.6%;
}
.card_bottom {
  margin-top: 10px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.image {
  width: 100%;
  display: block;
}
</style>
