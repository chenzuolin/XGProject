<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-过户订单详情</title>
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
				<c:if test="${ss.ssseedOrderStatus=='正在审核'}">
					<a class="layui-btn" id="quxiao">取消订单</a>
				</c:if>
			</div>
			<form class="layui-form layui-form-pane" action="" lay-filter='tianche'>
				<div id="showContent">
					<fieldset>
						<legend style="font-size: 1.5rem;">客户信息</legend>
						<input type="hidden" value="${ss.ssseedId }" name="ioperateId" id="exportoperateid" />
						<input type="hidden" id="dengluren" name="interiorUserBySsseedAuditing" value='<%=request.getSession().getAttribute("iulistId")%>' />
						<input type="hidden" id="zhuanchu" value="${ss.shiftStock.clientBySstockClientId.clientId}" name="clientBySstockClientId" />
						<input type="hidden" id="zhuanru" value="${ss.shiftStock.clientBySstockShiftToFirm.clientId}" name="clientBySstockShiftToFirm" />
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">订单编号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.shiftStock.sstockId }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">转出单位</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.shiftStock.clientBySstockClientId.clientAbbreviation }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">转入单位</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.shiftStock.clientBySstockShiftToFirm.clientAbbreviation }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过户时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.shiftStock.sstockReateTime }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">客户单号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.shiftStock.sstockClientNumber }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">订单状态</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.ssseedOrderStatus }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.ssseedRemark }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
					<!--货物信息填写如下-->
					<fieldset>
						<legend style="font-size: 1.5rem;">货物信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物品类</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.goods.goodsCategory.goodsCategoryName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物名称</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.goods.goodsName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物规格</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.goods.goodsStandard.goodsStandardName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物材质</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.goods.goodsQuality.goodsQualityName }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物属性</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.goods.goodsProperty.goodsPropertyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物产地</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.goods.goodsYieldly.goodsYieldlyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过户重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${ss.ssseedWeight }吨" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">转库类型</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.shiftStock.sstockDefinedOne==null?"无":ss.shiftStock.sstockDefinedOne }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend style="font-size: 1.5rem;">审核信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核人</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.interiorUserBySsseedAuditing==null?"无":ss.interiorUserBySsseedAuditing.iuserName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.ssseedAuditingTime==null?"无":ss.ssseedAuditingTime }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核结果</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.ssseedAuditingTrue==null?"无":ss.ssseedAuditingTrue }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend style="font-size: 1.5rem;">收费信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">收费人</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.interiorUserBySsseedCollectMan==null?"无":ss.interiorUserBySsseedCollectMan.iuserName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">收费时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.ssseedCollectTime==null?"无":ss.ssseedCollectTime }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">应收费用</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.ssseedShouldCost==null?"0":ss.ssseedShouldCost }元' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">实收费用</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.ssseedRealityCost==null?"0":ss.ssseedRealityCost }元' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">结算方式</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.ssseedClientAccounts==null?"无":ss.ssseedClientAccounts }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">支付方式</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.paymentFashion==null?"无":ss.paymentFashion.pfashionName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">票据类型</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${ss.paymentFashion==null?"无":ss.paymentFashion.pfashionReceipt }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>

				</div>
				<hr class="layui-bg-orange" />
			</form>
		</div>

	</body>
	<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
	<script src="/XGProject/xxcc-sl/js/call-client.js"></script>

	<script>
		inputBg();
		layui.use(['jquery', 'form', 'layer'], function() {
			var $ = layui.jquery;
			var form = layui.form;
			var layer = layui.layer;

			//将显示内容的文本框改变为只读
			$('#showContent input').attr('readonly', 'readonly');

			//给关键字添加背景,突出显示
			$('#showContent label').each(function() {
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
				var zid = $("#exportoperateid").val(); //获得子订单编号
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
							url: "/XGProject/shiftStockSeed.do?flag=goDingdanZuofei&id=" + zid,
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