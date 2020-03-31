layui.use(['form', 'jquery', 'layer', 'table'], function() {
   var form = layui.form;
   var $ = layui.jquery;
   var table = layui.table;
   var layer = layui.layer;

   //form监听提交,当单机【立即提交的时候调用】
   form.on('submit(formDemo)', function(data) {
      showContent(1);
      return false;
   });

   //剩余重量修改form监听提交,当单机【立即提交的时候调用】
   form.on('submit(openForm)', function(data) {
      layer.confirm("确定修改吗？", {
         icon: 3,
         closeBtn: 2,
         anim: 4,
         title: ['系统提示', 'font-size:16px;'],
         skin: 'layui-layer-blue',
      }, function(index) {
         $.ajax({
            type: "post",
            url: "/XGProject/tarehouseGoods.do?flag=updateTarehouseGoods",
            async: false,
            data: {
               "tgoodsId": data.field.tgoodsId,
               "tgoodsWeight": data.field.tgoodsWeight,
               "tgoodsNumber": data.field.tgoodsNumber
            },
            dataType: "text",
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
                     icon: 1,
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
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      if(layEvent === 'detail') { //修改
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
         //打开修改的弹出框
         $("#TGoodsId").val(data.id); //设置对应的库位库存编号
         $("#weight").val(data.shenYUWeight); //设置对应的库位库存的剩余重量
         $("#number").val(data.shenYUNumber); //设置对应的库位库存的剩余件数
         layer.open({
            type: 1,
            closeBtn: 2,
            anim: 4,
            title: ['库位库存修改', 'font-size:16px;'],
            skin: 'layui-layer-blue',
            content: $('#open'),
            area: ['500px', '300px'],
            btn: ["确定提交", "关闭"],
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
      var kuwei = $("#kuwei").val();
      var goods = $("#goods").val();
      $.ajax({
         type: "post",
         url: "/XGProject/tarehouseGoods.do?flag=selectHouseGoodsAjax",
         data: {
            "kuwei": kuwei,
            "huowu": goods,
            "pageNow": curr
            // 传入当前页
         },
         dataType: "json",
         success: function(data) {
            var sum_weight = 0;
            var sum_number = 0;
            if(data == null || data.length <= 0 || data[0].qingkong == "clean") {
               layer.alert("无库位库存记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }

            $.each(data, function(i, item) {
               sum_weight += parseFloat(item.shenYUWeight.toFixed(3)); //剩余重量相加
               sum_number += parseFloat(item.shenYUNumber.toFixed(3)); //剩余件数相加
               item.xuhao = (i + 1);
            });
           var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               width: w,
               height: 'full-165',
               cellMinWidth: 60,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'xuhao', //列显示的值
                     title: '序号', //显示的标题
                     sort: false, //是否产生排序
                     width: 60
                  }, {
                     field: 'kuweiName', //列显示的值
                     title: '库位名称', //显示的标题
                     sort: true //是否产生排序
                  }, {
                     field: 'goodsName',
                     title: '货物名称',
                     sort: true
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     sort: true
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                  }, {
                     field: 'shuxin',
                     title: '货物属性',
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     sort: true
                  }, {
                     field: 'shenYUWeight',
                     title: '剩余重量',
                     sort: true,
                  }, {
                     field: 'shenYUNumber',
                     title: '剩余件数',
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     fixed: 'right',
                     width: 135,
                     align: 'right',
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