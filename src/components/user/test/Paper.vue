<template>
    <QuestionChoice v-for="o in 7" :key="o" :index="o"/>
    <QuestionJudge v-for="o in 4" :key="o" :index="o"/>
</template>
<script setup>
import QuestionChoice from "./QuestionChoice.vue";
import QuestionJudge from "./QuestionJudge.vue";
</script>