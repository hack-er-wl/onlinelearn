<template>
  <div class="notify">
    <el-tooltip content="消息中心" :offset="10">
      <el-badge :is-dot="store.state.layoutStore.isDot" @click="this.$router.push('/notice')">
        <el-icon><Bell/></el-icon>
      </el-badge>
    </el-tooltip>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {watch} from "vue";
import {data} from "@/api/socket";
const router = useRouter();
const store = useStore();
watch(data.notices,() => {
  store.state.layoutStore.isDot = true;
})
</script>

<style lang="scss" scoped>
@import "../../../style/mixin";

.notify {
  cursor: pointer;
  height: 100%;
  padding: 0 5px;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;

  &:hover {
    @include hover_bg_color();
  }
}
</style>