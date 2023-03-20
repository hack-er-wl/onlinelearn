<template>
  <div class="assess">
    <div style="display: flex">
      <el-avatar :size="30" :src="data.assess.value.user_head"/>
      <div style="margin-left: 10px">
        <h6 style="color: gray">{{data.assess.value.user_name}}</h6>
        <el-rate
            v-model="data.assess.value.assess_rate"
            disabled
            show-score
            text-color="#ff9900"
        />
        <p style="font-size: 15px">{{data.assess.value.assess_content}}</p>
        <h6 style="color: gray;margin-top: 10px"><i class="bi bi-clock"/> {{data.assess.value.assess_time}}</h6>
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
        <p style="color: gray;font-size: 1px;align-items: center;padding:0">{{data.assess.value.assess_pointer}}</p>
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
  assess:Object
})
const data = toRefs(props);
const handleClick = () => {
  store.state.layoutStore.isHide = true
  store.state.layoutStore.assessid = data.assess.value.assess_id;
  store.state.layoutStore.acceptid = data.assess.value.user_id;
}
</script>

<style scoped>
.assess{
  display: flex;
  flex: auto;
  align-items: center;
  justify-content: space-between;
  padding: 15px 0 15px 0;
}
</style>