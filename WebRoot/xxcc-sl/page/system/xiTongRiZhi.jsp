<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>仓储管理系统-系统日志</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <link rel="stylesheet" type="text/css" href="../../css/public.css"/>
      <!--layui js-->
      <script src="../../plugin/layui/layui.js"></script>

      <!--自写js-->
      <script src="js/xiTongRiZhi.js"></script>

   </head>

   <body>
      <div class="layui-fluid">
         <!--头部填写表单，搜索表单-->
         <fieldset>
            <legend>筛选条件</legend>
            <form class="layui-form layui-form-pane">
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                     <label class="layui-form-label">开始时间</label>
                     <div class="layui-input-block">
                        <input type="text" name="begin" placeholder="请输入开始时间" id="begin" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                     <label class="layui-form-label">结束时间</label>
                     <div class="layui-input-block">
                        <input type="text" name="finish" placeholder="请输入结束时间" id="finish" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                     <label class="layui-form-label">登录名</label>
                     <div class="layui-input-block">
                        <input type="text" name="loginName" id="loginName" placeholder="请输入登录名" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                     <label class="layui-form-label">操作内容</label>
                     <div class="layui-input-block">
                        <input type="text" name="content" id="content" placeholder="请输入操作内容" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                 <div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
                     <button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
                     <button type="reset" class="layui-btn layui-btn-primary" id="daochu">重置</button>
                  </div>
               </div>
            </form>
         </fieldset>

         <!--以下是内容页-->
         <table id="showContent" lay-filter="table" style="display: none;">
         </table>

         <!--分页显示容器-->
         <div id="paging" style="text-align: center;"></div>

         <hr class="layui-bg-red" />
      </div>
      <script src="../../js/call-client.js"></script>
      <script>
         inputBg();
      </script>
   </body>

</html>