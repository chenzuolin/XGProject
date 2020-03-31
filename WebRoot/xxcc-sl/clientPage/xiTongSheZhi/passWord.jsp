<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>安全中心</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <!-- <link rel="stylesheet" href="../../css/public.css" />-->
      <style>
         blockquote.layui-elem-quote {
            border-left: 10px solid #009688;
            height: 30px;
         }
         
         .layui-breadcrumb a {
            font-size: 16px;
            font-weight: 500;
            line-height: 30px;
         }
         
         .layui-breadcrumb span[lay-separator] {
            font-size: 25px;
            color: #009688;
         }
         
         form.layui-form {
            width: 450px;
            margin-top: 5%;
            margin-left: 30%;
         }
         
         .layui-form-item label {
            font-weight: 600;
            font-size: 15px;
            text-align: left;
            background: #2F4056;
            color: #fff;
         }
      </style>

   </head>

   <body>
      <div class="layui-fluid">
         <input type="hidden" value="${client.clientPassword }" name="clientId" class="layui-input" id="password" />
         <blockquote class="layui-elem-quote">
            <span class="layui-breadcrumb" lay-separator="|">
               <a><cite>安全中心</cite></a>
               <a><cite>修改密码</cite></a>
            </span>
         </blockquote>

         <form class="layui-form" action="">
            <div class="layui-form-item">
               <label class="layui-form-label">原始密码:</label>
               <div class="layui-input-block">
                  <input type="password" name="oPassword" placeholder="请输入密码" autocomplete="off" class="layui-input" id="old">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">新密码:</label>
               <div class="layui-input-block">
                  <input type="password" name="nPassword" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input" id="new">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">确认密码:</label>
               <div class="layui-input-block">
                  <input type="password" name="cPassword" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input" id="new1">
               </div>
            </div>
            <div class="layui-form-item">
               <label class="layui-form-label">验证码:</label>
               <div class="layui-input-inline">
                  <input type="text" name="code" lay-verify="number" placeholder="" autocomplete="off" class="layui-input" id="code">
               </div>
               <a class="layui-btn" id="code1" style="float: right;">获取验证码</a>
            </div>
            <div class="layui-form-item" style="float: right;">
               <button class="layui-btn" lay-submit="" lay-filter="demo1" id="confirm">立即提交</button>
               <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
         </form>
      </div>
   </body>

   <!--layui js-->
   <script src="../../plugin/layui/layui.js"></script>
   <script src="../../js/call-client.js"></script>
   <script>
      inputBg();
      //注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作
      layui.use(['element', 'jquery', 'form', 'layer'], function() {
         var element = layui.element,
            form = layui.form,
            layer = layui.layer,
            $ = layui.jquery;

         //自定义验证规则
         form.verify({
            pass: [/^[a-zA-Z0-9]{8,12}$/, '密码必须8到12位,大小写字母和数字组合'],
         });

         //监听提交
         form.on('submit(demo1)', function(data) {
            if($(this).val() != $('#password').val()) {
               layer.alert('您填写的原始密码不正确，请重新填写！', {
                  icon: 5
               });
               return false;
            }
            layer.confirm('确定修改密码吗？', {
               type: 0,
               title: '密码修改',
               icon: 3,
               anim: 0
            }, function() {
               $.ajax({
                  type: "post",
                  url: "/XGProject/client.do?flag=updatePwd",
                  data: {
                     'clientPassword': $('#cPassword').val()
                  },
                  async: true,
                  dataType: "text",
                  success: function(data) {
                     var ok = data.indexOf("修改成功");
                     if(ok != -1) {
                        layer.alert("修改成功！", {
                           icon: 1,
                           closeBtn: 2,
                           anim: 4,
                           title: ['系统提示', 'font-size:16px;'],
                           skin: 'layui-layer-blue',
                        });
                     } else {
                        layer.alert("修改失败", {
                           icon: 1,
                           closeBtn: 2,
                           anim: 4,
                           title: ['系统提示', 'font-size:16px;'],
                           skin: 'layui-layer-blue',
                        });
                     }
                  },
                  error: function() {
                     layer.alert("数据上传错误，修改失败！", {
                        icon: 2,
                        closeBtn: 2,
                        anim: 4,
                        title: ['系统提示', 'font-size:16px;'],
                        skin: 'layui-layer-blue',
                     });
                  }
               });
            });
            return false;
         });

      });
   </script>

</html>