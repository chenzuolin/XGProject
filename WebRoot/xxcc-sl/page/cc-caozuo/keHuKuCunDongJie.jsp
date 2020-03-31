<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

   <head>
      <meta charset="UTF-8">
      <title>库存管理系统-客户库存冻结</title>
      <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <!--layui css-->
      <link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
      <link rel="stylesheet" href="../../css/public.css" media="all">
      <style>
         /*显示当页合计容器的样式*/
         
         #content-sum {
            width: 800px;
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
   <%
	  	int dongjie = 0;//冻结权限
	  	int jiedong = 0;//解冻权限
	
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("冻结客户库存")){
				dongjie++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("解冻客户库存")){
				jiedong++;
			}
		}
	%>
   <body>
   <input type="hidden" value="<%=dongjie %>" id="dongjie" />
   <input type="hidden" value="<%=jiedong %>" id="jiedong" />
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
                  <button type="reset" class="layui-btn layui-btn-primary" id="daochu">重置</button>
               </div>
            </div>
         </form>


         <!--主要内容显示容器-->
         <table id="showContent" lay-filter="demo">
         </table>
         <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe64f;</span>冻结</a>
            <a class="layui-btn layui-btn-sm" lay-event="edit"><span class="layui-icon">&#xe64d;</span>解冻</a>
         </script>
         <!--分页显示容器-->
         <div id="paging" style="text-align: left; display: inline-block;"></div>

         <!--显示每页的数据合计-->
         <table id="content-sum">
            <tr>
               <td align="right">当页合计：</td>
               <td align="center"><span></span></td>
               <td align="center"><span></span></td>
            </tr>
         </table>

         <hr class="layui-bg-orange" />

         <!--冻结打开容器-->
         <div id="dongjieOpen" style="display: none; margin: 10px;">
            <form class="layui-form layui-form-pane">
               <div class="layui-form-item">
                  <label class="layui-form-label">冻结重量</label>
                  <div class="layui-input-block">
                     <input type="text" name="DJW" id="DJW" required lay-verify="required|number" placeholder="请输入冻结重量" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <div class="layui-form-item">
                  <label class="layui-form-label">备注</label>
                  <div class="layui-input-block">
                     <input type="text" name="beizhu" placeholder="请输入备注" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <button class="layui-btn" style="display: none;" lay-submit lay-filter="dongjie" id="dongjie">立即提交</button>
            </form>
         </div>

         <!--解冻打开容器-->
         <div id="jiedongOpen" style="display: none; margin: 10px;">
            <form class="layui-form layui-form-pane">
               <div class="layui-form-item">
                  <label class="layui-form-label">解冻重量</label>
                  <div class="layui-input-block">
                     <input type="text" name="JDW" id="JDW" required lay-verify="required|number" placeholder="请输入冻结重量" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <div class="layui-form-item">
                  <label class="layui-form-label">备注</label>
                  <div class="layui-input-block">
                     <input type="text" name="beizhu" placeholder="请输入备注" autocomplete="off" class="layui-input">
                  </div>
               </div>
               <button class="layui-btn" style="display: none;" lay-submit lay-filter="jiedong" id="jiedong">立即提交</button>
            </form>
         </div>
      </div>
      <!--layui js-->
      <script src="../../plugin/layui/layui.js"></script>
      <script src="../../js/call-client.js"></script>
      <!--自写js-->
      <script src="js/keHuKuCunDongJie.js"></script>
      <script>
         inputBg();
      </script>
   </body>

</html>