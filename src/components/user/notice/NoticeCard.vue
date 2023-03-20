<template>
  <el-card class="message_box_card" shadow="hover">
    <template #header>
      <div class="card-header">
        <h6><i class="bi bi-gear-wide-connected" style="color: rgb(91,192,248)"/> {{data.notice.value.fromusername}}</h6>
        <p style="font-size: 5px;color: gray">{{data.notice.value.time}}</p>
      </div>
    </template>
    {{data.notice.value.textMessage}}
  </el-card>
</template>
<script setup>
import {toRefs} from "vue";
import {defineProps} from "vue"
const props = defineProps({
  notice:Object
})
const data = toRefs(props)

</script>
<style lang="scss" scoped>

.message_box_card {
  width: 99.8%;
  margin:0 0 10px 0;
    .card-header{
        display: flex;
      justify-content: space-between;
    }
}
</style>
