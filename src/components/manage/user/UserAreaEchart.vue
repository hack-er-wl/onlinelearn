<template>
  <el-container class="area_box">
    <el-card id="area" class="area"/>
    <el-card id="rate" class="rate"/>
    <el-card class="weather">
      <iframe
          scrolling="no"
          style="height: 260px;width: 100%"
          src="https://tianqiapi.com/api.php?style=tp&skin=sogou"
          frameborder="0"
          allowtransparency="true"/>
    </el-card>
  </el-container>
</template>
<script setup>
import {onMounted} from "vue";
import {initChart} from "../../../api/echart";

onMounted(()=>{
  const option_area = {
    title:{
      text:'各城市用户占比'
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      x: 'right'
    },
    series: [
      {
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 40,
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: [
          { value: 1048, name: '北京' },
          { value: 735, name: '上海' },
          { value: 580, name: '深圳' },
          { value: 484, name: '广州' },
          { value: 300, name: '其他' }
        ]
      }
    ]
  };
  const option_rate = {
    title:{
      text:'用户增长率',
      x:'center'
    },
    tooltip: {
      formatter: '{b} : {c}%'
    },
    series: [
      {
        type: 'gauge',
        detail: {
          formatter: '{value}'
        },
        data: [
          {
            value: 10,
            name: '增长率'
          }
        ]
      }
    ]
  };
  initChart(option_rate,'rate');
  initChart(option_area,'area');
});
</script>

<style lang="scss" scoped>
.area_box{
  display: flex;
  height: 50vh;
  margin-bottom: 30px;
  .area{
    width: 37%;
    height: 100%;
    margin: 10px;
  }
  .rate{
    width: 30%;
    height: 100%;
    margin: 10px;
  }
  .weather{
    width: 28%;
    height: 100%;
    margin: 10px;
  }
}
</style>