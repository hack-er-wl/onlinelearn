<template>
  <div class="data">
    <UserAreaEchart/>
    <UserTable/>
  </div>
</template>

<script  setup>

import UserTable from "./UserTable.vue";
import UserAreaEchart from "./UserAreaEchart.vue";
</script>
<style lang="scss" scoped>
.data{
  height: 128.5vh;
  width: 100%;
}
</style>
