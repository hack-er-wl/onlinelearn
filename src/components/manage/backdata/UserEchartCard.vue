<template>
  <el-container class="statistic_top">
    <el-card id="pie" class="pie"/>
    <el-card id="line" class="line"/>
  </el-container>
  <el-container class="statistic_bottom">
    <el-card id="bar" class="bar"/>
  </el-container>
</template>

<script setup>
import {initChart} from "../../../api/echart";
import {onMounted} from "vue";
onMounted(()=>{
  const option_pie = {
    title:{
      text:"用户占比",
    },
    legend: {
      x: 'right',
      data: ['注册用户', '讲师用户', '访客用户', '其他用户']
    },
    tooltip:{},
    series: [
      {
        type: 'pie',
        radius: ['50%', '70%'],
        avoidLabelOverlap: false,
        label: {
          show: false,
          position: 'center'
        },
        labelLine: {
          show: false
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '30',
            fontWeight: 'bold'
          }
        },
        data: [
          { value: 1000, name: '注册用户' },
          { value: 500, name: '讲师用户' },
          { value: 1000, name: '访客用户' },
          { value: 100, name: '其他用户' },
        ]
      }
    ]
  };
  const option_line = {
    title:{
      text:"各类用户每天的数据变化"
    },
    xAxis: {
      data: ['注册用户', '讲师用户', '访客用户', '其他用户']
    },
    yAxis: {},
    tooltip:{
      trigger: 'axis',
      show: true,
      // 自定义修改悬浮内容
      formatter: function (params) {
        let result = '';
        params.forEach(function (item) {
          result +=
              '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:' +
              item.color +
              '"></span>';
          if (item.seriesIndex == 0) {
            result += '昨日用户' + '<b>' + item.value + '</b>' + '<br>';
          }
          if(item.seriesIndex == 1){
            result += '今日增加：' + '<b>' + item.value + '</b>';
          }
        });
        return result;
      }
    },
    legend: {
      x:'right',
      data: ['昨日数据', '今日数据']
    },
    series: [
      {
        name:"昨日数据",
        data: [900, 490, 800, 75],
        type: 'line',
        stack: 'x'
      },
      {
        name:"今日数据",
        data: [100, 10, 200, 25],
        type: 'line',
        stack: 'x'
      }
    ]
  };
  const option_bar = {
    title:{
      text:"各类用户每个月的数据变化"
    },
    xAxis: {
      data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
    },
    yAxis: {},
    tooltip:{
      trigger: 'axis',
      show: true,
      // 自定义修改悬浮内容
      formatter: function (params) {
        let result = '';
        params.forEach(function (item) {
          result +=
              '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:' +
              item.color +
              '"></span>';
          if (item.seriesIndex == 0) {
            result += '注册用户' + '<b>' + item.value + '</b>' + '<br>';
          }
          if(item.seriesIndex == 1){
            result += '讲师用户：' + '<b>' + item.value + '</b>' + '<br>';
          }
          if(item.seriesIndex == 2){
            result += '访客用户：' + '<b>' + item.value + '</b>' + '<br>';
          }
          if(item.seriesIndex == 3){
            result += '其他用户：' + '<b>' + item.value + '</b>';
          }
        });
        return result;
      }
    },
    legend: {
      x:'right',
      data: ['注册用户', '讲师用户', '访客用户', '其他用户']
    },
    series: [
      {
        name:"注册用户",
        data: [83,77,109,100,111,103,120,90,85,87,91,73],
        type: 'bar',
        stack: 'x'
      },
      {
        name:"讲师用户",
        data: [35,43,56,30,29,45,42,44,38,56,45,30],
        type: 'bar',
        stack: 'x'
      },
      {
        name:"访客用户",
        data: [85,74,102,90,101,113,100,80,80,84,92,75],
        type: 'bar',
        stack: 'x'
      },
      {
        name:"其他用户",
        data: [36,33,46,30,39,41,46,40,39,52,45,34],
        type: 'bar',
        stack: 'x'
      }
    ]
  };
  initChart(option_pie,'pie');
  initChart(option_line,'line');
  initChart(option_bar,'bar');
});
</script>

<style lang="scss" scoped>
.statistic_top{
  display: flex;
  height: 50vh;
  .pie{
    width: 36%;
    height: 100%;
    margin: 10px;
  }
  .line{
    width: 64%;
    height: 100%;
    margin: 10px;
  }
}
.statistic_bottom{
  height: 50vh;
  margin-top: 20px;
  .bar{
    margin: 10px;
    width: 100%;
    height: 100%;
  }
}
</style>