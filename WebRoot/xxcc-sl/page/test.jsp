<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>layui</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <script src="../js/echarts.min.js"></script>
      <script src="../js/jquery1.9.0.min.js"></script>
   </head>

   <body>
      <div id="main" style="width: 100%;height:600px; margin-top: 100px;"></div>
   </body>
   <script>
      var myChart = echarts.init(document.getElementById('main'));
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
               // y: 0,
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
               subtext: '',
               left: '45%',
            },
            tooltip: {},
            legend: {
               x: "right",
               data: ['入库量', '出库量', '过户量'],
            },
            calculable: true,
            grid: {
               top: 80,
               bottom: 80,
               right: 280
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
               // max: 53500
               max: dataMax
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
                  name: '业务类型占比量',
                  type: 'pie',
                  center: ['92%', '20%'],
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
         return obj;
      }

      function optionsData() {
         var minYear = years[years.length - 1];
         var maxYear = years[0];
         var d = [];
         for(var y = maxYear; y >= minYear; y--) {
            var x = {
               title: {
                  text: y + '年鑫港库存吞吐量'
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