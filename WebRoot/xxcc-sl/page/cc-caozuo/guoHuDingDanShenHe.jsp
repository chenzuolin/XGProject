<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-过户订单审核详情</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
	</head>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" action="" lay-filter='tianche'>
				<div id="showContent">
					<fieldset>
						<legend style="font-size: 1.5rem;">客户信息</legend>

						<input type="hidden" value="${sss.ssseedId }" name="ioperateId" id="exportoperateid" />
						<input type="hidden" id="dengluren" name="interiorUserBySsseedAuditing" value='<%=request.getSession().getAttribute("iulistId")%>' />
						<input type="hidden" id="zhuanchu" value="${sss.shiftStock.clientBySstockClientId.clientId}" name="clientBySstockClientId" />
						<input type="hidden" id="zhuanru" value="${sss.shiftStock.clientBySstockShiftToFirm.clientId}" name="clientBySstockShiftToFirm" />
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">转出单位</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.shiftStock.clientBySstockClientId.clientAbbreviation }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">转入单位</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.shiftStock.clientBySstockShiftToFirm.clientAbbreviation }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过户时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.shiftStock.sstockReateTime }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">客户单号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.shiftStock.sstockClientNumber }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">订单状态</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.ssseedOrderStatus }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.ssseedRemark }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
					<!--货物信息填写如下-->
					<fieldset>
						<legend style="font-size: 1.5rem;">货物信息</legend>
						<div class="layui-row layui-col-space30">
							<input type="hidden" value="${inputOperate.inputSeed.goods.goodsId }" id="goodsId" />
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物品类</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.goods.goodsCategory.goodsCategoryName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物名称</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.goods.goodsName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物规格</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.goods.goodsStandard.goodsStandardName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物材质</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.goods.goodsQuality.goodsQualityName }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物属性</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.goods.goodsProperty.goodsPropertyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物产地</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.goods.goodsYieldly.goodsYieldlyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过户重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${sss.ssseedWeight }吨" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">转库类型</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${sss.shiftStock.sstockDefinedOne==null?"无":sss.shiftStock.sstockDefinedOne }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				<fieldset>
					<legend style="font-size: 1.5rem;">填写信息</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-block">
								<input type="text" name="beizhu" id="beizhu" placeholder="请输入备注" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<div style="margin-top: 15px; ">
					<a class="layui-btn layui-btn-warm" onclick="javascript:window.history.go(-1);" style="color: black;"><i class="layui-icon">&#xe603;</i>返回</a>
					<a class="layui-btn" id="tongguo"><i class="layui-icon">&#x1005;</i>通过</a>
					<a class="layui-btn layui-btn-danger" id="butongguo"> <i class="layui-icon">&#x1006;</i>不通过</a>
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

			//当点击审核通过的时候触发
			$("#tongguo").click(function() {
				layer.confirm('确定审核通过吗?', {
					icon: 3,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
				}, function(index) {
					//用ajax的方式进行提交
					$.ajax({
						type: "post",
						url: "/XGProject/shiftStockSeed.do?flag=TongGuoShiftStock",
						async: false,
						data: {
							"ssseedId": $("#exportoperateid").val(), //子订单编号
							"interiorUserBySsseedAuditing": $("#dengluren").val(), //对应的审核人
							"clientBySstockClientId": $("#zhuanchu").val(), //对应的转出单位
							"clientBySstockShiftToFirm": $("#zhuanru").val(),
							"ssseedRemark": $("#beizhu").val() //备注
						},
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("提交成功");
							if(ok != -1) {
								layer.alert("提交成功！", {
									icon: 1,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
                					skin: 'layui-layer-blue',
								}, function() {
									document.location.href = "/XGProject/xxcc-sl/page/cc-caozuo/dingDanShenHe.jsp";
								});
							} else {
								layer.alert("提交失败！", {
									icon: 5,
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
				});
			});

			//当点击审核不通过的时候触发
			$("#butongguo").click(function() {
				//用ajax的方式进行提交
				layer.confirm('确定审核不通过吗?', {
					icon: 3,
					title: ['系统提示', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
					closeBtn: 2,
					anim: 4,
				}, function(index) {
					$.ajax({
						type: "post",
						url: "/XGProject/shiftStockSeed.do?flag=NotTongGuoShiftStock",
						async: false,
						data: {
							"ssseedId": $("#exportoperateid").val(), //子订单编号
							"interiorUserBySsseedAuditing": $("#dengluren").val(), //对应的审核人
							"clientBySstockClientId": $("#zhuanchu").val(), //对应的转出单位
							"clientBySstockShiftToFirm": $("#zhuanru").val(),
							"ssseedRemark": $("#beizhu").val() //备注
						},
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("提交成功");
							if(ok != -1) {
								layer.alert("提交成功！", {
									icon: 1,
									closeBtn: 2,
									anim: 4,
									title: ['系统提示', 'font-size:16px;'],
                					skin: 'layui-layer-blue',
								}, function() {
									document.location.href = "/XGProject/xxcc-sl/page/cc-caozuo/dingDanShenHe.jsp";
								});
							} else {
								layer.alert("提交失败！", {
									icon: 5,
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
				});
			});
		});
	</script>

</html>