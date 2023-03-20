<template>
  <el-card class="table-card">
    <el-form :model="formAddField" style="display: flex;justify-content: space-between">
      <el-form-item label="领域名称" style="margin-top: 10px">
        <el-input v-model="formAddField.fieldname" placeholder="请输入领域···"/>
      </el-form-item>
      <el-form-item style="margin-top: 10px">
        <el-button type="primary" @click="onSubmit">添加领域</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup>
import {reactive} from "vue";
const emit = defineEmits(['submit']);
const formAddField = reactive({
  fieldname: "设计",
})
const onSubmit = () => {
  emit('submit',formAddField);
}
</script>

<style lang="scss" scoped>
.table-card{
  margin: 10px;
}
</style>