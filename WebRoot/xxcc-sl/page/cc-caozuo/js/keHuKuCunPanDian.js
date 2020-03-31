layui.use(['form', 'jquery', 'layer', 'table', 'laydate'], function() {
   var form = layui.form;
   var $ = layui.jquery;
   var table = layui.table;
   var layer = layui.layer;
   var laydate = layui.laydate;

   //form监听提交,当单机【立即提交的时候调用】
   form.on('submit(formDemo)', function(data) {
      showContent(1);
      return false;
   });

   //在客户名称下拉框中当键盘按下弹起的时候，将该文本框的值改变为小写
   $(".layui-form-select .layui-select-title input").keyup(function() {
      $(this).val($(this).val().toLowerCase());
   });

   var cgoodsId;
   var shengyuWeight;
   var clientId;
   var goodsId;
   //表格工具条的监听
   table.on('tool(demo)', function(obj) {
      var data = obj.data;
      var layEvent = obj.event;
      cgoodsId = data.cgid;
      shengyuWeight = data.shengyuweight;
      clientId = data.clientId;
      goodsId = data.goodsId;

      if(layEvent == "detail") {
         //如果单机盘点的时候触发
         //打开盘点对应的显示层
         var index = layer.open({
            type: 1,
            anim: 1,
            closeBtn: 2,
            content: $("#pandianOpen"),
            btn: ["立即提交", '关闭'],
            title: ['盘点客户库存', 'font-size:16px;'],
            skin: 'layui-layer-blue',
            area: ["450px", '300px'],
            yes: function() {
               $("#pandian").click();
            },
            btn2: function(index) {
               layer.close(index);
            },
            success: function(layero, index) {
               layer.tips('盈库：客户减少相应库存重量，亏库：客户增加相应库存重量！', '#YKweight', {
                  tips: 1,
                  time: 6000
               });
            }
         });
      }
   });

   //盘点form表单提交
   form.on('submit(pandian)', function(obj) {
      var data = obj.field;
      if(parseFloat(data.YKweight) > parseFloat(shengyuWeight)) {
         layer.msg("盈库重量不能大于剩余重量！", {
            icon: 2,
            time: 3000
         });
         return false;
      }
      layer.confirm("确定提交吗？", {
         icon: 3,
         anim: 4,
         closeBtn: 2,
         title: ['系统提示', 'font-size:16px;'],
         skin: 'layui-layer-blue',
      }, function() {
         $.ajax({
            type: "post",
            url: "/XGProject/checksClientGoods.do?flag=FaQiChecksClientGoods",
            async: false,
            data: {
               "client": clientId,
               "goods": goodsId,
               "ccgoodsBeforeWeight": shengyuWeight,
               "interiorUser": data.interiorUser,
               "ccgoodsSurplus": data.YKweight,
               "ccgoodsLoss": data.KKweight,
               "ccgoodsRemark": data.beizhu
            },
            dataType: "text",
            success: function(data) {
               if(data.indexOf("提交成功") != -1) {
                  layer.alert("提交成功！", {
                     icon: 1,
                     anim: 4,
                     closeBtn: 2,
                     title: ['系统提示', 'font-size:16px;'],
                     skin: 'layui-layer-blue',
                  }, function() {
                     document.location.reload();
                  });
               } else {
                  layer.alert("提交失败！", {
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

   //当点击盘点记录的时候触发
   $("#jilu").click(function() {
      laydate.render({
         elem: '#begin' //指定元素
      });
      laydate.render({
         elem: '#finish' //指定元素
      });
      clientName("#clientJilu");

      panDianJiLu(1);

      var jiluopen = layer.open({
         type: 1,
         anim: 1,
         closeBtn: 1,
         content: $("#pandianjilu"),
         btnAlign: 'l',
         title: ['盘点客户库存记录', 'font-size:16px;'],
         skin: 'layui-layer-blue',
         maxmin: true,
//       btn: ["关闭打开层"],
//       btnAlign: 'r',
//       yes: function(index) {
//          layer.close(index);
//       }
      });
      layer.full(jiluopen);
   });

   //盘点记录form表单提交
   form.on('submit(pandianjilu)', function(obj) {
      panDianJiLu(1);
      return false;
   });
});

//加载盘点记录函数
function panDianJiLu(curr) {
   layui.use(['element', 'jquery', 'table', 'layer', 'laypage'], function() {
      var $ = layui.jquery;
      var table = layui.table;
      var layer = layui.layer;
      var laypage = layui.laypage;
      var element = layui.element;

      $.ajax({
         type: "post",
         url: "/XGProject/checksClientGoods.do?flag=getChecksClientGoodsAll",
         async: false,
         data: {
            "time": new Date().getTime(),
            "begin": $("#begin").val(),
            "finish": $("#finish").val(),
            "goodsName": $("#goodsNameJilu").val(),
            "jiancheng": $("#clientJilu").val(),
            "pageNow": curr
         },
         dataType: "json",
         success: function(obj) {
            if(obj == null || obj[0].result == null || obj[0].result == "null") {
               layer.alert("无盘点记录！", {
                  icon: 5,
                  anim: 4,
                  closeBtn: 2,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
            }
            var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度
            table.render({
               data: obj, //返回的json数据
               elem: '#showContentJilu', //显示数据的容器
               width: w,
               height: 'full-215',
               cellMinWidth: 100,
               limit: 1000000, //默认显示的行数
               even: true,
               cols: [
                  [{
                     field: 'id', //列显示的值
                     title: '编号', //显示的标题
                     width: 130,
                     sort: false, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'client',
                     title: '客户名称',
                     sort: false,
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     sort: false,
                  }, {
                     field: 'mingcheng',
                     title: '货物名称',
                     width: 100,
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     sort: false,
                     edit: false //是否可编辑
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                     sort: false
                  }, {
                     field: 'shuxing',
                     title: '货物属性',
                     width: 100,
                     sort: false
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     sort: false
                  }, {
                     field: 'yuanweight',
                     title: '原重量',
                     sort: false
                  }, {
                     field: 'yingweight',
                     title: '盈库重量',
                     sort: false
                  }, {
                     field: 'kuiweight',
                     title: '亏库重量',
                     sort: false
                  }, {
                     field: 'time',
                     title: '盘点时间',
                     sort: false
                  }, {
                     field: 'pandianren',
                     title: '盘点人',
                     sort: false
                  }, {
                     field: 'beizhu',
                     title: '备注',
                     sort: false
                  }]
               ]
            });
            if(parseInt(curr) == 1) {
               //分页的渲染
               laypage.render({
                  elem: 'pagingJilu', //显示分页的容器
                  count: parseInt(obj[0].pageCount) * 30, //显示的总条数
                  limit: 30,
                  layout: ['prev', 'page', 'next', 'skip'],
                  jump: function(obj, first) {
                     //obj包含了当前分页的所有参数，比如：
                     //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
                     if(!first) {
                        panDianJiLu(obj.curr);
                     }
                  }
               });
            }
         },
         error: function() {
            layer.alert("获得盘点数据错误！", {
               icon: 2,
               anim: 4,
               closeBtn: 2,
               title: ['系统提示', 'font-size:16px;'],
               skin: 'layui-layer-blue',
            });
         }
      });
   });
}

//当页面加载的时候调用查询对应客户的方法
clientName("#client");

//当页面加载的时候调用显示内容的方法
showContent(1);

//显示内容的方法
function showContent(curr) {
   layui.use(['element', 'table', 'laypage', 'jquery', 'layer'], function() {
      var table = layui.table,
         laypage = layui.laypage,
         layer = layui.layer,
         element = layui.element,
         $ = layui.jquery;
      //用ajax访问数据
      var client = $("#client").val(); //获得客户名称文本框中的值
      var goodsName = $("#goodsName").val(); //获得货物名称中的值
      var loading = layer.load();
      $.ajax({
         type: "post",
         url: "/XGProject/clientGoods.do?flag=getClientGoodsAll",
         async: true,
         data: {
            "time": new Date().getTime(),
            "goodsName": goodsName,
            "jiancheng": client,
            "pageNow": curr,
            'pageRow': 10000
         },
         dataType: "json",
         success: function(data) {
            layer.close(loading);
            if(data == null || data[0].result == null) {
               layer.alert("无客户库存记录！", {
                  icon: 5,
                  closeBtn: 2,
                  anim: 4,
                  title: ['系统提示', 'font-size:16px;'],
                  skin: 'layui-layer-blue',
               });
               $("#content-sum tr td").eq(1).text("重量合计：0吨");
               return true;
            }
            var weight_shengyu = 0; //剩余重量的相加

            $.each(data, function(i, obj) {
               obj.xuhao = (i + 1);
               weight_shengyu += parseFloat(obj.shengyuweight);
            });
            var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度  
            table.render({
               data: data, //返回的json数据
               elem: '#showContent', //显示数据的容器
               width: w,
               height: 'full-90',
               cellMinWidth: 80,
               page: true,
               limit: 100, //默认显示的行数
               limits: [100, 200, 500],
               even: true,
               cols: [
                  [{
                     field: 'xuhao', //列显示的值
                     title: '序号', //显示的标题
                     width: 60, //列的宽度
                     sort: false, //是否产生排序
                     fixed: true //是否是固定列宽
                  }, {
                     field: 'clientName',
                     title: '客户名称',
                     width: 160,
                     sort: true,
                  }, {
                     field: 'pinlei',
                     title: '货物品类',
                     sort: true,
                  }, {
                     field: 'goodsName',
                     title: '货物名称',
                     sort: true
                  }, {
                     field: 'guige',
                     title: '货物规格',
                     width: 160,
                     sort: true,
                     edit: false //是否可编辑
                  }, {
                     field: 'caizhi',
                     title: '货物材质',
                     sort: false
                  }, {
                     field: 'shuxing',
                     title: '货物属性',
                     sort: false
                  }, {
                     field: 'chandi',
                     title: '货物产地',
                     sort: true
                  }, {
                     field: 'shengyuweight',
                     title: '剩余重量（吨）',
                     width: 130,
                     sort: false
                  }, {
                     fixed: 'right',
                     width: 150,
                     align: 'center',
                     toolbar: '#barDemo'
                  }]
               ] //设置表头
            });

            $("#content-sum tr td").eq(2).text("重量合计：" + weight_shengyu.toFixed(3) + "吨");

            //如果是第一次调用此方法或者是当点击查询的时候进行执行下面分页的方法
            //          if(parseInt(curr) == 1) {
            //             //分页的渲染
            //             laypage.render({
            //                elem: 'paging', //显示分页的容器
            //                count: parseInt(data[0].pageCount) * 30, //显示的总条数
            //                limit: 30,
            //                layout: ['prev', 'page', 'next', 'skip'],
            //                jump: function(obj, first) {
            //                   //obj包含了当前分页的所有参数，比如：
            //                   //如果不是第一次执行就调用显示数据的方法，例如当点击下一页或者上一页的时候调用
            //                   if(!first) {
            //                      showContent(obj.curr);
            //                   }
            //                }
            //             });
            //          }
         },
         error: function() {
            layer.alert("数据加载错误！", {
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