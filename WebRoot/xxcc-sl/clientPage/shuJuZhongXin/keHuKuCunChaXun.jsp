<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>库存管理系统-客户库存查询</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <link rel="stylesheet" href="../../css/public.css" media="all">
   </head>

   <body>
      <div class="layui-fluid">
         <!--头部填写表单，搜索表单-->
         <fieldset>
            <legend>库存查询</legend>
            <form class="layui-form layui-form-pane">
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">货物品类</label>
                     <div class="layui-input-block">
                        <input type="text" name="pinlei" placeholder="请输入货物品类" id="pinlei" autocomplete="off" class="layui-input">
                     </div>
                  </div>

                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">货物名称</label>
                     <div class="layui-input-block">
                        <input type="text" name="mingcheng" placeholder="请输入货物名称" id="mingcheng" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">货物规格</label>
                     <div class="layui-input-block">
                        <input type="text" name="guige" placeholder="请输入货物规格" id="guige" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">货物材质</label>
                     <div class="layui-input-block">
                        <input type="text" name="caizhi" placeholder="请输入货物材质" id="caizhi" autocomplete="off" class="layui-input">
                     </div>
                  </div>
               </div>
               <div class="layui-row layui-col-space30">
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">货物属性</label>
                     <div class="layui-input-block">
                        <input type="text" name="shuxing" placeholder="请输入货物属性" id="shuxing" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <label class="layui-form-label">货物产地</label>
                     <div class="layui-input-block">
                        <input type="text" name="chandi" placeholder="请输入货物产地" id="chandi" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                     <button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
                     <button type="button" class="layui-btn layui-btn-primary" id="daochu" onClick="$('#showContent').tableExport({type:'excel', separator:';', escape:'false'});">导出</button>
                  </div>
               </div>
            </form>
         </fieldset>

         <!--主要内容显示容器-->
         <table id="showContent" lay-filter="demo" even class="layui-table" style="width: 100%; display:none;">
            <thead>
               <tr>
                  <th>序号</th>
                  <th>客户名称</th>
                  <th>货物品类</th>
                  <th>货物名称</th>
                  <th>货物规格</th>
                  <th>货物材质</th>
                  <th>货物属性</th>
                  <th>货物产地</th>
                  <th>库存重量（吨）</th>
                  <th>冻结重量（吨）</th>
               </tr>
            </thead>
            <tbody>

            </tbody>
         </table>

         <!--分页显示容器-->
         <div id="paging" style="text-align: center;"></div>

         <hr class="layui-bg-red" />
      </div>
      <!--layui js-->
      <script src="../../plugin/layui/layui.js"></script>
      <script src="../../js/DaoChu/jquery1.9.0.min.js"></script>
      <script src="../../js/call-client.js"></script>
      <!--自写js-->
      <script src="js/keHuKuCunChaXun.js"></script>
      <!-- 导出excel插件 -->
      <script src="../../js/DaoChu/tableExport.js"></script>
      <script src="../../js/DaoChu/jquery.base64.js"></script>
      <script src="../../js/DaoChu/jszip-utils.js"></script>
      <script src="../../js/DaoChu/xlsx.core.min.js"></script>
      <script>
         inputBg();
      </script>
   </body>

</html>