<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>库存管理系统-客户库存盘点</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <link rel="stylesheet" href="../../css/public.css" media="all">

      <style>
         #content-sum {
            width: 400px;
            float: right;
         }
         
         #content-sum tr td {
            font-weight: bold;
            border: 1px solid #888888;
            height: 35px;
            line-height: 35px;
            text-align: center;
         }
      </style>
   </head>

   <body>
      <div class="layui-fluid">
         <!--头部填写表单，搜索表单-->
         <form class="layui-form layui-form-pane">
            <div class="layui-row layui-col-space30 layui-elem-quote">
               <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                  <label class="layui-form-label">客户名称</label>
                  <div class="layui-input-block">
                     <select name="client" id="client" lay-search>
                        <option value="">请选择客户</option>
                     </select>
                  </div>
               </div>
               <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                  <label class="layui-form-label">货物资料</label>
                  <div class="layui-input-block">
                     <input type="text" name="goodsName" placeholder="请输入货物名称或规格等" id="goodsName" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
                  <button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
                  <a class="layui-btn layui-btn-normal" id="jilu">盘点记录</a>
               </div>
            </div>
         </form>

         <!--主要内容显示容器-->
         <table id="showContent" lay-filter="demo">
         </table>
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe631;</span>盘点</a>
         </script>
         <!--分页显示容器-->
         <div id="paging" style="text-align: center;"></div>

         <!--显示每页的数据合计-->
         <!--<table id="content-sum">
            <tr>
               <td align="right">当页合计：</td>
               <td align="center"><span></span></td>
            </tr>
         </table>-->

         <hr class="layui-bg-orange" />

         <!--盘点打开容器-->
         <div id="pandianOpen" style="display: none; margin: 10px;">
            <form class="layui-form layui-form-pane">
               <input type="hidden" value='<%=request.getSession().getAttribute("iulistId")%>' name="interiorUser" />
               <div class="layui-form-item">
                  <label class="layui-form-label">盈库重量</label>
                  <div class="layui-input-block">
                     <input type="text" name="YKweight" id="YKweight" required lay-verify="required|number" placeholder="请填写盈库重量，没有盈库请填写0" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <div class="layui-form-item">
                  <label class="layui-form-label">亏库重量</label>
                  <div class="layui-input-block">
                     <input type="text" name="KKweight" id="KKweight" required lay-verify="required|number" placeholder="请填写亏库重量，没有亏库请填写0" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <div class="layui-form-item">
                  <label class="layui-form-label">备注</label>
                  <div class="layui-input-block">
                     <input type="text" name="beizhu" placeholder="请输入备注" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <button class="layui-btn" style="display: none;" lay-submit lay-filter="pandian" id="pandian">立即提交</button>
            </form>
         </div>
      </div>

      <!--盘点记录显示容器-->
      <div id="pandianjilu" style="display: none; margin: 10px;">
         <!--头部填写表单，搜索表单-->
         <fieldset>
            <legend>筛选条件</legend>
            <form class="layui-form layui-form-pane">
               <div class="layui-row">
                  <div class="layui-inline">
                     <label class="layui-form-label">开始时间</label>
                     <div class="layui-input-block">
                        <input type="text" name="begin" placeholder="请输入开始时间" id="begin" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-inline">
                     <label class="layui-form-label">结束时间</label>
                     <div class="layui-input-block">
                        <input type="text" name="finish" placeholder="请输入结束时间" id="finish" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-inline">
                     <label class="layui-form-label">客户名称</label>
                     <div class="layui-input-block">
                        <select name="client" id="clientJilu" lay-search>
                        </select>
                     </div>
                  </div>
                  <div class="layui-inline">
                     <label class="layui-form-label">货物资料</label>
                     <div class="layui-input-block">
                        <input type="text" name="goodsNameJilu" placeholder="请输入货物名称或规格等" id="goodsNameJilu" autocomplete="off" class="layui-input">
                     </div>
                  </div>
                  <div class="layui-inline">
                     <button class="layui-btn" lay-submit lay-filter="pandianjilu">立即查询</button>
                  </div>
               </div>
            </form>
         </fieldset>
         <!--主要内容显示容器-->
         <table id="showContentJilu">
         </table>

         <!--分页显示容器-->
         <div id="pagingJilu" style="text-align: left;"></div>
      </div>
      <!--layui js-->
      <script src="../../plugin/layui/layui.js"></script>
      <!--自写js-->
      <script src="../../js/call-client.js"></script>
      <script src="js/keHuKuCunPanDian.js"></script>
      <script>
         inputBg();
      </script>
   </body>

</html>