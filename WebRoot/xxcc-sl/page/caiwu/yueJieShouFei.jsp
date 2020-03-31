<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>仓储管理系统-月结收费</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../../plugin/layui/css/layui.css"
	media="all">
<link rel="stylesheet" href="../../css/public.css" media="all">
</head>

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
						<button class="layui-btn" lay-submit lay-filter="formDemo"
							id="query">立即查询</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</fieldset>
		<!--显示内容容器-->
		<table id="showContent" lay-filter="showcontent"></table>

		<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe61d;</span>查看详情</a>
				<a class="layui-btn layui-btn-sm layui-btn-danger" lay-event="edit"><span class="layui-icon">&#xe65e;</span>收取费用</a>
			</script>

		<!--分页容器-->
		<div id="paging" style="text-align: center;"></div>

		<hr class="layui-bg-orange" />
	</div>
	<!--收取费用模态框-->
	<div id="shouquOpen" style="display: none; margin: 10px;">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label">应收总费用</label>
				<div class="layui-input-block">
					<input type="text" name="yingshou" lay-verify="required|number"
						readonly='readonly' id="yingshou" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">实收费用</label>
				<div class="layui-input-block">
					<input type="text" name="shishou" lay-verify="required|number"
						id="shishou" placeholder="请输入实收费用" autocomplete="off"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">缴费人</label>
				<div class="layui-input-block">
					<input type="text" name="jiaofei" lay-verify="required"
						placeholder="请输入缴费人" autocomplete="off" id="jiaofei"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">支付方式</label>
				<div class="layui-input-block">
					<select name="zhifu" id="zhifu" lay-verify="required"
						lay-filter="zhifu"></select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">票据类型</label>
				<div class="layui-input-block">
					<select name="piaoju" id="piaoju" lay-verify="required"></select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
				<div class="layui-input-block">
					<input type="text" name="beizhu" id="beizhu" placeholder="请输入备注"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<button class="layui-btn" lay-submit lay-filter="shouquDemo"
				id="shouquDemo" style="display: none;">立即提交</button>
		</form>
	</div>
</body>

<script src="../../plugin/layui/layui.js" charset="utf-8"></script>
<script src="../../js/call-client.js" charset="utf-8"></script>
<script src="js/yueJieShouFei.js"></script>
<script src="../cc-caozuo/js/zhiFuPiaoJu.js" charset="utf-8"></script>

</html>