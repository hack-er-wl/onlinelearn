<template>
  <el-card class="question_box_card">
    <template #header>
      <div class="card-header">
        <h6><el-tag class="ml-2" type="warning">判断</el-tag> {{index['index']}}.数据结构是计算机存储知识数据的方式,是指相互之间存在一种或多种特定关系的数据元素的集合?</h6>
      </div>
    </template>
      <el-radio-group v-model="radio1" class="answer_group">
        <el-radio label="1" size="small">A.对</el-radio>
        <el-radio label="2" size="small">B.错</el-radio>
      </el-radio-group>
  </el-card>
</template>
<script setup>
import {ref,toRefs} from "vue";
const props = defineProps({
  index:Number
})
const index = toRefs(props)
const radio1 = ref('1');
</script>
<style scoped>
.question_box_card {
  width: 99.8%;
  margin:0 0 10px 0;
}
.answer_group{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
</style>