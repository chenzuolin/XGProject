layui.use(['form', 'jquery', 'layer', 'table', 'laydate'], function() {
   var form = layui.form;
   var $ = layui.jquery;
   var table = layui.table;
   var layer = layui.layer;
   var laydate = layui.laydate;

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

   var piciId;
   //剩余重量修改form监听提交,当单机【立即提交的时候调用】
   form.on('submit(openForm)', function(data) {
      var data = data.field;

      layer.confirm("确定修改吗？", {
         icon: 3,
         closeBtn: 2,
         anim: 4,
         title: ['系统提示', 'font-size:16px;'],
         skin: 'layui-layer-blue',
      }, function(index) {
         $.ajax({
            type: "post",
            url: "/XGProject/tarehouseDetail.do?flag=updatePiCi",
            async: false,
            dataType: "text",
            data: {
               "tdetailWeight": data.ruWeight,
               "tdetailId": piciId,
               "tdetailNumber": data.ruNumber,
               "tdetailEweight": data.chuWeight,
               "tdetailEnumber": data.chuNumber,
            },
            success: function(data) {
               var ok = data.indexOf("修改成功");
               if(ok != -1) {
                  layer.alert("修改成功！", {
                     icon: 1,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  }, function() {
                     document.location.reload();
                  });
               } else {
                  layer.alert("修改失败！", {
                     icon: 5,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  });
               }
            },
            error: function() {
               layer.alert("数据上传错误！", {
                  icon: 2,
                  anim: 4,
                  closeBtn: 2,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
         });
      });
      return false;
   });

   //表格工具条的监听
   table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	   
	   if($("#power").val()=="0"){
 		  layer.alert("无修改权限！", {
               icon: 5,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
 		  return false;
 	  }
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      piciId = data.picihao;
      if(layEvent === 'detail') { //修改
         $("#ruWeight").val(data.rukuweight);
         $("#ruNumber").val(data.rukunumber);
         $("#chuWeight").val(data.chukuweight);
         $("#chuNumber").val(data.chukunumber);
         //打开修改的弹出框
         layer.open({
            type: 1,
            closeBtn: 2,
            anim: 4,
            title: ['货物批次修改', 'font-size:16px;'],
            skin: 'layui-layer-blue',
            content: $('#open'),
            area: ['500px', '350px'],
            btn: ['确定提交', '关闭'],
            yes: function() {
               $("#tijiao").click();
            },
            btn2: function(index) {
               layer.close(index);
            }
         });
      }
   });

});

//当页面加载的时候调用查询库位的方法
queryKuWei();

//查询对应的库位追加到对应的id为kuwei的下拉框中
function queryKuWei() {
   layui.use(['jquery', 'form'], function() {
      var $ = layui.jquery;
      var form = layui.form;
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
         error: function() {}
      });
   });
}

//当页面加载的时候调用显示内容的方法
showContent(1);

function showContent(curr) {
   layui.use(['jquery', 'layer', 'table', 'laypage'], function() {
      var $ = layui.jquery,
         layer = layui.layer,
         table = layui.table,
         laypage = layui.laypage;

      //用ajax请求数据
      $.ajax({
         type: "post",
         url: "/XGProject/tarehouseDetail.do?flag=getTDetailAll",
         data: {
            "time": new Date().getTime(),
            "begin": $("#begin").val(),
            "finish": $("#finish").val(),
            "kuname": $("#kuwei").val(),
            "pageNow": curr,
            "goodsName": $("#goods").val()
         },
         dataType: "json",
         success: function(data) {
            var sum_weight = 0;
            var sum_number = 0;
            if(data == null || data.length <= 0 || data[0].result == null) {
               layer.alert("无货物批次记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }

            $.each(data, function(i, item) {
               sum_weight += parseFloat(item.shenyuweight.toFixed(3)); //剩余重量相加
               sum_number += parseFloat(item.shenyunumber.toFixed(3)); //剩余件数相加
               item.xuhao = (i + 1);
            });
            var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度 
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               width: w,
               height: 'full-210',

               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'xuhao', //列显示的值
                     title: '序号', //显示的标题
                     width: 60, //列的宽度
                     sort: false, //是否产生排序
                  }, {
                     field: 'kuwei', //列显示的值
                     title: '库位名称', //显示的标题
                     width: 100, //列的宽度
                     sort: false, //是否产生排序
                  }, {
                     field: 'picihao',
                     title: '批次号',
                     width: 150,
                     sort: false,
                  }, {
                     field: 'goodsName',
                     title: '货物名称',
                     width: 100,
                     sort: false,
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     width: 150,
                     sort: false,
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                     width: 120,
                  }, {
                     field: 'shuxing',
                     title: '货物属性',
                     width: 100,
                     sort: false,
                     edit: false //是否可编辑
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     width: 100,
                     sort: false,
                  }, {
                     field: 'rukuweight',
                     title: '入库重量',
                     width: 100,
                     sort: true,
                  }, {
                     field: 'rukunumber',
                     title: '入库件数',
                     width: 100,
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     field: 'chukuweight',
                     title: '已出重量',
                     width: 100,
                     sort: true,
                  }, {
                     field: 'chukunumber',
                     title: '已出件数',
                     width: 100,
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     field: 'shenyuweight',
                     title: '剩余重量',
                     width: 100,
                     sort: true,
                  }, {
                     field: 'shenyunumber',
                     title: '剩余件数',
                     width: 100,
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     fixed: 'right',
                     width: 120,
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
            layer.alert("请求数据错误！", {
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