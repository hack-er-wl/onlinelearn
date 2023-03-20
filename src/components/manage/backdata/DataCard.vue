<template>
  <el-card class="box-card" :style="{backgroundColor:data.color.value}">
    <div class="statistic-card" :style="{backgroundColor:data.color.value}">
      <el-statistic :value="data.value.value">
        <template #title>
          <div style="display: inline-flex; align-items: center;color: black">
            {{data.title.value}}
            <el-tooltip
                effect="dark"
                :content="data.content.value"
                placement="top">
              <el-icon style="margin-left: 4px" :size="12">
                <Warning />
              </el-icon>
            </el-tooltip>
          </div>
        </template>
      </el-statistic>
      <div class="statistic-footer">
        <div class="footer-item">
          <span>{{data.foot.value}}</span>
          <span class="green">
              {{data.num.value}}%
              <el-icon>
                <CaretTop />
              </el-icon>
            </span>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script setup>
import {toRefs} from "vue";
import {defineProps} from "vue";
const props = defineProps({
  title:String,
  value:Number,
  content:String,
  foot:String,
  num:Number,
  color:String,
})
const data = toRefs(props)
</script>

<style scoped>
.box-card{
  margin:10px;
  height: 20vh;
  width: 25%;
  display: flex;
  justify-content: center;
}
:global(h2#card-usage ~ .example .example-showcase) {
  background-color: var(--el-fill-color) !important;
}

.el-statistic {
  --el-statistic-content-font-size: 28px;
}

.statistic-card {
  height: 100%;
  width: 100%;
  border-radius: 4px;
  background-color: var(--el-bg-color-overlay);
}

.statistic-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  font-size: 12px;
  color: black;
  margin-top: 16px;
}

.statistic-footer .footer-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.statistic-footer .footer-item span:last-child {
  display: inline-flex;
  align-items: center;
  margin-left: 4px;
}

.green {
  color: black;
}
.red {
  color: var(--el-color-error);
}
</style>