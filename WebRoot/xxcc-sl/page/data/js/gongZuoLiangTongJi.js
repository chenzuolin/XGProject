layui.use(['laydate', 'form', 'jquery', 'layer', 'table'], function() {
   var laydate = layui.laydate;
   var form = layui.form;
   var $ = layui.jquery;
   var table = layui.table;
   var layer = layui.layer;

   //执行一个laydate实例
   laydate.render({
      elem: '#begin', //指定元素，开始时间
      type: "datetime"
   });
   laydate.render({
      elem: '#finish', //指定元素，结束时间
      type: "datetime"
   });

   //form监听提交,当单机【立即提交的时候调用】
   form.on('submit(formDemo)', function(data) {
      showContent();
      return false;
   });

   //表格工具条的监听
   table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      if(layEvent === 'detail') { //查看
         //id的字符串进行截取然后判断
         var type = data.zhiwu;
         switch(type) {
            case "调度员":
               //通过职务统计对应的工作量
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['调度员工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "1",
                  "name": data.name
               };
               xiangQing(d);
               break;
            case "保管员":
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['保管员工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "2",
                  "name": data.name
               };
               xiangQing(d);
               break;
            case "司磅员":
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['司磅员工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "3",
                  "name": data.name
               };
               xiangQing(d);
               break;
            case "审核员":
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['审核员工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "4",
                  "name": data.name
               };
               xiangQing(d);
               break;
            case "收费员":
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['收费员工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "5",
                  "name": data.name
               };
               xiangQing(d);
               break;
            case "天车员":
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['天车员工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "6",
                  "name": data.name
               };
               xiangQing(d);
               break;
            case "装卸员":
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['装卸员工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "7",
                  "name": data.name
               };
               xiangQing(d);
               break;
            case "短倒司机":
               $("#jiaoseOpen table").html("");
               var open = layer.open({
                  type: 1,
                  anim: 1,
                  closeBtn: 1,
                  title: ['短倒司机工作详情', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
                  content: $("#jiaoseOpen"),
                  btn: ["关闭打开层"],
                  btnAlign: "l"
               });
               layer.full(open);
               var d = {
                  "begin": data.begin,
                  "finish": data.finish,
                  "zhiwu": "8",
                  "name": data.name
               };
               xiangQing(d);
               break;
         }
      }
   });
});

//当页面加载的时候调用显示内容的方法
showContent();

//判断加载内容方法的函数，curr为当前页
function showContent() {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;
      var loading = layer.load(1);
      //显示值：对应的角色，开始日期，结束日期，出库量，入库量，过户量，挪库量，短倒量，合计
      $.ajax({
         type: "post",
         url: "/XGProject/workCountAction.do?flag=getExcelCount",
         async: true,
         data: {
            'begin': $("#begin").val(),
            "finish": $("#finish").val(),
            "name": $("#name").val()
         },
         dataType: "json",
         success: function(data) {
            layer.close(loading);
            if(data == null || data == "" || data == undefined || data == []) {
               layer.alert("无数据！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度 
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               widht: w,
               height: 'full-130',
               cellMinWidth: 100,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'zhiwu', //列显示的值
                     title: '职务', //显示的标题
                     width: 100, //列的宽度
                     sort: false, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'begin',
                     title: '起始日期',
                     width: 170,
                     sort: false,
                  }, {
                     field: 'finish',
                     title: '结束日期',
                     width: 170,
                  }, {
                     field: 'name',
                     title: '操作人',
                     sort: true,
                  }, {
                     field: 'ruku',
                     title: '入库量(吨)',
                     sort: false,
                     edit: false //是否可编辑
                  }, {
                     field: 'chuku',
                     title: '出库量(吨)',
                     sort: false
                  }, {
                     field: 'guohu',
                     title: '过户量(吨)',
                     sort: false
                  }, {
                     field: 'nuoku',
                     title: '挪库量(吨)',
                     sort: false
                  }, {
                     field: 'duandao',
                     title: '短倒量(吨)',
                     sort: false
                  }, {
                     field: 'sum',
                     title: '总合计(吨)',
                     sort: false
                  }, {
                     fixed: 'right',
                     width: 130,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ]
            });
            $("#showContent tbody").html("");
            $.each(data, function(i, obj) {
               $("#showContent tbody").append("<tr><td>" + obj.zhiwu + "</td><td>" + obj.begin + "</td>" +
                  "<td>" + obj.finish + "</td><td>" + obj.name + "</td><td>" + obj.ruku + "</td>" +
                  "<td>" + obj.chuku + "</td><td>" + obj.guohu + "</td><td>" + obj.nuoku + "</td>" +
                  "<td>" + obj.duandao + "</td><td>" + obj.sum + "</td></tr>");
            });
         },
         error: function() {
            layer.close(loading);
            layer.alert("获取数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

//工作量统计详情函数
function xiangQing(d) {
   layui.use(['jquery', 'table', 'layer'], function() {
      var $ = layui.jquery;
      var table = layui.table;
      var layer = layui.layer;
      var loading = layer.load(1);
      $.ajax({
         type: "post",
         url: "/XGProject/workCountAction.do?flag=getWorkWeightMingXi",
         async: true,
         data: d,
         dataType: "json",
         success: function(data) {
            layer.close(loading);
            var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度 
            table.render({
               data: data, //返回的json数据
               elem: '#jiaose', //显示数据的容器
               widht: w,
               height: 'full-130',
               cellMinWidth: 60,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'dingdanhao', //列显示的值
                     title: '订单编号', //显示的标题
                     width: 60, //列的宽度
                     sort: false, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'type',
                     title: '业务类型',
                     width: 100,
                     sort: true
                  }, {
                     field: 'name',
                     title: '操作人',
                     sort: true,
                  }, {
                     field: 'time',
                     title: '操作时间',
                     width: 170,
                     sort: false
                  }, {
                     field: 'faqiTime',
                     title: '订单发起时间',
                     width: 170,
                  }, {
                     field: 'client',
                     title: '客户名称',
                     sort: true
                  }, {
                     field: 'clientNumber',
                     title: '客户单号',
                     sort: false,
                     edit: false //是否可编辑
                  }, {
                     field: 'goodsName',
                     title: '货物名称',
                     sort: false
                  }, {
                     field: 'workweight',
                     title: '操作重量(吨)',
                     sort: false
                  }, {
                     field: 'worknumber',
                     title: '操作件数',
                     sort: false
                  }]
               ]
            });
         },
         error: function() {
            layer.close(loading);
            layer.alert("获取数据错误！", {
               icon: 2,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}