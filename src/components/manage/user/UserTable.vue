<template>
  <el-card  class="table-card">
    <el-table :data="filterTableData" border>
      <el-table-column type="index" width="55px" label="编号"/>
      <el-table-column prop="id" label="用户ID" />
      <el-table-column prop="name" label="昵称" />
      <el-table-column prop="email" label="邮箱" />
      <el-table-column prop="teacher" label="是否是讲师" />
      <el-table-column prop="like" label="兴趣" />
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="small" placeholder="请输入···"/>
        </template>
        <template #default="scope">
          <el-button type="primary" size="small" @click="">编辑</el-button>
          <el-button size="small" type="danger" @click="">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="display: flex;justify-content: center;margin-top: 20px">
      <el-pagination layout="prev, pager, next" :total="50" />
    </div>
  </el-card>
</template>

<script setup>
import {computed, ref} from "vue";

const search = ref('');
const filterTableData = computed(() =>
    tableData.filter(
        (data) =>
            !search.value ||
            data.name.toLowerCase().includes(search.value.toLowerCase())
    )
)
const tableData = [
  {
    id: '0989765678001',
    name: 'Tom',
    email:'1369778414@qq.com',
    teacher: '否',
    like:'数据结构与算法'
  },
  {
    id: '0989765678031',
    name: 'John',
    email:'1369778814@qq.com',
    teacher: '否',
    like:'Java程序设计'
  },
  {
    id: '0989365678001',
    name: 'Morgan',
    email:'1369778214@qq.com',
    teacher: '否',
    like:'高等数学'
  },
  {
    id: '0929765678001',
    name: 'Jessy',
    email:'1369778014@qq.com',
    teacher: '否',
    like:'平面设计'
  },{
    id: '0989761678001',
    name: 'Tom',
    email:'1369778414@qq.com',
    teacher: '否',
    like:'数据结构与算法'
  },
  {
    id: '0989265678021',
    name: 'John',
    email:'1369778814@qq.com',
    teacher: '否',
    like:'Java程序设计'
  },
  {
    id: '0989335678001',
    name: 'Morgan',
    email:'1369778214@qq.com',
    teacher: '否',
    like:'高等数学'
  },
  {
    id: '0929465678001',
    name: 'Jessy',
    email:'1369778014@qq.com',
    teacher: '否',
    like:'平面设计'
  },{
    id: '0989335378001',
    name: 'Morgan',
    email:'1369778214@qq.com',
    teacher: '否',
    like:'高等数学'
  },
  {
    id: '0929455678001',
    name: 'Jessy',
    email:'1369778014@qq.com',
    teacher: '否',
    like:'平面设计'
  }
]
</script>

<style lang="scss" scoped>
.table-card{
  margin: 10px;
}
</style>