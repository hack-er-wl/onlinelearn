<template>
  <div class="data">
    <AddFieldForm v-on:submit="onsubmit"/>
    <FieldTable/>
  </div>
</template>

<script setup>
import FieldTable from "@/components/manage/course/FieldTable.vue";
import AddFieldForm from "@/components/manage/course/AddFieldForm.vue";
import {onMounted, toRaw} from "vue";
import useNotification from "@/hooks/useNotification";
import {useStore} from "vuex";
import {get} from "@/network/request";
const store = useStore();
const onsubmit = async (formAddField) => {
  const res = await store.dispatch("handleAddField", toRaw(formAddField));
  if(res)
    useNotification("success","系统通知",res.result)
  else
    console.log(res);
}
onMounted(()=>{
  console.log(get("admin/query/cfield"));
})
</script>

<style lang="scss" scoped>
.data{
  height: 128.5vh;
  width: 100%;
}
</style>