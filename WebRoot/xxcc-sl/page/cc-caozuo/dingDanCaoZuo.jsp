<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="utf-8">
      <title>仓储管理系统-订单操作</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

      <!--引入layui css-->
      <link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../css/public.css" media="all">
   </head>

   <body>
      <input type="hidden" value="<%=request.getSession().getAttribute("iulistId")%>" id="dengluren" />
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane" action="">
            <fieldset>
               <legend>订单操作</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">业务类型</label>
                     <div class="layui-input-block">
                        <select name="type" id="type">
                           <option value="出库订单" selected>出库订单</option>
                           <option value="入库订单">入库订单</option>
                           <option value="盘库订单">盘库订单</option>
                           <option value="挪库订单">挪库订单</option>
                           <option value="短倒订单">短倒订单</option>
                        </select>
                     </div>
                  </div>
                  <!--<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">搜索订单</label>
                     <div class="layui-input-block">
                        <select name="type" id="type">
                           <option value="出库订单" selected>出库订单</option>
                           <option value="入库订单">入库订单</option>
                           <option value="盘库订单">盘库订单</option>
                           <option value="挪库订单">挪库订单</option>
                           <option value="短倒订单">短倒订单</option>
                        </select>
                     </div>
                  </div>-->
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <button class="layui-btn" lay-submit="" lay-filter="query">立即查询</button>
                  </div>
               </div>
            </fieldset>
         </form>
         <!--显示主要内容的容器-->
         <table id="showContent" lay-filter="demo"></table>

         <!--表格工具条-->
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe631;</span>开始操作</a>
         </script>

      </div>
      <!--<hr class="layui-bg-orange" />-->
   </body>
   <!--引入layui js-->
   <script src="../../plugin/layui/layui.js"></script>

   <!--引入自写js-->
   <script src="js/dingDanCaoZuo.js"></script>

   <!--引入文本背景显示js-->
   <script src="../../js/call-client.js"></script>
   <script>
      inputBg();
   </script>

</html>