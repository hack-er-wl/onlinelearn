<template>
  <el-card class="button_card">
    <div class="button_card_box">
      <el-button type="success" @click="submit()" icon="CircleCheck">{{btns.btnA.value}}</el-button>
      <el-button type="warning" @click="cancel()" icon="CircleClose">{{btns.btnB.value}}</el-button>
    </div>
  </el-card>
</template>
<script setup>
import 'element-plus/es/components/message/style/css';
import {toRefs} from "vue";
const emit = defineEmits(['submit','cancel']);
const props = defineProps({
  btnA:String,
  btnB:String,
})
const btns = toRefs(props)
function submit(){
  emit('submit');
}
function cancel(){
  emit('cancel');
}

</script>
<style lang="scss" scoped>
.button_card{
  width: 99.8%;
  margin:15px 0 0 0;
  .button_card_box{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }
}
</style>
