<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>layui</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../css/public.css" media="all">
      <link rel="stylesheet" href="../../plugin/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
   </head>

   <body>
      <div class="layui-fluid">
         <fieldset class="buttons">
            <legend style="font-size: 1.4rem;">货物类型管理</legend>
            <button class="layui-btn" id="ADD"><i class="fa fa-plus" style="padding-right: 6px;"></i>新增类型</button>
         </fieldset>
         <!--表格内容-->
         <table id="demo" lay-filter="datatable"></table>
         <script type="text/html" id="barDemo">
            <!--<a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>-->
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">停用</a>
         </script>
      </div>
      <!--编辑部门-->
      <div id="edit" style="display: none;">
         <form class="layui-form" action="">
            <div class="layui-form-item">
               <label class="layui-form-label">货物类型：</label>
               <div class="layui-input-block">
                  <input type="text" name="pinlei" lay-verify="required" class="layui-input" id="pinlei1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">描述：</label>
               <div class="layui-input-block">
                  <input type="text" name="miaoshu" lay-verify="required" class="layui-input" id="miaoshu1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">备注：</label>
               <div class="layui-input-block">
                  <input type="text" name="remark" class="layui-input" id="remark1">
               </div>
            </div>
            <div class="layui-form-item" style="display: none;">
               <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="btn1" id="btn1">立即提交</button>
               </div>
            </div>
         </form>
      </div>
      <!--添加部门-->
      <div id="add" style="display: none;">
         <form class="layui-form" action="">
            <div class="layui-form-item">
               <label class="layui-form-label">货物类型：</label>
               <div class="layui-input-block">
                  <input type="text" name="pinlei" lay-verify="required" class="layui-input" id="pinlei">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">描述：</label>
               <div class="layui-input-block">
                  <input type="text" name="miaoshu" lay-verify="required" class="layui-input" id="miaoshu">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">备注：</label>
               <div class="layui-input-block">
                  <input type="text" name="remark" class="layui-input" id="remark">
               </div>
            </div>
            <div class="layui-form-item" style="display: none;">
               <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="btn" id="btn">立即提交</button>
               </div>
            </div>
         </form>
      </div>
   </body>

   <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
   <script src="../../js/call-client.js" charset="utf-8"></script>
   <script src="js/cargoDate.js"></script>
   <script>
      inputBg();
      layui.use(['table', 'form'], function() {
         var table = layui.table,
            form = layui.form,
            $ = layui.jquery;
         //展示已知数据
         var data = goodsContent('/XGProject/goodsCategory.do?flag=selectGoodsCategory', {}, 'json');
         if(data == 'error') {
            layer.alert('货物数据获取错误!', {
               anim: 4,
               closeBtn: 2,
               icon: 2,
            })

         } else {
          var w=$(parent.window).width()-235;//获取浏览器的宽，减去侧边栏的宽度  
            table.render({
               elem: '#demo',
               height: 'full-125',
               width:w,
               data: JSON.parse(data),
               cols: [
                  [ //标题栏
                     {
                        field: 'id',
                        title: '序号',
                        width: 120

                     }, {
                        field: 'pinlei',
                        title: '货物类型',
                        width: 300,
                        align: 'center'

                     }, {
                        field: 'miaoshu',
                        title: '描述',
                        width: 300,
                        align: 'center'

                     }, {
                        field: 'remark',
                        title: '备注',
                        width: 300,
                        align: 'center'

                     }, {
                        fixed: 'right',
                        width: 200,
                        align: 'center',
                        toolbar: '#barDemo'

                     }
                  ]
               ],
               //             initSort: {
               //                field: 'time', //排序字段，对应 cols 设定的各字段名
               //                type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
               //             },
               even: true,
               page: true, //是否显示分页
               limits: [20, 30, 50],
               limit: 20, //每页默认显示的数量
            });
         }

         //监听工具条
         var hangID;
         table.on('tool(datatable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if(layEvent === 'del') { //删除
               layer.confirm('确定停用此品类吗？', function(index) {
                  var text = goodsContent("/XGProject/goodsCategory.do?flag=stopGoodsCategory", {
                     'id': data.id
                  }, 'text');
                  if(text == 'error') {
                     layer.alert('数据上传错误', {
                        icon: 2,
                        closeBtn: 2,
                        anim: 4,

                     })
                  } else {
                     if(text.indexOf('停用成功') != -1) {
                        layer.alert("停用成功！", {
                           icon: 1,
                           closeBtn: 2,
                           anim: 4
                        }, function(index) {
                           layer.close(index);
                           obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        });
                     } else {
                        layer.alert("停用失败！", {
                           icon: 1,
                           closeBtn: 2,
                           anim: 4
                        });
                     }
                  }
                  layer.close(index);
                  //向服务端发送删除指令

               });
               //编辑操作
            } else if(layEvent === 'edit') { //编辑
               //获取对应行的数据
               hangID = data.id;
               $("#pinlei1").val(data.pinlei);
               $("#miaoshu1").val(data.miaoshu);
               $("#remark1").val(data.remark);
               openindex = layer.open({
                  type: 1,
                  title: ['编辑类型', 'font-size:16px;'],
                  content: $('#edit'),
                  area: '550px',
                  closeBtn: 2,
                  anim: 4,
                  skin: 'layui-layer-blue',
                  btn: ['确定提交', '关闭'],
                  yes: function(index, layero) {
                     //按钮【按钮一】的回调
                     $('#btn1').click();
                  },
               });
            }
         });
         form.on('submit(btn1)', function(obj) {
            var data = obj.field;
            var datas = {
               'goodsCategoryId': hangID,
               'goodsCategoryName': $('#pinlei1').val(),
               'goodsCategoryDefinedTwo': $('#miaoshu1').val(),
               'goodsCategoryRemark': $('#remark1').val(),
            };
            var bianji = goodsContent('/XGProject/goodsCategory.do?flag=updateGoodsCategory', datas, 'text');
            if(bianji == 'error') {
               layer.alert('数据上传错误', {
                  icon: 2
               })
            } else {
               var ok = bianji.indexOf('修改成功');
               if(ok != -1) {
                  layer.alert('库区修改成功！', {
                     icon: 1
                  }, function() {
                     document.location.reload();
                  });
               } else {
                  layer.alert('库区修改失败！', {
                     icon: 5
                  })
               }
            }
            return false;
         })
         //添加货物数据====================
         $('#ADD').click(function() {
            layer.open({
               type: 1,
               title: ['新增类型', 'font-size:16px;'],
               content: $('#add'),
               area: '550px',
               closeBtn: 2,
               anim: 4,
               skin: 'layui-layer-blue',
               btn: ['确定提交', '关闭'],
               yes: function(index, layero) {
                  $('#btn').click();
               },
            });

         });
         form.on('submit(btn)', function(obj) {
            var data = obj.field;
            layer.confirm('确定添加货物品类吗！', {
               icon: 3
            }, function() {
               var tianjia = goodsContent('/XGProject/goodsCategory.do?flag=addGoodsCategory', {
                  'goodsCategoryName': data.pinlei,
                  'goodsCategoryDefinedTwo': data.miaoshu,
                  'goodsCategoryRemark': data.remark,
               }, 'text');
               if(tianjia == 'error') {
                  layer.alert('数据上传错误！', {
                     icon: 2
                  })
               } else {
                  var ok = tianjia.indexOf('添加成功');
                  if(ok != -1) {
                     layer.alert('货物品类添加成功！', {
                        icon: 1
                     }, function() {
                        document.location.reload();
                     });
                  } else {
                     layer.alert('货物添加失败！', {
                        icon: 5
                     })
                  }

               }
            });
            return false;
         });
      });
   </script>

</html>