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
      <link rel="stylesheet" href="../../plugin/font-awesome-4.7.0/css/font-awesome.min.css">
   </head>

   <body>
      <div class="layui-fluid">
         <fieldset class="buttons">
            <legend style="font-size: 1.4rem;">操作员管理</legend>
            <button class="layui-btn" id="ADD"><i class="fa fa-plus" style="padding-right: 6px;"></i>新增操作员</button>
         </fieldset>
         <!--表格内容-->
         <table id="demo" lay-filter="datatable"></table>
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">停用</a>
         </script>
         <hr class="layui-bg-red" />
      </div>
      <!--编辑操作员-->
      <div id="edit" style="display: none;">
         <form class="layui-form" action="">
            <input type="hidden" id="oid" name="oid" />
            <div class="layui-form-item">
               <label class="layui-form-label">真实姓名：</label>
               <div class="layui-input-block">
                  <input type="text" name="name" class="layui-input" id="name1" readonly="">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">所属部门：</label>
               <div class="layui-input-block">
                  <select name="department" lay-verify="required" lay-search="" id="department1">
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">所属班组：</label>
               <div class="layui-input-block">
                  <select name="team" lay-verify="required" lay-search="" id="team1">
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">职务：</label>
               <div class="layui-input-block">
                  <select name="duty" lay-verify="required" lay-search="" id="duty1">
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">密码：</label>
               <div class="layui-input-block">
                  <input type="text" name="password" lay-verify="required|pass" class="layui-input" id="password1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">联系电话：</label>
               <div class="layui-input-block">
                  <input type="text" name="tel" lay-verify="phone" class="layui-input" id="tel1">
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
                  <button class="layui-btn" lay-submit lay-filter="formDemo1" id="formDemo1">立即提交</button>
               </div>
            </div>
         </form>
      </div>
      <!--新增操作员-->
      <div id="add" style="display: none;">
         <form class="layui-form" action="">
            <div class="layui-form-item">
               <label class="layui-form-label">真实姓名：</label>
               <div class="layui-input-block">
                  <input type="text" name="name" lay-verify="required" class="layui-input" id="name">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">性别：</label>
               <div class="layui-input-block">
                  <input type="radio" name="sex" value="男" title="男">
                  <input type="radio" name="sex" value="女" title="女" checked="">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">所属部门：</label>
               <div class="layui-input-block">
                  <select name="department" lay-verify="required" lay-search="" id="department">
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">所属班组：</label>
               <div class="layui-input-block">
                  <select name="team" lay-verify="required" lay-search="" id="team">
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">职务：</label>
               <div class="layui-input-block">
                  <select name="duty" lay-verify="required" lay-search="" id="duty">
                  </select>
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">用户名：</label>
               <div class="layui-input-block">
                  <input type="text" name="username" lay-verify="required" class="layui-input" id="username">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">初始密码：</label>
               <div class="layui-input-block">
                  <input type="text" name="password" lay-verify="required" class="layui-input" id="password">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">联系电话：</label>
               <div class="layui-input-block">
                  <input type="text" name="tel" lay-verify="phone" class="layui-input" id="tel">
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
      department('#department1');
      department('#department');
      team('#team1');
      team('#team');
      duty('#duty1');
      duty('#duty');
      layui.use(['table', 'form', 'layer'], function() {
         var table = layui.table,
            form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;
         //展示已知数据
         $.ajax({
            type: 'post',
            url: '/XGProject/interiorUser.do?flag=selectInteriorUser',
            async: false,
            dataType: 'json',
            success: function(data) {
               //layui-table表格渲染
               var w = $(parent.window).width() - 235; //获取浏览器的宽，减去侧边栏的宽度  
               table.render({
                  elem: '#demo',
                  width: w,
                  height: 'full-125',
                  cellMinWidht: 60,
                  data: data,
                  cols: [
                     [ //标题栏
                        {
                           field: 'id',
                           title: '序号',
                           width: 60,
                           align: 'center'
                        }, {
                           field: 'name',
                           title: '真实姓名',
                           align: 'center',
                           sort: true

                        }, {
                           field: 'sex',
                           title: '性别',
                           align: 'center'

                        }, {
                           field: 'department',
                           title: '所属部门',
                           align: 'center',
                           sort: true

                        }, {
                           field: 'team',
                           title: '所属班组',
                           align: 'center',
                           sort: true

                        }, {
                           field: 'duty',
                           title: '职务',
                           align: 'center',
                           sort: true

                        }, {
                           field: 'username',
                           title: '用户名',
                           align: 'center'

                        }, {
                           field: 'password',
                           title: '密码',
                           align: 'center'

                        }, {
                           field: 'tel',
                           title: '联系电话',
                           align: 'center'

                        }, {
                           field: 'date',
                           title: '注册时间',
                           align: 'center'

                        }, {
                           field: 'remark',
                           title: '备注',
                           align: 'center'

                        }, {
                           fixed: 'right',
                           width: 170,
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
                  limits: [20, 30, 50],
                  limit: 20, //每页默认显示的数量
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
               layer.confirm('确定停用此操作员吗？', function(index) {
                  $.ajax({
                     type: "post",
                     url: "/XGProject/interiorUser.do?flag=stopInteriorUser",
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
               $('#oid').val(data.id);
               $('#name1').val(data.name);
               $("#department1 option").each(function() {
                  if($(this).text() == data.department) {
                     $(this).parent('select').val($(this).val());
                     form.render('select');
                  }
               });
               $("#team1 option").each(function() {
                  if($(this).text() == data.team) {
                     $(this).parent('select').val($(this).val());
                     form.render('select');
                  }
               });
               $("#duty1 option").each(function() {
                  if($(this).text() == data.duty) {
                     $(this).parent('select').val($(this).val());
                     form.render('select');
                  }
               });
               $('#password1').val(data.password);
               $("#tel1").val(data.tel);
               $("#remark1").val(data.remark);
               openindex = layer.open({
                  type: 1,
                  title: ['编辑操作员', 'font-size:16px;'],
                  content: $('#edit'),
                  area: '600px',
                  closeBtn: 2,
                  anim: 4,
                  skin: 'layui-layer-blue',
                  btn: ['确定提交', '关闭'],
                  yes: function(index, layero) {
                     //按钮【按钮一】的回调
                     $('#formDemo1').click();
                  },

               });
            }
         });
         //===============================================
         form.on('submit(formDemo1)', function(obj) {
            //用ajax的方式进行提交
            var data = obj.field;
            $.ajax({
               type: 'post',
               url: '/XGProject/interiorUser.do?flag=updateinteriorUser',
               async: false,
               //修改内容提交到服务器
               data: {
                  'iuserId': data.oid,
                  'iuserName': data.name,
                  'section': data.department,
                  'classT': data.team,
                  'interiorUserDuty': data.duty,
                  'iuserPassword': data.password,
                  'iuserTel': data.tel,
                  'iusetremark': data.remark,
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
                  layer.alert('编辑操作员数据上传错误', {
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
               title: ['新增操作员', 'font-size:16px;'],
               content: $('#add'),
               area: '600px',
               closeBtn: 2,
               anim: 4,
               skin: 'layui-layer-blue',
               btn: ['确定提交', '关闭'],
               yes: function(index, layero) {
                  //按钮【按钮一】的回调
                  $('#formDemo').click();
               },
            });

         });

         //========================================
         form.on('submit(formDemo)', function(obj) {
            //用ajax的方式进行提交
            var data = obj.field;
            $.ajax({
               type: 'post',
               url: '/XGProject/interiorUser.do?flag=addInteriorUserOk',
               async: false,
               //修改内容提交到服务器
               data: {
                  'iuserName': data.name,
                  'iuserSex': data.sex,
                  'section': data.department,
                  'classT': data.team,
                  'interiorUserDuty': data.duty,
                  'iuserLoginName': data.username,
                  'iuserPassword': data.password,
                  'iuserTel': data.tel
               },
               dataType: 'text',
               success: function(data) {
                  var ok = data.indexOf('添加成功');
                  if(ok != -1) {
                     layer.alert('添加成功', {
                        icon: 1,
                        closeBtn: 2
                     }, function() {
                        document.location.reload();
                     });
                  } else {
                     layer.alert('添加失败', {
                        icon: 5,
                        closeBtn: 2

                     })
                  }
               },
               error: function() {
                  layer.alert('新增操作员数据上传错误', {
                     icon: 2,
                     closeBtn: 2,

                  })
               }
            });
            return false;
         });
      });
   </script>

</html>