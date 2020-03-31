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
            <legend>班组管理</legend>
            <button class="layui-btn" id="ADD"><i class="fa fa-plus" style="padding-right: 6px;"></i>新增班组</button>
         </fieldset>
         <!--表格内容-->
         <table id="demo" lay-filter="datatable"></table>
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">停用</a>
         </script>
         <hr class="layui-bg-red" />
      </div>
      <!--编辑班组窗口-->
      <div id="edit" style="display: none;">
         <form class="layui-form" action="">
            <input type="hidden" id="tid" name="tid" />
            <div class="layui-form-item">
               <label class="layui-form-label">班组名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="team" lay-verify="required" class="layui-input" id="team1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">所属部门：</label>
               <div class="layui-input-block">
                  <select name="department" lay-verify="required" lay-search="" id="department1">
                     <option value=""></option>
                  </select>
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
                  <input type="text" name="title" class="layui-input" id="remark1">
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
      <!--新增编组窗口-->
      <div id="add" style="display: none;">
         <form class="layui-form" action="">
            <div class="layui-form-item">
               <label class="layui-form-label">班组名称：</label>
               <div class="layui-input-block">
                  <input type="text" name="team" lay-verify="required" class="layui-input" id="team">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">所属部门：</label>
               <div class="layui-input-block">
                  <select name="department" lay-verify="required" lay-search="" id="department">
                     <option value=""></option>
                  </select>
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
         </form>
      </div>
   </body>
   <script src="../../plugin/layui/layui.js" charset="utf-8"></script>
   <script src="../../js/call-client.js" charset="utf-8"></script>
   <script>
      inputBg();
      department('#department');
      department('#department1');

      layui.use(['table', 'form', 'jquery'], function() {
         var table = layui.table,
            form = layui.form,
            $ = layui.jquery;
         var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度   
         //展示已知数据
         $.ajax({
            type: 'post',
            url: '/XGProject/classT.do?flag=selectclassT',
            async: false,
            dataType: 'json',
            success: function(data) {
               table.render({
                  elem: '#demo',
                  width: w,
                  height: 'full-125',
                  data: data,
                  cols: [
                     [ //标题栏
                        {
                           field: 'id',
                           title: '序号',
                           width: 60

                        }, {
                           field: 'team',
                           title: '班组名称',
                           width: 200,
                           align: 'center'

                        }, {
                           field: 'department',
                           title: '所属部门',
                           width: 200,
                           align: 'center'

                        }, {
                           field: 'incharge',
                           title: '班组负责人',
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
                     field: 'team', //排序字段，对应 cols 设定的各字段名
                     type: 'null' //排序方式  asc: 升序、desc: 降序、null: 默认排序
                  },
                  even: true,
                  page: true, //是否显示分页
                  limits: [10, 20, 50],
                  limit: 10, //每页默认显示的数量
                  loading: true

               });
            },
            error: function() {
               layer.alert('请求数据错误！');
            }

         });

         //监听工具条
         table.on('tool(datatable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if(layEvent === 'del') { //删除
               layer.confirm('确定停用此班组吗？', function(index) {
                  $.ajax({
                     type: "post",
                     url: "/XGProject/classT.do?flag=stopClassT",
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
                           layer.alert("班组停用失败！", {
                              icon: 1,
                              closeBtn: 2,
                              anim: 4
                           });
                        }
                     },
                     error: function() {
                        layer.alert("数据上传错误！", {
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
               $("#tid").val(data.id);
               $("#team1").val(data.team);
               //遍历select内包含的option的文本
               $("#department1 option").each(function() {
                  //判断option中的文本是否与点击表格内对应行的部门文本是否一致，如果一致就选中对应option的value的值，
                  //把这个值赋给select，显示的是value对应的文本
                  if($(this).text() == data.department) {
                     $(this).parent('select').val($(this).val());
                     form.render('select');
                     return;
                  }
               });
               $("#incharge1").val(data.incharge);
               $("#remark1").val(data.remark);
               openindex = layer.open({
                  type: 1,
                  title: ['编辑班组', 'font-size:16px;'],
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
         //编辑班组数据时第一验证、第二提交
         form.on('submit(formDemo1)', function(obj) {
            //用ajax的方式进行提交
            var data = obj.field;
            $.ajax({
               type: 'post',
               url: '/XGProject/classT.do?flag=updateClassT',
               async: false,
               //修改内容提交到服务器
               data: {
                  'classId': $('#tid').val(),
                  'className': $('#team1').val(),
                  'section': $('#department1').val(),
                  'classHuman': $('#incharge1').val(),
                  'classRemark': $('#remark1').val(),
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

         //===========================================
         $('#ADD').click(function() {
            layer.open({
               type: 1,
               title: ['新增班组', 'font-size:16px;'],
               content: $('#add'),
               area: '550px',
               closeBtn: 2,
               anim: 4,
               skin: 'layui-layer-blue',
               btn: ['确定提交', '关闭'],
               yes: function(index, layero) {
                  //把input的内容设置被变量用于验证内容是否为空，是否可以用text()这个方法
                  var team = $('#team').val();
                  var department = $('#department').val();
                  var incharge = $('#incharge').val();
                  var remark = $('#remark').val();
                  if(team == null || team == undefined || team == '') {
                     layer.alert('班组名称不能为空', {
                        icon: 0
                     });
                     return false
                  }
                  if(department == null || department == undefined || department == '') {
                     layer.alert('部门名称不能为空', {
                        icon: 0
                     });
                     return false
                  }
                  if(incharge == null || incharge == undefined || incharge == '') {
                     layer.alert('负责人称不能为空', {
                        icon: 0
                     });
                     return false
                  }

                  //按钮【按钮一】的回调
                  $.ajax({
                     type: 'post',
                     url: '/XGProject/classT.do?flag=addClassT',
                     async: false,
                     //把data中的数据交给服务器
                     data: {
                        'className': team,
                        'section': department,
                        'classHuman': incharge,
                        'classRemark': remark,
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
                        layer.alert('新增班组数据上传错误', {
                           icon: 2
                        })
                     }
                  });
               },
            });

         });
      });
   </script>

</html>