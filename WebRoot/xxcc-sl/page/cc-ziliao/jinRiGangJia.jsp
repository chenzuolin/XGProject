<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>layui</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="../../css/public.css" media="all">
		<link rel="stylesheet" href="../../plugin/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
	</head>

	<body>
		<div class="layui-fluid">
			<fieldset class="buttons">
				<legend style="font-size: 1.4rem;">今日钢价</legend>
				<button class="layui-btn" id="xzbm"><i class="fa fa-plus" style="padding-right: 6px;"></i>新增价格</button>
			</fieldset>
			<!--表格内容-->
			<table id="demo" lay-filter="datatable"></table>
			<script type="text/html" id="barDemo">
				<!--<a class="layui-btn layui-btn-mini" lay-event="detail">查看</a>-->
				<a class="layui-btn layui-btn-sm" lay-event="edit">编辑</a>
				<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="del">停用</a>
			</script>
		</div>
		<!--编辑部门-->
		<div id="edit" style="display: none;">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">部门名称</label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">负责人</label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="required" autocomplete="off" class="layui-input">
					</div>
				</div>
			</form>
		</div>
		<!--添加部门-->
		<div id="add" style="display: none;">
			<form class="layui-form" action="">
				<div class="layui-form-item">
					<label class="layui-form-label">部门名称</label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">负责人</label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="title" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
					</div>
				</div>
			</form>
		</div>
	</body>

	<script src="../../plugin/layui/layui.js" charset="utf-8"></script>
	<script src="../../js/call-client.js" charset="utf-8"></script>
	<script>
		layui.use(['table', 'form'], function() {
			var table = layui.table,
				form = layui.form,
				$ = layui.jquery;
			//展示已知数据
			$.ajax({
				type: 'post',
				url: 'http://192.168.214.101:8080/XGProject/shiftStock.do?flag=QueryZhuanChu&ff=zhuanchu',
				async: false,
				dataType: 'json',
				success: function(data) {
					table.render({
						elem: '#demo',
						height: 700,
						data: data,
						cols: [
							[ //标题栏
								{
									field: 'id',
									title: '序号',
									width: 60

								}, {
									field: 'department',
									title: '货物名称',
									width: 150,
									align: 'center'

								}, {
									field: 'charge',
									title: '货物规格',
									width: 150,
									align: 'center'

								}, {
									field: 'charge',
									title: '货物材质',
									width: 150,
									align: 'center'

								}, {
									field: 'charge',
									title: '货物属性',
									width: 150,
									align: 'center'

								}, {
									field: 'charge',
									title: '货物产地',
									width: 150,
									align: 'center'

								}, {
									field: 'charge',
									title: '货物单价',
									width: 150,
									align: 'center'

								}, {
									field: 'remark',
									title: '备注',
									width: 200,
									align: 'center'

								}, {
									fixed: 'right',
									width: 260,
									align: 'center',
									toolbar: '#barDemo'

								}
							]
						],
						initSort: {
							field: 'time', //排序字段，对应 cols 设定的各字段名
							type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
						},
						even: true,
						page: true, //是否显示分页
						limits: [20, 30, 50],
						limit: 20, //每页默认显示的数量
					});
				},
				error: function() {
					alert('请求数据错误！');
				}
			});

			//监听工具条
			table.on('tool(datatable)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
				var data = obj.data; //获得当前行数据
				var layEvent = obj.event; //获得 lay-event 对应的值
				var tr = obj.tr; //获得当前行 tr 的DOM对象

				if(layEvent === 'del') { //删除
					layer.confirm('真的停用此部门吗', function(index) {
						obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
						layer.close(index);
						//向服务端发送删除指令
					});
				} else if(layEvent === 'edit') { //编辑
					//获取对应行的数据
					$('#xuhao').val(data.xuhao);
					$("#department").val(data.department);
					$("#charg").val(data.charg);
					$("#remark").val(data.remark);
					openindex = layer.open({
						type: 1,
						title: ['编辑部门', 'font-size:16px;'],
						content: $('#edit'),
						area: '550px',
						closeBtn: 2,
						anim: 4,
						skin: 'layui-layer-blue',
						btn: ['确定提交', '关闭'],
						yes: function(index, layero) {
							//按钮【按钮一】的回调
						},

					});
					obj.update({
						username: '123',
						title: 'xxx'
					});
				}
			});
			$('#xzbm').click(function() {
				layer.open({
					type: 1,
					title: ['新增部门', 'font-size:16px;'],
					content: $('#add'),
					area: '550px',
					closeBtn: 2,
					anim: 4,
					skin: 'layui-layer-blue',
					btn: ['确定提交', '关闭'],
					yes: function(index, layero) {
						//按钮【按钮一】的回调
					},
				});

			});

		});
	</script>

</html>