<template>
  <el-card class="message_box_card" shadow="hover">
    <template #header>
      <div class="card-header">
        <h5><i class="bi bi-bell-fill" style="color: #cf9236"/> 消息中心</h5>
      </div>
    </template>
    <div style="display: flex;flex-direction: column;justify-content: center">
      <el-button type="info" round><i class="bi bi-gear" style="color: white"/>&nbsp;系统消息</el-button>
      <el-button type="info" style="margin-top: 8px" round><i class="bi bi-chat-dots" style="color: white"/>&nbsp;普通消息</el-button>
      <el-button type="info" style="margin-top: 8px" round><i class="bi bi-envelope" style="color: white"/>&nbsp;其他消息</el-button>
    </div>
  </el-card>
</template>

<style lang="scss" scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.message_box_card {
  width: 100%;
  margin-bottom: 15px;
}
.el-button+.el-button {
  margin-left: 0;
}
</style>
