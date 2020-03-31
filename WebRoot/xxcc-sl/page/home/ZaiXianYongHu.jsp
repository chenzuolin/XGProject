<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-在线用户</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" type="text/css" href="../../css/public.css" />
	</head>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" action="">
				<fieldset class="buttons">
					<legend>在线用户</legend>
					<div class="layui-form-item" style="margin: 0;">
						<div class="layui-inline">
							<label class="layui-form-label">部门</label>
							<div class="layui-input-inline">
								<select name="bumen" id="bumen" lay-filter="bumen">
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">班组</label>
							<div class="layui-input-inline">
								<select name="banzu" id="banzu" lay-filter="banzu">
								</select>
							</div>
						</div>

						<div class="layui-inline">
							<button class="layui-btn" lay-submit="" id="demo1" lay-filter="demo1">立即查询</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</fieldset>
			</form>
			<table id="demo"></table>
		</div>
		<script src="../../plugin/layui/layui.js" charset="utf-8"></script>
		<script src="../../js/call-client.js" charset="utf-8"></script>
		<script>
			layui.use(['table', 'form', 'jquery', 'layer'], function() {
				var table = layui.table;
				var form = layui.form;
				var $ = layui.jquery;
				var layer = layui.layer;
				QueryOnLine($("#bumen option:selected").text(), $("#banzu").val());
				//监听立即查询
				form.on('submit(demo1)', function(obj) {
					var data = obj.field;
					QueryOnLine($("#bumen option:selected").text(), data.banzu);
					return false;
				});
				//监听部门选择框
				form.on('select(bumen)', function(obj) {
					QueryBanZu(obj.value);
				});
			});

			queryBumen();
			//查询对应的部门追加到相应下拉框中
			function queryBumen() {
				layui.use(['jquery', 'form', 'layer'], function() {
					var $ = layui.jquery;
					var form = layui.form;
					var layer = layui.layer;
					//用ajax请求数据
					$.ajax({
						type: "post",
						url: "/XGProject/section.do?flag=selectSectionAjax",
						async: false,
						dataType: "json",
						success: function(obj) {
							$("#bumen").html("");
							if(obj != null) {
								for(var i = 0; i < obj.length; i++) {
									if(i == 0) {
										$("#bumen").append("<option value=''>请选择部门</option>");
									}
									$("#bumen").append("<option value='" + obj[i].id + "'>" + obj[i].name + "</option>");
								}
							} else {
								$("#bumen").append("<option value=''>无</option>");
							}
							form.render('select');
							QueryBanZu($("#bumen").val());
						},
						error: function() {
							layer.alert("获得部门数据错误！", {
								icon: 2,
								anim: 4,
								closeBtn: 2
							});
						}
					});
				});
			}
			//查询对应的班组追加到相应下拉框中
			function QueryBanZu(bumen) {
				layui.use(['jquery', 'form', 'layer'], function() {
					var $ = layui.jquery;
					var form = layui.form;
					var layer = layui.layer;
					if(bumen == null || bumen == "") {
						$("#banzu").html("<option value=''>无班组</option>");
						form.render('select');
						return false;
					}
					$.ajax({
						type: "post",
						url: "/XGProject/classT.do?flag=selectclassTAjax",
						data: {
							"section": bumen
						},
						async: true,
						dataType: "json",
						success: function(obj) {
							$("#banzu").html("");
							if(obj != null) {
								for(var i = 0; i < obj.length; i++) {
									$("#banzu").append("<option value='" + obj[i].name + "'>" + obj[i].name + "</option>");
								}
							} else {
								$("#banzu").append("<option value=''>无班组</option>");
							}
							form.render('select');
						},
						error: function() {
							layer.alert("获得班组数据错误！", {
								icon: 2,
								anim: 4,
								closeBtn: 2
							});
						}
					});
				});
			}

			//查询在线的人员
			function QueryOnLine(bumen, banzu) {
				layui.use(['jquery', 'form', 'table', 'layer'], function() {
					var $ = layui.jquery;
					var form = layui.form;
					var table = layui.table;
					var layer = layui.layer;
					var bumens = "";
					if(bumen == "请选择部门" || bumen == "无" || bumen == "" || bumen == "无部门") {
						bumens = "";
					} else {
						bumens = bumen;
					}
					$.ajax({
						type: "post",
						url: "/XGProject/interiorUser.do?flag=getOnLine",
						data: {
							"bumen": bumens,
							"banzu": banzu
						},
						async: false,
						dataType: "json",
						success: function(obj) {
							//                   alert(JSON.stringify(obj));
							if(obj == null || obj[0].result == null) {
								layer.alert("无在线人员！", {
									icon: 5,
									anim: 4,
									closeBtn: 2
								});
							}
							var w=$(parent.window).width()-234;//获取浏览器的宽，减去侧边栏的宽度  
							table.render({
								data: obj,
								elem: '#demo',
								height: 'full-125',
								width:w,
								page: true,
								limit: 20, //默认显示的行数
								limits: [20, 30, 50],
								even: true,
								cols: [
									[{
										field: 'id',
										title: '编号',
										width: 100,
										sort: true,
										align: 'center'
									}, {
										field: 'name',
										title: '姓名',
										minWidth:100,
										align: 'center'
									}, {
										field: 'bumen',
										title: '部门',
										minWidth:100,
										sort: true,
										align: 'center'
									}, {
										field: 'banzu',
										title: '班组',
										minWidth:100,
										sort: true,
										align: 'center'
									}, {
										field: 'juese',
										title: '角色',
										minWidth:100,
										align: 'center'
									}, {
										field: 'zaixian',
										title: '在线状态',
										minWidth:100,
										align: 'center'
									}, {
										field: 'zuoye',
										title: '作业状态',
										minWidth:100,
										align: 'center'
									}, {
										field: 'tel',
										title: '联系电话',
										minWidth:100,
										align: 'center'
									}, {
										field: 'remark',
										title: '备注',
										minWidth:100,
										align: 'center'
									}]
								],
							});
						},
						error: function() {
							layer.alert("获取在线用户数据错误！", {
								icon: 2,
								anim: 4,
								closeBtn: 2
							});
						}
					});
				});
			}
		</script>
	</body>

</html>