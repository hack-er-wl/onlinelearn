<template>
  <div class="replay">
    <div style="display: flex">
      <el-avatar :size="25" :src="data.reply.value.user_head"/>
      <div style="margin-left: 10px;display: flex;flex-direction: column;justify-content: space-between">
        <h6 style="color: gray;margin-bottom: 10px">{{data.reply.value.user_name}} <i class="bi bi-caret-right-fill" style="color:gray;"/> {{data.reply.value.accept_name}}</h6>
        <p style="font-size: 15px">{{data.reply.value.reply_content}}</p>
        <h6 style="color: gray;margin-top: 10px"><i class="bi bi-clock"/> {{data.reply.value.reply_time}}</h6>
      </div>
    </div>
    <div style="align-items: center;display: flex">
      <div>
        <el-button round size="small" style="margin-right: 10px" @click="handleClick">回复</el-button>
      </div>
      <div>
        <el-button class="point_btn" @click="store.state.Point = !store.state.Point" link>
          <i :class="store.state.Point?point_fill:point_no_fill" :style="{color:store.state.Point?'#ff8800':''}"/>
        </el-button>
      </div>
      <div style="margin-left: 10px">
        <p style="color: gray;font-size: 1px;align-items: center;padding:0px">{{data.reply.value.reply_pointer}}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import {useStore} from "vuex";
import {defineProps, toRefs} from "vue";
const store = useStore();
const point_no_fill ="bi bi-hand-thumbs-up";
const point_fill = "bi bi-hand-thumbs-up-fill";
const props = defineProps({
  reply:Object
})
const data = toRefs(props);
const handleClick = () => {
  store.state.layoutStore.isHide = true
  store.state.layoutStore.acceptid = data.reply.value.user_id;
  store.state.layoutStore.assessid = data.reply.value.assess_id;
}
</script>

<style scoped>
.replay{
  display: flex;
  flex: auto;
  align-items: center;
  justify-content: space-between;
  padding: 15px;
}
.el-button.is-link{
  padding: 0px;
}
</style>