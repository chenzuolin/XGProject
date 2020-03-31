layui.use(['laydate', 'form', 'jquery', 'layer', 'table'], function() {
   var laydate = layui.laydate;
   var form = layui.form;
   var $ = layui.jquery;
   var table = layui.table;
   var layer = layui.layer;

   //当页面加载的时候调用显示内容的方法
   showContent(1);

   //执行一个laydate实例
   laydate.render({
      elem: '#begin' //指定元素，开始时间
   });
   laydate.render({
      elem: '#finish' //指定元素，结束时间
   });

   //form监听提交,当单机【立即提交的时候调用】
   form.on('submit(formDemo)', function(data) {
      showContent(1);
      return false;
   });
   //表格工具条的监听
   table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      if(layEvent === 'detail') { //查看
         layer.alert("点击的订单编号是：" + data.id, {
            icon: 1,
            closeBtn: 2,
            anim: 4,
            title: ['系统提示', 'font-size:16px;'],
            skin: 'layui-layer-blue',
         });
      }
   });
});

//当页面加载的时候调用查询库位的方法
queryKuWei();

//查询对应的库位追加到对应的id为kuwei的下拉框中
function queryKuWei() {
   layui.use(['jquery', 'layer', 'form'], function() {
      var $ = layui.jquery,
         layer = layui.layer,
         form = layui.form;
      $.ajax({
         type: "post",
         url: "/XGProject/tarehouse.do?flag=selectAjaxKuwei",
         async: false,
         dataType: "json",
         success: function(data) {
            $("#kuwei").html("");
            $.each(data, function(i, obj) {
               if(i == 0) {
                  $("#kuwei").append("<option value=''>请选择库位</option>");
               }
               $("#kuwei").append("<option value='" + obj.name + "'>" + obj.name + "</option>");
            });
            form.render('select');
         },
         error: function() {
            layer.alert("相应库位查询错误！", {
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

function showContent(curr) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;

      var begin = $("#begin").val(); //获得起始日期中的值
      var finish = $("#finish").val(); //获得结束日期中的值
      var kuwei = $("#kuwei").val(); //获得货库位文本框中的值

      $.ajax({
         type: "post",
         url: "/XGProject/shift.do?flag=ShiftgetAll&ff=ajax",
         async: true,
         data: {
            "time": new Date().getTime(),
            "begin": begin,
            "finish": finish,
            "kuName": kuwei,
            "pageNow": curr
         },
         dataType: "json",
         success: function(data) {
            if(data == null || data[0].result == null || data == undefined) {
               layer.alert("无挪库记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
               return true;
            }
            var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度 
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               width: w,
               height: 'full-160',
               limit: 1000000, //默认显示的行数
               even: true,
               id: "show",
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '订单编号', //显示的标题
                     width: 150, //列的宽度
                     sort: false, //是否产生排序
                  }, {
                     field: 'faqiren',
                     title: '挪库发起人',
                     width: 160,
                     sort: false,
                  }, {
                     field: 'time',
                     title: '发起时间',
                     width: 200,
                     sort: true,
                  }, {
                     field: 'kuwei',
                     title: '原库位',
                     width: 200,
                  }, {
                     field: 'xinkuwei',
                     title: '新库位',
                     width: 180,
                     sort: false,
                     edit: false //是否可编辑
                  }, {
                     fixed: 'right',
                     width: 200,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });
            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            if(parseInt(curr) == 1) {
               //分页的渲染
               laypage.render({
                  elem: 'paging', //显示分页的容器
                  count: parseInt(data[0].pageCount) * 30, //显示的总条数
                  limit: 30,
                  layout: ['prev', 'page', 'next', 'skip'],
                  jump: function(obj, first) {
                     //obj包含了当前分页的所有参数，比如：
                     console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                     console.log(obj.limit); //得到每页显示的条数
                     //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
                     if(!first) {
                        showContent(obj.curr);
                     }
                  }
               });
            }
         },
         error: function() {
            layer.alert("请求数据错误!", {
               icon: 5,
               closeBtn: 2,
               anim: 4,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}