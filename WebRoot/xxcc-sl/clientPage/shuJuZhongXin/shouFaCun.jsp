﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>库存管理系统-收发存报表</title>
		<!--layui css-->
		<link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
		<link rel="stylesheet" href="../../css/public.css" media="all">
	</head>

	<body>
		<div class="layui-fluid">
			<!--头部填写表单，搜索表单-->
			<fieldset style="border: 1px solid #8D8D8D;">
				<legend>收发存报表</legend>
				<form class="layui-form layui-form-pane">
					<div class="layui-row layui-col-space30">
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
							<label class="layui-form-label">货物资料</label>
							<div class="layui-input-block">
								<input type="text" name="goodsName" id="goodsName" placeholder="请输入货物名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<button class="layui-btn" lay-submit lay-filter="formDemo" id="query">立即查询</button>
							<button type="button" class="layui-btn layui-btn-primary" id="daochu" onClick="$('#showContent').tableExport({type:'excel', separator:';', escape:'false'});">导出</button>
						</div>
					</div>
				</form>
			</fieldset>
			<!--以下是内容页-->
			<table id="showContent" lay-filter="table" style="display: none;">
				<thead>
					<tr>
						<th>客户名称</th>
						<th>货物品类</th>
						<th>货物名称</th>
						<th>期初库存（吨）</th>
						<th>入库合计（吨）</th>
						<th>出库合计（吨）</th>
						<th>期末库存（吨）</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>

			<!--分页显示容器-->
			<div id="test1" style="text-align: center;"></div>

		</div>
		<!--layui js-->
		<script src="../../plugin/layui/layui.js"></script>
		<script src="../../js/DaoChu/jquery1.9.0.min.js"></script>
		<script src="../../js/call-client.js"></script>
		<!--自写js-->
		<script src="js/shouFaCun.js"></script>
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