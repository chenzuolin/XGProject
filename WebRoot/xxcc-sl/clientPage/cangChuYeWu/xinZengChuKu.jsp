<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-新增出库</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="../../plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="../../css/public.css" />
	</head>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" action="">
				<fieldset>
					<legend style="font-size: 1.5rem;">客户信息</legend>
					<input type="hidden" value="${clientId}" lay-verify="required" id="client" name="clientOut">
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">客户单号</label>
							<div class="layui-input-block">
								<input type="text" name="clientNum" lay-verify="required" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">订单有效期</label>
							<div class="layui-input-block">
								<input type="text" name="indate" lay-verify="required|number" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">是否配送</label>
							<div class="layui-input-block">
								<input type="radio" name="peisong" value="配送" title="配送" lay-filter="delivery">
								<input type="radio" name="peisong" value="不配送" title="不配送" lay-filter="delivery" checked>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">是否超发</label>
							<div class="layui-input-block">
								<input type="radio" name="chaofa" value="接受超发" title="可超发">
								<input type="radio" name="chaofa" value="不接受超发" title="不超发" checked>
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 " id="transport">
							<label class="layui-form-label">运输方式</label>
							<div class="layui-input-block">
								<select name="transport" lay-verify="required">
									<option value="汽运">汽运</option>
									<option value="火运">火运</option>
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3" id="carNum">
							<label class="layui-form-label">车号</label>
							<div class="layui-input-block">
								<input type="text" name="truckNum" lay-verify="required" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 " id="driverName">
							<label class="layui-form-label">司机姓名</label>
							<div class="layui-input-block">
								<input type="text" name="driverName" lay-verify="required" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3" id="tel">
							<label class="layui-form-label">司机电话</label>
							<div class="layui-input-block">
								<input type="text" name="driverTel" lay-verify="required|phone" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 " id="tel1" style="display: none;">
							<label class="layui-form-label">联系电话</label>
							<div class="layui-input-block">
								<input type="text" name="lianXiTel" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<!--货物信息填写如下-->
				<fieldset>
					<legend>货物信息</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物品类</label>
							<div class="layui-input-block">
								<select name="cargoPinlei" lay-verify="required" id="cargoPinlei" lay-filter="cargoPinlei">
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物名称</label>
							<div class="layui-input-block">
								<select name="cargoName" lay-verify="required" id="cargoName" lay-filter="cargoName" lay-search>
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物规格</label>
							<div class="layui-input-block">
								<select name="cargoGuige" lay-verify="required" id="cargoGuige" lay-filter="cargoGuige" lay-search>
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物材质</label>
							<div class="layui-input-block">
								<select name="cargoCaizhi" lay-verify="required" id="cargoCaizhi" lay-filter="cargoCaizhi" lay-search>
								</select>
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物属性</label>
							<div class="layui-input-block">
								<select name="cargoShuxing" lay-verify="required" id="cargoShuxing" lay-filter="cargoShuxing" lay-search>
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物产地</label>
							<div class="layui-input-block">
								<select name="cargoChandi" lay-verify="required" id="cargoChandi" lay-filter="cargoChandi" lay-search>
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物重量</label>
							<div class="layui-input-block">
								<input type="text" name="cargoWeight" lay-verify="required|number" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-block">
								<input type="text" name="remark" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<div class="layui-layout-right" style="margin-top: 20px;">
							<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</div>
				<select id="weight" lay-ignore style="display: none; opacity: 0;"></select>
			</form>
		</div>
		<script src="../../plugin/layui/layui.js" charset="utf-8"></script>
		<script src="../../js/call-client.js"></script>
	</body>
	<script>
		inputBg();

		layui.use(['form', 'jquery', 'layer'], function() {
			var $ = layui.jquery;
			var form = layui.form;
			var layer = layui.layer;
			//更新货物信息
			cargoPinlei('#cargoPinlei', $("#client").val());
			form.render('select'); //刷新select选择框渲染

			form.on('select(cargoPinlei)', function(data) {
				cargoName('#cargoName', $('#client').val(), data.value);
				form.render('select'); //刷新select选择框渲染
			});
			form.on('select(cargoName)', function(data) {
				cargoGuige('#cargoGuige', $('#client').val(), $('#cargoPinlei').val(), data.value);
				form.render('select'); //刷新select选择框渲染
			});
			form.on('select(cargoGuige)', function(data) {
				cargoCaizhi('#cargoCaizhi', $('#client').val(), $('#cargoPinlei').val(), $('#cargoName').val(), data.value);
				form.render('select'); //刷新select选择框渲染
			});
			form.on('select(cargoCaizhi)', function(data) {
				cargoShuxing('#cargoShuxing', $('#client').val(), $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), data.value);
				form.render('select'); //刷新select选择框渲染
			});
			form.on('select(cargoShuxing)', function(data) {
				cargoChandi('#cargoChandi', $('#client').val(), $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), $('#cargoCaizhi').val(), data.value);
				form.render('select'); //刷新select选择框渲染
			});

			//当单机提交的时候，用ajax的方式进行提交
			form.on('submit(demo1)', function(obj) {
				var data = obj.field;
				var yes = false;
				$("#weight option").each(function() {
					if($(this).val() == data.cargoChandi) {
						if(parseFloat(data.cargoWeight) > parseFloat($(this).text())) {
							yes = true;
						}
						return;
					}
				});
				if(yes) {
					layer.alert("出库重量不能大于库存重量！", {
						icon: 2,
						anim: 4,
						closeBtn: 2,
						title: ['系统提示', 'font-size:16px;'],
						skin: 'layui-layer-blue',
					});
					return false;
				}
				tijiao(data);
				return false;
			});
			//当选择配送是改变编辑内容
			form.on('radio(delivery)', function(data) {
				//          alert(JSON.stringify(data));
				var peisong = data.value;
				if(peisong == '配送') {
					$('#tel1').css('display', 'block');
					$("#tel1 input").attr("lay-verify", "required");
					$('#carNum,#driverName,#tel').css('display', 'none');
					$('#carNum input,#driverName input,#tel input').attr("lay-verify", "");
				} else {
					$('#tel1').css('display', 'none');
					$("#tel1 input").attr("lay-verify", "");
					$('#carNum,#driverName,#tel').css('display', 'block');
					$('#carNum input,#driverName input,#tel input').attr("lay-verify", "required");
				}
			});
		});

		function tijiao(data) {
			layui.use(['jquery', 'form', 'layer'], function() {
				var $ = layui.jquery;
				var form = layui.form;
				var layer = layui.layer;

				layer.confirm("确定提交吗？", {
					icon: 3,
					anim: 4,
					closeBtn: 2,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
				}, function() {
					$.ajax({
						type: "post",
						url: "/XGProject/export.do?flag=FaQiChuKu",
						async: false,
						data: {
							"client": data.clientOut, //对应客户
							"exportClientNumber": data.clientNum, //客户单号
							"exportDefinedTwo": data.indate, //订单有效期
							"exportCarryType": data.transport, //运输方式
							"exportRemark": data.peisong, //是否配送
							"exportDefinedOne": data.chaofa, //是否超发
							"exportWagonNumber": data.truckNum, //车号
							"exportDriverName": data.driverName, //司机姓名
							"exportDriverTel": data.driverTel, //司机电话
							"goodsId": data.cargoChandi, //货物
							"goodsWeight": data.cargoWeight, //重量
							"eseedDefinedOne": data.lianXiTel, //联系电话
							"remark": data.remark //备注
						},
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("发起成功");
							if(ok != -1) {
								layer.alert("出库发起成功！", {
									icon: 1,
									anim: 4,
									closeBtn: 2,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								});
								cargoChandi('#cargoChandi', $('#client').val(), $('#cargoPinlei').val(), $('#cargoName').val(), $('#cargoGuige').val(), $('#cargoCaizhi').val(), $("#cargoShuxing").val());
								form.render('select'); //刷新select选择框渲染
							} else {
								layer.alert("出库发起失败！", {
									icon: 5,
									anim: 4,
									closeBtn: 2,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								});
							}
						},
						error: function() {
							layer.alert("数据上传错误！", {
								icon: 2,
								anim: 4,
								closeBtn: 2,
								title: ['系统提示', 'font-size:16px;'],
								skin: 'layui-layer-blue',
							});
						}
					});
				});
			});
		}
	</script>

</html>