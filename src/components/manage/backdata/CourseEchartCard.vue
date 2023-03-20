<template>
  <el-container class="statistic_top">
    <el-card id="pie" class="pie"/>
    <el-card id="line" class="line"/>
  </el-container>
  <el-container class="statistic_bottom">
    <el-card id="bar" class="bar"/>
    <el-card id="radar" class="radar"/>
  </el-container>
  <el-container class="statistic_mix">
    <el-card id="mix" class="mix"/>
  </el-container>
</template>

<script setup>
import {initChart} from "../../../api/echart";
import {onMounted} from "vue";
onMounted(()=>{
  const option_pie = {
    title:{
      text:"领域占比",
    },
    legend: {
      x: 'right',
      data: ['IT领域', '设计领域', '其他领域', '工程领域']
    },
    tooltip:{},
    series: [
      {
        type: 'pie',
        data: [
          {
            value: 335,
            name: 'IT领域'
          },
          {
            value: 234,
            name: '设计领域'
          },
          {
            value: 1548,
            name: '其他领域'
          },
          {
            value: 548,
            name: '工程领域'
          }
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
        stack: 'x',
        areaStyle: {}
      },
      {
        name:"今日数据",
        data: [100, 10, 200, 25],
        type: 'line',
        stack: 'x',
        areaStyle: {}
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
        type: 'line',
        smooth: true,
        stack: 'x',
      },
      {
        name:"讲师用户",
        data: [35,43,56,30,29,45,42,44,38,56,45,30],
        type: 'line',
        smooth: true,
        stack: 'x'
      },
      {
        name:"访客用户",
        data: [85,74,102,90,101,113,100,80,80,84,92,75],
        type: 'line',
        smooth: true,
        stack: 'x'
      },
      {
        name:"其他用户",
        data: [36,33,46,30,39,41,46,40,39,52,45,34],
        type: 'line',
        smooth: true,
        stack: 'x'
      }
    ]
  };
  const option_radar = {
    title: {
      text: 'Basic Radar Chart'
    },
    legend: {
      x:'right',
      data: ['Allocated Budget', 'Actual Spending']
    },
    tooltip:{},
    radar: {
      indicator: [
        { name: 'Sales', max: 6500 },
        { name: 'Administration', max: 16000 },
        { name: 'Information Technology', max: 30000 },
        { name: 'Customer Support', max: 38000 },
        { name: 'Development', max: 52000 },
        { name: 'Marketing', max: 25000 }
      ]
    },
    series: [
      {
        name: 'Budget vs spending',
        type: 'radar',
        data: [
          {
            value: [4200, 3000, 20000, 35000, 50000, 18000],
            name: 'Allocated Budget'
          },
          {
            value: [5000, 14000, 28000, 26000, 42000, 21000],
            name: 'Actual Spending'
          }
        ]
      }
    ]
  };
  const option_mix = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        crossStyle: {
          color: '#999'
        }
      }
    },
    toolbox: {
      feature: {
        dataView: { show: true, readOnly: false },
        magicType: { show: true, type: ['line', 'bar'] },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    legend: {
      data: ['Evaporation', 'Precipitation', 'Temperature']
    },
    xAxis: [
      {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        axisPointer: {
          type: 'shadow'
        }
      }
    ],
    yAxis: [
      {
        type: 'value',
        name: 'Precipitation',
        min: 0,
        max: 250,
        interval: 50,
        axisLabel: {
          formatter: '{value} ml'
        }
      },
      {
        type: 'value',
        name: 'Temperature',
        min: 0,
        max: 25,
        interval: 5,
        axisLabel: {
          formatter: '{value} °C'
        }
      }
    ],
    series: [
      {
        name: 'Evaporation',
        type: 'bar',
        tooltip: {
          valueFormatter: function (value) {
            return value + ' ml';
          }
        },
        data: [
          2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3
        ]
      },
      {
        name: 'Precipitation',
        type: 'bar',
        tooltip: {
          valueFormatter: function (value) {
            return value + ' ml';
          }
        },
        data: [
          2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3
        ]
      },
      {
        name: 'Temperature',
        type: 'line',
        yAxisIndex: 1,
        tooltip: {
          valueFormatter: function (value) {
            return value + ' °C';
          }
        },
        data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
      }
    ]
  };
  initChart(option_pie,'pie');
  initChart(option_line,'line');
  initChart(option_bar,"bar");
  initChart(option_radar,"radar");
  initChart(option_mix,"mix")
})
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
    width: 64%;
    height: 100%;
  }
  .radar{
    width: 36%;
    height: 100%;
    margin: 10px;
  }
}
.statistic_mix{
  height: 70vh;
  margin-top: 20px;
  .mix{
    margin: 10px;
    width: 100%;
    height: 100%;
  }
}
</style>