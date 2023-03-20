import * as echarts from "echarts";

export function initChart(option,element){
    echarts.init(document.getElementById(element)).setOption(option);
}