﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>库存管理系统-订单查询</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<!--layui css-->
		<link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
		<link rel="stylesheet" href="../../css/public.css" media="all">
		<!--layui js-->
		<script src="../../plugin/layui/layui.js"></script>

		<!--自写js-->
		<script src="js/dingDanChaXun.js"></script>
	</head>
	<%
		if (request.getSession().getAttribute("loginName") == null
			|| request.getSession().getAttribute("iulist") == null || request.getSession().getAttribute("power")==null) {
	%>
	<script type="text/javascript">
		$(function() {
			$("body")
				.append(
					"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
		});
	</script>
	<%
		}
	%>

	<%
		int x = 0;
		int ruku = 0;
		int chuku = 0;
		int guohu = 0;
		int chukuzuofei = 0;
		int rukuzuofei = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单查询")){
				x++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("入库订单查询")){
				ruku++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("出库订单查询")){
				chuku++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("过户订单查询")){
				guohu++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("出库作废订单查询")){
				chukuzuofei++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("入库作废订单查询")){
				rukuzuofei++;
			}
		}
		if(x==0){
	%>
	<script type="text/javascript">
		$(function() {
			$("body")
				.append(
					"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
		});
	</script>
	<%
		}
	%>

	<body>
		<div class="layui-fluid">
			<!--头部填写表单，搜索表单-->
			<fieldset>
				<legend>筛选条件</legend>
				<form class="layui-form layui-form-pane">
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">客户名称</label>
							<div class="layui-input-block">
								<select name="client" id="client" lay-search>
									<option value="">请选择客户</option>
								</select>
							</div>
						</div>

						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">开始时间</label>
							<div class="layui-input-block">
								<input type="text" name="begin" placeholder="请输入开始时间" id="begin" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">结束时间</label>
							<div class="layui-input-block">
								<input type="text" name="finish" placeholder="请输入结束时间" id="finish" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">订单类型</label>
							<div class="layui-input-block">
								<select id="indent-type" name="indent-type">
									<%
										if(chuku!=0){
									%>
									<option value="出库订单">出库订单</option>
									<%
										}
									%>
									<%
										if(ruku!=0){
									%>
									<option value="入库订单">入库订单</option>
									<%
										}
									%>
									<%
										if(guohu!=0){
									%>
									<option value="过户订单">过户订单</option>
									<%
										}
									%>
									<%
										if(chukuzuofei!=0){
									%>
									<option value="出库作废订单">出库作废订单</option>
									<%
										}
									%>
									<%
										if(rukuzuofei!=0){
									%>
									<option value="入库作废订单">入库作废订单</option>
									<%
										}
									%>
								</select>
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">订单编号</label>
							<div class="layui-input-block">
								<input type="text" name="indent-number" placeholder="请输入订单编号" id="indent-number" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">客户单号</label>
							<div class="layui-input-block">
								<input type="text" name="client-number" placeholder="请输入客户单号" id="client-number" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">货物资料</label>
							<div class="layui-input-block">
								<input type="text" name="goods" placeholder="请输入货物名称或规格等" id="goods" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</fieldset>

			<!--主要内容显示容器-->
			<table id="showContent" lay-filter="demo">
			</table>

			<!--table操作工具条的demo-->
			<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe61d;</span>查看详情</a>
			</script>

			<!--分页显示容器-->
			<div id="paging" style="text-align: center;"></div>

			<!--入库时的数据表格渲染时的状态判断demo-->
			<script type="text/html" id="titleTp1">
				{{# if(d.zhuangtai=="准备入库"){ }}
				<span style="color: blue;">{{d.zhuangtai}}</span> {{# } else if(d.zhuangtai=="计划入库") { }}
				<span style="color: red;">{{d.zhuangtai}}</span> {{# } else { }}
				<span style="color: green;">{{d.zhuangtai}}</span> {{# } }}
			</script>

			<!--出库时的数据表格渲染时的状态判断demo-->
			<script type="text/html" id="titleTp2">
				{{# if(d.zhuangtai=="准备出库"){ }}
				<span style="color: blue;">{{d.zhuangtai}}</span> {{# } else if(d.zhuangtai=="计划出库") { }}
				<span style="color: red;">{{d.zhuangtai}}</span> {{# } else { }}
				<span style="color: green;">{{d.zhuangtai}}</span> {{# } }}
			</script>

			<!--过户时的数据表格渲染时的状态判断demo-->
			<script type="text/html" id="titleTp3">
				{{# if(d.zhuangtai=="正在审核"){ }}
				<span style="color: blue;">{{d.zhuangtai}}</span> {{# } else if(d.zhuangtai=="正在收费") { }}
				<span style="color: red;">{{d.zhuangtai}}</span> {{# } else { }}
				<span style="color: green;">{{d.zhuangtai}}</span> {{# } }}
			</script>

			<hr class="layui-bg-red" />
		</div>
		<script src="../../js/call-client.js"></script>
		<script>
			inputBg();
		</script>

	</body>

</html>