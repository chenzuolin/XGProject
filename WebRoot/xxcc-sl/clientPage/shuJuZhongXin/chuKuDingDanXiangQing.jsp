﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-出库订单详情</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
	</head>

	<body>
		<div class="layui-fluid">
			<div style="margin-top: 15px; ">
				<a class="layui-btn layui-btn-warm" onclick="javascript:window.history.go(-1);" style="color: black;"><i class="layui-icon">&#xe603;</i>返回</a>
				<c:if test="${es.eseedOrderStatus=='计划出库'}">
					<a class="layui-btn" id="quxiao">取消订单</a>
				</c:if>
			</div>
			<form class="layui-form layui-form-pane" action="" id="showContent">
				<fieldset>
					<legend style="font-size: 1.5rem;">客户信息</legend>
					<input type="hidden" value="${es.eseedId }" id="kzid" />
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">客户名称</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.client.clientAbbreviation }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">客户单号</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportClientNumber }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">发起时间</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportReateTime }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">联系电话</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedDefinedOne==null?'无':es.eseedDefinedOne }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">运输方式</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportCarryType==null?'无':es.export.exportCarryType }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">车号</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportWagonNumber=='null'?'无':es.export.exportWagonNumber}" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">司机姓名</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportDriverName=='null'?'无':es.export.exportDriverName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">司机电话</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportDriverTel=='null'?'无':es.export.exportDriverTel }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">订单有效期</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportDefinedTwo }天" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">是否配送</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportRemark==null?'无':es.export.exportRemark}" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">是否超发</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.export.exportDefinedOne }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedRemark }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<!--货物信息填写如下-->
				<fieldset>
					<legend style="font-size: 1.5rem;">货物信息</legend>
					<input type="hidden" value="${es.goods.goodsId }" id="goodsId" />
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物品类</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.goods.goodsCategory.goodsCategoryName}" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物名称</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.goods.goodsName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物规格</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.goods.goodsStandard.goodsStandardName}" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物材质</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.goods.goodsQuality.goodsQualityName }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物属性</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.goods.goodsProperty.goodsPropertyName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物产地</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.goods.goodsYieldly.goodsYieldlyName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">应发重量</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedShouldWeight }吨" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实发重量</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedRealityWeight==null?'0':es.eseedRealityWeight}吨" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实发件数</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedRealityNumber==null?'0':es.eseedRealityNumber}${es.goods.goodsUnit.goodsUnitName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">应收费用</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedShouldCost==null?'0':es.eseedShouldCost }元" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实收费用</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedRealityCost==null?'0':es.eseedRealityCost }元" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">订单状态</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedOrderStatus }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">二次应收费</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedSwshouldCost==null?'0':es.eseedSwshouldCost }元" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">二次实收费</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${es.eseedSwrealityCost==null?'0':es.eseedSwrealityCost}元" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>

	</body>
	<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
	<script src="/XGProject/xxcc-sl/js/call-client.js"></script>

	<script>
		inputBg();
		layui.use(['jquery', 'layer'], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			//给input添加只读属性
			$('#showContent input').attr('readonly', 'readonly');
			//给关键字添加背景,突出显示
			$('label').each(function() {
				if($(this).text() == '时间' || $(this).text() == '订单有效期' ||
					$(this).text().indexOf("重量") != -1 || $(this).text() == '调度员' ||
					$(this).text() == '操作员' || $(this).text() == '作业件数' ||
					$(this).text() == '审核人' || $(this).text() == '收费人' ||
					$(this).text().indexOf("费") != -1) {
					$(this).css({
						'color': '#009688',
					});
					$(this).next('div').find("input").css({
						'color': '#009688',
					});
				}
			});
			//当单机取消订单的时候触发
			$("#quxiao").click(function() {
				var zid = $("#kzid").val(); //获得子订单编号
				layer.confirm("确定要取消此订单吗？", {
					icon: 3,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
					skin: 'layui-layer-blue',
					yes: function() {
						//用ajax的方式进行提交取消订单
						$.ajax({
							type: "post",
							url: "/XGProject/exportSeed.do?flag=goDingdanZuofei&id=" + zid,
							async: true,
							dataType: "text",
							success: function(data) {
								if(data.indexOf("操作成功") != -1) {
									layer.alert("订单取消成功！", {
										icon: 1,
										closeBtn: 2,
										anim: 4,
										title: ['系统提示', 'font-size:16px;'],
										skin: 'layui-layer-blue',
										yes: function() {
											window.history.go(-1);
										}
									});
								} else {
									layer.alert("订单取消失败！", {
										icon: 1,
										closeBtn: 2,
										anim: 4,
										title: ['系统提示', 'font-size:16px;'],
										skin: 'layui-layer-blue',
									});
								}
							},
							error: function() {
								layer.alert("数据上传错误！", {
									icon: 2,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
									skin: 'layui-layer-blue',
								});
							}
						});
					}
				})
			});
		});
	</script>

</html>