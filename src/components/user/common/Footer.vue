<template>
  <div class="footer_box_card">
    <p style="padding-top: 12px">Copyright © 2023 在线学习平台</p>
    <p>作者：Wali</p>
  </div>
</template>
<script setup>
import {useStore} from "vuex";
const store = useStore();
</script>
<style>
.footer_box_card {
  width: 100%;
  color: gray;
  margin: 12px 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 12px;
}
</style>
