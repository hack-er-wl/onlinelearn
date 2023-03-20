<template>
  <el-card
      :body-style="{padding: '0px'}"
      shadow="hover"
      class="favorite_card"
      :style="{marginRight:isLast.isLast.value?'0px':'4px'}">
    <img src="../../../assets/img/cover.png" class="image" :style="{height:'180px',width:'100%',borderRadius:'4px'}"/>
    <div class="tags" style="padding:3px">
      <h5>数据结构与算法</h5>
      <div class="card_bottom">
        <h6 style="color: gray">主讲老师：和大爷</h6>
        <el-button class="like_btn" @click="store.state.Like = !store.state.Like" round size="small">
          <h6 style="color: gray;margin-right: 2px">已收藏</h6>
          <i :class="!store.state.Like?like_fill:like_no_fill" :style="{color:!store.state.Like?'#ff8800':''}"/>
        </el-button>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import {useStore} from "vuex";
import {toRefs} from "vue";
import {defineProps} from "vue";
const props = defineProps({
  isLast:Boolean
})
const isLast = toRefs(props)
const store = useStore();
const like_no_fill ="bi bi-star";
const like_fill = "bi bi-star-fill";
</script>

<style>
.favorite_card{
  height:240px;
  width:25%;
}
.card_bottom {
  margin-top: 10px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.image {
  width: 100%;
  display: block;
}
</style>
