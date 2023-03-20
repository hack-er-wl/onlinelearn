<template>
  <el-card>
    <template #header>
      <div class="person_card_header">
        <h4> <i style="color:gray" class="bi bi-cloud-drizzle-fill"/> 当前天气</h4>
      </div>
    </template>
    <iframe scrolling="no" src="https://tianqiapi.com/api.php?style=ti&skin=pitaya" frameborder="0" width="250" height="270" allowtransparency="true"/>
  </el-card>
</template>

<script setup>
</script>

<style scoped>

</style>