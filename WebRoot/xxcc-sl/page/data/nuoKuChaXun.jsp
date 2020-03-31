<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>库存管理系统-挪库查询</title>

		<!--layui css-->
		<link rel="stylesheet" type="text/css" href="../../plugin/layui/css/layui.css" />
		<link rel="stylesheet" href="../../css/public.css" media="all">

		<!--layui js-->
		<script src="../../plugin/layui/layui.js"></script>

		<!--自写js-->
		<script src="js/jquery1.9.0.min.js"></script>
		<script src="js/nuoKuChaXun.js"></script>
	</head>

	<body>
		<div class="layui-fluid">
			<!--头部填写表单，搜索表单-->
			<fieldset style="border: 1px solid #8D8D8D;">
				<legend>筛选条件</legend>
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
							<label class="layui-form-label">对应库位</label>
							<div class="layui-input-block">
								<select id="kuwei" name="kuwei" lay-search>
								</select>
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

			<!--table导航工具条的demo-->
			<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-sm" lay-event="detail"><span class="layui-icon">&#xe61d;</span>查看详情</a>
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