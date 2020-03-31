<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>首页--layui后台管理模板</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <meta name="apple-mobile-web-app-status-bar-style" content="black">
      <meta name="apple-mobile-web-app-capable" content="yes">
      <meta name="format-detection" content="telephone=no">
      <link rel="stylesheet" href="../plugin/layui/css/layui.css" media="all" />
      <link rel="stylesheet" href="../css/index.css" media="all" />
      <link rel="stylesheet" href="../css/public.css" />
      <style type="text/css">
         #oneChart {
            width: 100%;
            height: 750px;
            margin-bottom: 5rem;
         }
         
         #monthChart {
            width: 100%;
            height: 700px;
            top: -5rem;
         }
      </style>
   </head>

   <body>
      <div class="layui-fluid">
         <!--头部填写表单，搜索表单-->
         <fieldset>
            <legend>筛选条件</legend>
            <form class="layui-form layui-form-pane">
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">开始时间</label>
                     <div class="layui-input-block">
                        <input type="text" name="begin" placeholder="请输入开始时间" id="begin" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">结束时间</label>
                     <div class="layui-input-block">
                        <input type="text" name="finish" placeholder="请输入结束时间" id="finish" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
                  </div>
               </div>
            </form>
         </fieldset>
         <!-- 图标内容-->
         <div class="layui-row">
            <!--自定义吞吐量图表-->
            <div class="layui-col-xs12 layui-col-sm6 layui-col-md6" id="oneChart"></div>
            <!--日实际出入过库量统计-->
            <div class="layui-col-xs12 layui-col-sm6 layui-col-md6" id="day1Chart"></div>
         </div>
         <div class="layui-row">
            <!--月出入过产量图表-->
            <div class="layui-col-xs12 layui-col-sm12 layui-col-md12" id="monthChart"></div>
         </div>
      </div>

   </body>
   <script type="text/javascript" src="../plugin/layui/layui.js"></script>
   <script src="../plugin/echarts.min.js"></script>
   <script src="../js/jquery1.9.0.min.js"></script>

   <!--鑫港库自定义吞吐量图表------------------------------------------------------>
   <script type="text/javascript">
      layui.use(['laydate', 'form'], function() {
         var laydate = layui.laydate;
         var form = layui.form;
         laydate.render({
            elem: '#begin',
            type: "datetime"
         });
         laydate.render({
            elem: '#finish',
            type: "datetime"
         });
         form.on("submit(formDemo)", function(data) {
            getPinLeiData();
            return false;
         });

      });
      getPinLeiData();
      //查询入、出、过的品类数据
      function getPinLeiData() {
         $.ajax({
            type: "post",
            url: "/XGProject/tidingsAction.do?flag=getChuRuGuoTunTu",
            async: false,
            data: {
               "begin": $("#begin").val(),
               "finish": $("#finish").val()
            },
            dataType: "json",
            success: function(data) {
               var tuli = ['总入库量', '总出库量', '总过户量'];
               var rukuSum = 0.0;
               var chukuSum = 0.0;
               var guohuSum = 0.0;
               var huan = "[";
               for(var i = 0; i < data.length; i++) {
                  tuli[i + 3] = data[i].pinlei;
                  rukuSum += parseFloat(data[i].ru);
                  chukuSum += parseFloat(data[i].chu);
                  guohuSum += parseFloat(data[i].guo);
                  huan += "{value: " + data[i].ru + ",name: '" + data[i].pinlei + "'},"
               }
               for(var i = 0; i < data.length; i++) {
                  huan += "{value: " + data[i].chu + ",name: '" + data[i].pinlei + "'},"
               }
               for(var i = 0; i < data.length; i++) {
                  huan += "{value: " + data[i].guo + ",name: '" + data[i].pinlei + "'},"
               }
               huan += "]";
               //alert(eval("(" + huan + ")"));
               showPinLei(tuli, rukuSum, chukuSum, guohuSum, eval("(" + huan + ")"));
               return false;
            },
            error: function() {
               alert("获取数据错误！");
            }
         });
      }

      function showPinLei(tuli, r, c, g, huan) {
         // 基于准备好的dom，初始化echarts实例
         var oneChart = echarts.init(document.getElementById('oneChart'));
         option = {
            title: {
               text: '鑫港库货物品类吞吐量统计图(吨)',
               textStyle: {
                  fontSize: 22,
               },
               subtext: '（鑫港库）',
               subtextStyle: {
                  color: '#aaa',
                  fontSize: 16
               },
               x: 'center',
               top: '3%',
               left: 120
            },
            tooltip: {
               trigger: 'item',
               formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
               orient: 'vertical',
               top: '10%',
               right: '10%',
               itemGap: 15,
               itemWidth: 50,
               itemHeight: 20,
               data: tuli
            },
            series: [{
                  name: '鑫港库总吞吐量',
                  type: 'pie',
                  selectedMode: 'single',
                  center: ['45%', '50%'],
                  radius: [0, '36%'],

                  label: {
                     normal: {
                        position: 'inner'
                     }
                  },
                  labelLine: {
                     normal: {
                        show: false
                     }
                  },
                  data: [{
                        value: r,
                        name: '总入库量',
                        //selected: true
                     },
                     {
                        value: c,
                        name: '总出库量'
                     },
                     {
                        value: g,
                        name: '总过户量'
                     }
                  ]
               },
               {
                  name: '货物品类产量',
                  type: 'pie',
                  center: ['45%', '50%'],
                  radius: ['45%', '62%'],
                  label: {
                     //图标信息提示框的样式	
                     normal: {
                        formatter: '{a|{a}}{abg|}\n{hr|}\n  {b|{b}：}{c|{c}}  {per|{d}%}',
                        backgroundColor: '#eee', //背景颜色
                        borderColor: '#aaa', //边框颜色
                        borderWidth: 1, //边框大小
                        borderRadius: 4, //边框圆角
                        shadowBlur: 3,
                        shadowOffsetX: 2,
                        shadowOffsetY: 2,
                        shadowColor: '#999', //阴影的颜色
                        padding: [0, 7],
                        rich: {
                           a: {
                              color: '#000',
                              lineHeight: 22,
                              align: 'center'
                           },
                           abg: {
                              backgroundColor: 'aaa',
                              width: '100%',
                              align: 'right',
                              height: 22,
                              borderRadius: [4, 4, 0, 0]
                           },
                           //分割线样式
                           hr: {
                              borderColor: '#aaa',
                              width: '100%',
                              borderWidth: 0.5,
                              height: 0
                           },
                           b: {
                              color: '#000',
                              fontSize: 16,
                              lineHeight: 33
                           },
                           c: {
                              fontSize: 16
                           },
                           per: {
                              color: '#fff',
                              backgroundColor: '#334455',
                              padding: [3, 4],
                              borderRadius: 2
                           }
                        }
                     }
                  },
                  data: huan
               }
            ]
         };
         oneChart.setOption(option, true);
         return false;
      };
   </script>

   <!--鑫港库每月出入过产量图---------------------------------------------------------------->
   <script type="text/javascript">
      //月出入过产量图
      var myChart = echarts.init(document.getElementById('monthChart'));
      //显示的月数
      var month = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];
      //显示的年，从服务器分组获得
      var years;
      var dataMax = 0;
      var ruku;
      var chuku;
      var guohu;

      //用ajax的方式请求服务器的数据
      $.ajax({
         type: "post",
         url: "/XGProject/tidingsAction.do?flag=getTuBiao",
         async: false,
         dataType: "json",
         async: false,
         success: function(data) {
            years = data[0].year;
            years = years.reverse();
            var xx = "{" + data[0].chuku + "}";
            var ss = "{" + data[0].ruku + "}";
            var bb = "{" + data[0].guohu + "}";
            chuku = dataFormatter(eval("(" + xx + ")"));
            ruku = dataFormatter(eval("(" + ss + ")"));
            guohu = dataFormatter(eval("(" + bb + ")"));
         },
         error: function() {
            alert("获取数据错误！");
         }
      });
      var option = {
         baseOption: {
            timeline: {
               axisType: 'category',
               // realtime: false,
               // loop: false,
               autoPlay: false,
               // currentIndex: 2,
               playInterval: 1000,
               // controlStyle: {
               //     position: 'left'
               // },
               data: years,
               label: {
                  formatter: function(s) {
                     return(new Date(s)).getFullYear() + "年";
                  }
               },
               rewind: true,
               playInterval: 4000
            },
            title: {
               subtext: '（鑫港库）',
               subtextStyle: {
                  color: '#aaa',
                  fontSize: 16
               },
               x: 'center',
            },
            //工具提示。当鼠标悬浮在图表上是弹出的系统信息
            tooltip: {
               trigger: 'axis',
               axisPointer: { // 坐标轴指示器，坐标轴触发有效
                  type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
               }
            },
            legend: {
               x: "right",
               data: ['入库量', '出库量', '过户量'],
               itemGap: 15,
               itemWidth: 50,
               itemHeight: 20
            },
            toolbox: {
               show: true,
               left: '8%',
               feature: {
                  //dataView : {show: true, readOnly: false},
                  magicType: {
                     show: true,
                     type: ['line', 'bar']
                  },
                  //restore: {show: true},
                  saveAsImage: {
                     show: true
                  }
               }
            },
            //calculable: true,
            grid: {
               top: '15%',
               bottom: '12%',
               left: '8%',
               right: '10%',
               //width: '100%',
               //height: '80%',
               containLabel: false

            },
            xAxis: [{
               'type': 'category',
               'axisLabel': {
                  'interval': 0
               },
               'data': month,
               splitLine: {
                  show: false
               }
            }],
            yAxis: [{
               type: 'value',
               name: '重量（吨）',
               max: function(dataMax) {
                  return parseInt(dataMax.max) + 3000;
               }
            }],
            series: [{
                  name: '入库量',
                  type: 'bar',
                  label: {
                     normal: {
                        show: true,
                        position: 'top',
                        formatter: '{c}'
                     }
                  },
               },
               {
                  name: '出库量',
                  type: 'bar',
                  label: {
                     normal: {
                        show: true,
                        position: 'top',
                        formatter: '{c}'
                     }
                  },
               },
               {
                  name: '过户量',
                  type: 'bar',
                  label: {
                     normal: {
                        show: true,
                        position: 'top',
                        formatter: '{c}'
                     }
                  },
               },
               {
                  name: '业务类型占比量（年）',
                  tooltip: {
                     trigger: 'item',
                     formatter: "{a} <br/>{b} : {c} ({d}%)"
                  },
                  z: 100,
                  type: 'pie',
                  center: ['88%', '22%'],
                  radius: '28%'
               }
            ]
         },
         options: optionsData()
      };

      function dataFormatter(obj) {
         var pList = month;
         var temp;
         var maxYear = years[0];
         var minYear = years[years.length - 1];
         for(var year = minYear; year <= maxYear; year++) {
            var max = 0;
            var sum = 0;
            temp = obj[year];
            for(var i = 0, l = temp.length; i < l; i++) {
               max = Math.max(max, temp[i]);
               sum += temp[i];
               obj[year][i] = {
                  name: pList[i],
                  value: temp[i]
               }
            }
            obj[year + 'max'] = Math.floor(max / 100) * 100;
            obj[year + 'sum'] = sum;
            dataMax = Math.max(dataMax, max);
         }
         dataMax = parseInt(dataMax + 100);
         return obj;
      }

      function optionsData() {
         var minYear = years[years.length - 1];
         var maxYear = years[0];
         var d = [];
         for(var y = maxYear; y >= minYear; y--) {
            var x = {
               title: {
                  text: y + '年鑫港库存吞吐量(吨)',
                  textStyle: {
                     fontSize: 22,
                  },
                  x: 'center',
               },
               series: [{
                     data: ruku[y]
                  },
                  {
                     data: chuku[y]
                  },
                  {
                     data: guohu[y]
                  },
                  {
                     data: [{
                           name: '入库量',
                           value: ruku[y + 'sum'],
                        },
                        {
                           name: '出库量',
                           value: chuku[y + 'sum']
                        },
                        {
                           name: '过户量',
                           value: guohu[y + 'sum']
                        }
                     ]
                  }
               ]
            }
            d.push(x);
         }
         return d;
      }
      myChart.setOption(option);
   </script>

</html>