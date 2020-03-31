<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-订单待处理</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="../../css/public.css" media="all">

	</head>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" action="">
				<fieldset>
					<legend>订单审批</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">操作人</label>
							<div class="layui-input-block">
								<input type="text" placeholder="请输入申请人或审批人" name="shenqingren" id="shenqingren" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">开始时间</label>
							<div class="layui-input-block">
								<input type="text" name="begin" placeholder="请选择开始时间" id="begin" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<label class="layui-form-label">结束时间</label>
							<div class="layui-input-block">
								<input type="text" name="finish" placeholder="请选择结束时间" id="finish" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12 layui-col-sm6 layui-col-md3">
							<button class="layui-btn" lay-submit="" lay-filter="query" id="chaxun">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</fieldset>
			</form>
			<!--显示主要内容的容器-->
			<table id="showContent" lay-filter="demo"></table>

			<hr class="layui-bg-red" />
		</div>
	</body>
	<script src="../../plugin/layui/layui.js" charset="utf-8"></script>
	<script src="../../js/call-client.js" charset="utf-8"></script>
	<script>
		layui.use(['element', 'form', 'table', 'layer', 'laydate'], function() {
			var element = layui.element,
				form = layui.form,
				table = layui.table,
				layer = layui.layer,
				$ = layui.jquery;
			var laydate = layui.laydate;
			laydate.render({
				elem: "#begin",
				type: "datetime"
			});
			laydate.render({
				elem: "#finish",
				type: "datetime"
			});

			form.on('submit(query)', function() {
				show();
				return false;
			});
		});
		//当页面第一次加载的时候调用查询数据的方法
		show();

		function show() {
			layui.use(['jquery', 'layer', 'table'], function() {
				var $ = layui.jquery;
				var layer = layui.layer;
				var table = layui.table;
				var begin = $("#begin").val();
				var finish = $("#finish").val();
				var faqiren = $("#shenqingren").val();
				$.ajax({
					type: "post",
					url: "/XGProject/updateRecordAction.do?flag=getShenPiJiLu",
					async: true,
					data: {
						"begin": begin,
						"finish": finish,
						"urfaqiren": faqiren
					},
					dataType: "json",
					success: function(datas) {
						if(datas == null || datas[0].result == null) {
							layer.alert("无审批记录数据！", {
								icon: 5,
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
								skin: 'layui-layer-blue',
							});
						}
						table.render({
							elem: '#showContent',
							height: 'full-125',
							cellMinWidth: 100, //全局定义常规单元格的最小宽度，layui 2.2.1 新增
							data: datas,
							page: true,
							cols: [
								[{
									field: 'id',
									title: '审批编号',
									width: 100,
									sort: true,
									fixed: "left"
								}, {
									field: 'bianhao',
									title: '订单编号',
									width: 150,
								}, {
									field: 'caozuobh',
									title: '操作编号',
									width: 180,
								}, {
									field: 'client',
									title: '货主',
									width: 120,
								}, {
									field: 'type',
									title: '订单类型',
									width: 120,
								}, {
									field: 'faqiren',
									title: '申请人',
									width: 120,
								}, {
									field: 'miaoshu',
									title: '申请内容',
									width: 120,
								}, {
									field: 'faqitime',
									title: '发起时间',
									width: 180,
									sort: true,
								}, {
									field: 'zhuangtai',
									title: '申请状态',
									width: 120,
								}, {
									field: 'yijiren',
									title: '一级审批人',
									width: 120,
								}, {
									field: 'yijijie',
									title: '一级审批结果',
									width: 120,
								}, {
									field: 'yijiyijian',
									title: '一级审批意见',
									width: 120,
								}, {
									field: 'yijitime',
									title: '一级审批时间',
									width: 180,
								}, {
									field: 'erjiren',
									title: '二级审批人',
									width: 120,
								}, {
									field: 'erjijie',
									title: '二级审批结果',
									width: 120,
								}, {
									field: 'erjiyijian',
									title: '二级审批意见',
									width: 120,
								}, {
									field: 'erjitime',
									title: '二级审批时间',
									width: 180,
								}, {
									field: 'sanjiren',
									title: '三级审批人',
									width: 120,
								}, {
									field: 'sanjijie',
									title: '三级审批结果',
									width: 120,
								}, {
									field: 'sanjiyijian',
									title: '三级审批意见',
									width: 120,
								}, {
									field: 'sanjitime',
									title: '三级审批时间',
									width: 180,
								}, {
									field: 'beizhu',
									title: '备注',
									width: 120,
								}]
							]
						});
					},
					error: function() {
						layer.alert("获取数据失败！", {
							icon: 5,
							closeBtn: 2,
							anim: 4,
							title: ['系统提示', 'font-size:16px;'],
							skin: 'layui-layer-blue',
						});
					}
				});
			})
		}
	</script>

</html>