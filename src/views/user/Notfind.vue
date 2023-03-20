<template>
  <div class="errPage">
    <div style="margin-top: 100px">
      <el-result icon="info" title="404">
        <template #sub-title>
          <p style="color: gray;margin-left: 15px">抱歉，无法找到该网页！</p>
        </template>
        <template #extra>
          <div class="goback">
            <el-button type="warning" @Click="goBack">返回</el-button>
            <el-button type="primary" @Click="goHome">首页</el-button>
          </div>
        </template>
      </el-result>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

function goHome() {
  router.push("/");
}

function goBack() {
  router.go(-1);
}
</script>

<style lang="scss" scoped>
.errPage {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  .goback {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>