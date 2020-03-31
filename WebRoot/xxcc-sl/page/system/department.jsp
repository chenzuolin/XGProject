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
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css">
      <link rel="stylesheet" href="../../css/public.css" media="all">
      <link rel="stylesheet" href="../../plugin/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
   </head>

   <body>
      <div class="layui-fluid">
         <fieldset class="buttons">
            <legend>部门管理</legend>
            <button class="layui-btn" id="ADD"><i class="fa fa-plus" style="padding-right: 6px;"></i>新增部门</button>
         </fieldset>
         <!--表格内容-->
         <table id="demo" lay-filter="datatable"></table>
         <script type="text/html" id="barDemo">
            <!--<a class="layui-btn layui-btn-sm" lay-event="detail">查看</a>-->
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">停用</a>
         </script>
          <hr class="layui-bg-red" />
      </div>
      <!--编辑部门-->
      <div id="edit" style="display: none;">
         <form class="layui-form" action="">
            <input type="hidden" id="sid" name="sid" />
            <div class="layui-form-item">
               <label class="layui-form-label">部门名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="department" lay-verify="required" class="layui-input" id="department1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">负责人：</label>
               <div class="layui-input-block">
                  <input type="text" name="incharge" lay-verify="required" class="layui-input" id="incharge1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">备注：</label>
               <div class="layui-input-block">
                  <input type="text" name="remark" class="layui-input" id="remark1">
               </div>
            </div>
            <!--编辑form表单的提交按钮，用于表单内容的验证-->
            <div class="layui-form-item" style="display: none;">
               <div class="layui-input-block">
                  <button class="layui-btn" lay-submit lay-filter="formDemo1" id="formDemo1">立即提交</button>
               </div>
            </div>
         </form>
      </div>
      <!--添加部门-->
      <div id="add" style="display: none;">
         <form class="layui-form" action="">
            <div class="layui-form-item">
               <label class="layui-form-label">部门名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="department" lay-verify="required" class="layui-input" id="departmen">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">负责人：</label>
               <div class="layui-input-block">
                  <input type="text" name="incharge" lay-verify="required" class="layui-input" id="incharge">
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
                  <button class="layui-btn" lay-submit lay-filter="formDemo" id="formDemo">立即提交</button>
               </div>
            </div>
         </form>
      </div>
   </body>
   <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
   <script src="../../js/call-client.js" charset="utf-8"></script>
   <script>
      inputBg();
      layui.use(['table', 'form', 'jquery'], function() {
         var table = layui.table,
            form = layui.form,
            $ = layui.jquery;
         //展示已知数据
         $.ajax({
            type: 'post',
            url: '/XGProject/section.do?flag=selectSection',
            async: false,
            dataType: 'json',
            success: function(data) {
               //layui-table表格渲染
               var w=$(parent.window).width()-235;//获取浏览器的宽，减去侧边栏的宽度  
               table.render({
                  elem: '#demo',
                  width:w,
                  height: 'full-125',
                  data: data,
                  cols: [
                     [ //标题栏
                        {
                           field: 'id',
                           title: '序号',
                           width: 60

                        }, {
                           field: 'department',
                           title: '部门名称',
                           width: 200,
                           align: 'center'

                        }, {
                           field: 'incharge',
                           title: '部门负责人',
                           width: 200,
                           align: 'center'

                        }, {
                           field: 'remark',
                           title: '备注',
                           width: 200,
                           align: 'center'

                        }, {
                           fixed: 'right',
                           width: 150,
                           align: 'center',
                           toolbar: '#barDemo'

                        }
                     ]
                  ],
                  initSort: {
                     field: 'department', //排序字段，对应 cols 设定的各字段名
                     type: 'null' //排序方式  asc: 升序、desc: 降序、null: 默认排序
                  },
                  even: true,
                  page: true, //是否显示分页
                  limits: [10, 20, 50],
                  limit: 10, //每页默认显示的数量
               });
            },
            error: function() {
               alert('请求数据错误！');
            }
         });

         //监听工具条
         table.on('tool(datatable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'del') { //删除
               layer.confirm('确定停用此部门吗？', function(index) {
                  $.ajax({
                     type: "post",
                     url: "/XGProject/section.do?flag=stopSection",
                     async: false,
                     data: {
                        "id": data.id
                     },
                     dataType: "text",
                     success: function(data) {
                        var ok = data.indexOf("停用成功");
                        if(ok != -1) {
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
                     },
                     error: function() {
                        layer.alert("停用数据上传错误！", {
                           icon: 2,
                           closeBtn: 2,
                           anim: 4
                        });
                     }
                  });
                  layer.close(index);
                  //向服务端发送删除指令

               });
            } else if(layEvent === 'edit') { //编辑
               //获取对应行的数据
               $("#sid").val(data.id);
               $("#department1").val(data.department);
               $("#incharge1").val(data.incharge);
               $("#remark1").val(data.remark);
               openindex = layer.open({
                  type: 1,
                  title: ['编辑部门', 'font-size:16px;'],
                  content: $('#edit'),
                  area: '550px',
                  closeBtn: 2,
                  anim: 4,
                  skin: 'layui-layer-blue',
                  btn: ['确定提交', '关闭'],
                  yes: function(index, layero) {
                     //按钮【按钮一】的回调
                     $("#formDemo1").click();
                  },
               });
            }
         });

         //===============================
         form.on('submit(formDemo1)', function(obj) {
            //用ajax的方式进行提交
            var data = obj.field;
            $.ajax({
               type: 'post',
               url: '/XGProject/section.do?flag=updateSection',
               async: false,
               //修改内容提交到服务器
               data: {
                  'sectionId': data.sid,
                  'sectionName': data.department,
                  'sectionHuman': data.incharge,
                  'sectionRemark': data.remark,
               },
               dataType: 'text',
               success: function(data) {
                  var ok = data.indexOf('修改成功');
                  if(ok != -1) {
                     layer.alert('修改成功', {
                        icon: 1,
                        closeBtn: 2
                     }, function() {
                        document.location.reload();
                     });
                  } else {
                     layer.alert('修改失败', {
                        icon: 5,
                        closeBtn: 2

                     })
                  }
               },
               error: function() {
                  layer.alert('编辑数据上传错误', {
                     icon: 2,
                     closeBtn: 2,

                  })
               }
            });
            return false;
         });
         //======================================

         $('#ADD').click(function() {
            layer.open({
               type: 1,
               title: ['新增部门', 'font-size:16px;'],
               content: $('#add'),
               area: '550px',
               closeBtn: 2,
               anim: 4,
               skin: 'layui-layer-blue',
               btn: ['确定提交', '关闭'],
               yes: function(index, layero) {
                  $("#formDemo").click();

               },
            });

         });
         form.on('submit(formDemo)', function(obj) {
            //用ajax的方式进行提交
            var data = obj.field;
            $.ajax({
               type: 'post',
               url: '/XGProject/section.do?flag=addSection',
               async: false,
               //把data中的数据交给服务器
               data: {
                  'sectionName': data.department,
                  'sectionHuman': data.incharge,
                  'sectionRemark': data.remark,
               },
               dataType: 'text',
               success: function(data) {
                  var ok = data.indexOf('添加成功');
                  if(ok != -1) {
                     layer.alert('添加成功', {
                        icon: 1
                     }, function() {
                        document.location.reload();
                     });
                  } else {
                     layer.alert('添加失败', {
                        icon: 5
                     })
                  }
               },
               error: function() {
                  layer.alert('新增数据上传错误', {
                     icon: 2
                  })
               }
            });
            return false;
         });

      });
   </script>

</html>