<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>仓储管理系统-货物批次查询</title>
         <meta name="renderer" content="webkit">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<!--layui css-->
		<link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
		<link rel="stylesheet" href="../../css/public.css" media="all">
		<!--layui js-->
		<script src="../../plugin/layui/layui.js"></script>

		<!--自写js-->
		<script src="js/huoWuPiCiChaXun.js"></script>
	</head>
	<%
		int x = 0;//批次修改的权限
	
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("批次修改")){
				x++;
			}
		}
	%>
	<body>
		<input type="hidden" value="<%=x %>" id="power" />
		<div id="open" style="display: none; margin: 10px;">
			<form class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label">入库重量：</label>
					<div class="layui-input-block">
						<input type="text" lay-verify="required|number" id="ruWeight" name="ruWeight" placeholder="请填写剩余重量" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">入库件数：</label>
					<div class="layui-input-block">
						<input type="text" lay-verify="required|number" id="ruNumber" name="ruNumber" placeholder="请填写剩余件数" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">已出重量：</label>
					<div class="layui-input-block">
						<input type="text" lay-verify="required|number" id="chuWeight" name="chuWeight" placeholder="请填写剩余重量" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">已出件数：</label>
					<div class="layui-input-block">
						<input type="text" lay-verify="required|number" id="chuNumber" name="chuNumber" placeholder="请填写剩余件数" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<button class="layui-btn" lay-submit lay-filter="openForm" id="tijiao" style="display: none;">立即提交</button>
				</div>
			</form>
		</div>
		<div class="layui-fluid">
			<!--头部填写表单，搜索表单-->
			<fieldset style="border: 1px solid #8D8D8D;">
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
							<label class="layui-form-label">对应库位</label>
							<div class="layui-input-block">
								<select id="kuwei" name="kuwei" lay-search>
								</select>
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
							<label class="layui-form-label">货物资料</label>
							<div class="layui-input-block">
								<input type="text" name="goods" placeholder="请输入货物名称或规格等" id="goods" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md4">
							<button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</fieldset>
			<!--主要内容显示容器-->
			<table id="showContent" lay-filter="demo" >
			</table>

			<!--table导航工具条的demo-->
			<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe642;</span>修改</a>
			</script>

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