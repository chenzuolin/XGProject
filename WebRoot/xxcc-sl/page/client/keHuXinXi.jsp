<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>仓储管理系统-客户信息</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <link rel="stylesheet" href="../../css/public.css" />

      <script src="../../js/DaoChu/jquery1.9.0.min.js"></script>
      <!--layui js-->
      <script src="../../plugin/layui/layui.js"></script>
      <!--自写js-->
      <script src="js/keHuXinXi.js"></script>

      <!-- 导出excel插件 -->
      <script src="../../js/DaoChu/tableExport.js"></script>
      <script src="../../js/DaoChu/jquery.base64.js"></script>
      <script src="../../js/DaoChu/jszip-utils.js"></script>
      <script src="../../js/DaoChu/xlsx.core.min.js"></script>
   </head>

   <body>
      <div class="layui-fluid">
         <form class="layui-form layui-form-pane" action="">
            <fieldset>
               <legend>客户查询</legend>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label" style="background-color: #009688; color: #fff;">
                        <i class="layui-icon" style="vertical-align: middle;">&#xe615;</i> 搜索</label>
                     <div class="layui-input-block">
                        <input type="text" name="" placeholder="请输入客户信息" id="search" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <button class="layui-btn" lay-submit="" lay-filter="search">立即查询</button>
                     <a class="layui-btn layui-btn-warm layui-btn-sm" style="color: black;" onClick="javascript:document.location.href='/XGProject/xxcc-sl/page/client/clientdaochu.jsp';">导出
                     </a>
                  </div>
               </div>
            </fieldset>
         </form>
         <!--以下是内容页-->
         <table id="showContent" lay-filter="table" style="display: none;"></table>

         <!--分页显示容器-->
         <div>
            <div id="paging" style="float: left; "></div>
         </div>

         <!--table导航工具条的demo-->
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe61d;</span>查看</a>
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del"><span class="layui-icon">&#xe640;</span>停用</a>
         </script>

         <hr class="layui-bg-red" />
      </div>
   </body>

</html>