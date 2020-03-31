<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>仓储管理系统-月结收费记录</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../../plugin/layui/css/layui.css"
	media="all">
<link rel="stylesheet" href="../../css/public.css" media="all">
</head>
<%
		int update = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("下站费修改")){
				update++;
			}
		}
	%>

<body>
	<div class="layui-fluid">
		<!--头部填写表单，搜索表单-->
		<fieldset>
			<legend>筛选条件</legend>
			<form class="layui-form layui-form-pane" id="formRi">
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
						<label class="layui-form-label">客户名称</label>
						<div class="layui-input-block">
							<select name="client" id="client" lay-search>
								<option value="">请选择客户</option>
							</select>
						</div>
					</div>
					<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
						<label class="layui-form-label">开始时间</label>
						<div class="layui-input-block">
							<input type="text" name="begin" placeholder="请输入开始时间" id="begin"
								autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
						<label class="layui-form-label">结束时间</label>
						<div class="layui-input-block">
							<input type="text" name="finish" placeholder="请输入结束时间"
								id="finish" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
						<label class="layui-form-label">收费人</label>
						<div class="layui-input-block">
							<input type="text" name="shoufeiren" placeholder="请输入收费人"
								id="shoufeiren" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
						<button class="layui-btn" lay-submit lay-filter="formDemo"
							id="query">立即查询</button>
						<a class="layui-btn layui-btn-primary" id="daochu">导出</a>
					</div>
				</div>
			</form>
		</fieldset>
		<!--显示内容容器-->
		<table id="showContent" lay-filter="showcontent"></table>

		<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-sm" lay-event="edit"><span class="layui-icon">&#xe61d;</span>查看详情</a>
			</script>

		<!--分页容器-->
		<div id="paging" style="text-align: center;"></div>

		<hr class="layui-bg-orange" />
	</div>

</body>

<script src="../../plugin/layui/layui.js" charset="utf-8"></script>
<script src="../../js/jquery1.9.0.min.js" charset="utf-8"></script>
<script src="../../js/call-client.js" charset="utf-8"></script>
<script src="js/yueJieShouFeiJiLu.js"></script>

</html>