<template>
  <MessageCard v-for="(n,i) in data.notices" :key="i" :notice="n"/>
</template>
<script setup>
import MessageCard from "./NoticeCard.vue";
import {data} from "../../../api/socket";
</script>
<style scoped>
</style>