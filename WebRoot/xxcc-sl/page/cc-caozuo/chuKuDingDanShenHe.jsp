<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-出库订单操作详情</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
	</head>
	<%
		int shenhe = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单审核")){
				shenhe++;
			}
		}
	%>
	<%
	if(shenhe==0){
	%>
	<script type="text/javascript">
		$(function() {
			$("body").append(
				"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
		});
	</script>
	<%
	}
	%>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" action="" lay-filter='tianche'>
				<div id="showContent">
					<fieldset>
						<legend style="font-size: 1.5rem;">客户信息</legend>

						<input type="hidden" id="exportoperateid" value="${caozuoid}" name="eoperateId" />
						<input type="hidden" value="${ziId}" id="eseedId" name="eseedId" />

						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">客户名称</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.client.clientAbbreviation }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">客户单号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportClientNumber}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">发起时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportReateTime }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">联系电话</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedDefinedOne==null?'无':exportOperate.exportSeed.eseedDefinedOne }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">运输方式</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportCarryType}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">车号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportWagonNumber}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">司机姓名</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportDriverName}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">司机电话</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportDriverTel}" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">订单有效期</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportDefinedTwo}天" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">是否配送</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportRemark==null?'无':exportOperate.exportSeed.export.exportRemark}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">是否超发</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.export.exportDefinedOne}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedRemark }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
					<!--货物信息填写如下-->
					<fieldset>
						<legend style="font-size: 1.5rem;">货物信息</legend>
						<div class="layui-row layui-col-space30">
							<input type="hidden" value="${eo.exportSeed.goods.goodsId }" id="goodsId" />
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物品类</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.goods.goodsCategory.goodsCategoryName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物名称</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.goods.goodsName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物规格</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.goods.goodsStandard.goodsStandardName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物材质</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.goods.goodsQuality.goodsQualityName }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物属性</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.goods.goodsProperty.goodsPropertyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物产地</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.goods.goodsYieldly.goodsYieldlyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">应发重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedShouldWeight }吨" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">实发重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedRealityWeight==null?'0':exportOperate.exportSeed.eseedRealityWeight}吨" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">实发件数</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedRealityNumber==null?'0':exportOperate.exportSeed.eseedRealityNumber}${exportOperate.exportSeed.goods.goodsUnit.goodsUnitName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">应收费用</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedShouldCost==null?'0':eo.exportSeed.eseedShouldCost }元" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">实收费用</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedRealityCost==null?'0':eo.exportSeed.eseedRealityCost }元" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">订单状态</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedOrderStatus }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">二次应收费</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedSwshouldCost==null?'0':exportOperate.exportSeed.eseedSwshouldCost }元" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">二次实收费</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.exportSeed.eseedSwrealityCost==null?'0':exportOperate.exportSeed.eseedSwrealityCost}元" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>

					<fieldset>
						<legend style="font-size: 1.5rem;">分配信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">调度员</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.interiorUserByEoperateDispatcher.iuserName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">分配时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.eoperateDispatcherTime }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过磅/理算</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.eoperatePonderationTrue }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">库位</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="tarehouseName" value="${exportOperate.tarehouse.tarehouseName}" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">分配重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${exportOperate.eoperateDefinedTwo=="null"?"0":exportOperate.eoperateDefinedTwo}吨' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">批次号</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="EOperatepici" value='${exportOperate.EOperatepici==null?"无":exportOperate.EOperatepici}' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${exportOperate.eoperateRemark}' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
					<fieldset>
						<legend style="font-size: 1.5rem;">操作信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">保管员</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.interiorUserByEoperateStoreman.iuserName}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">开始操作时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.eoperateScreateTime}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">结束操作时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.eoperateSfinishTime}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="tarehouseName" value="${exportOperate.eoperateRealityWeight}吨" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作件数</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${exportOperate.eoperateRealityNumber}${exportOperate.exportSeed.goods.goodsUnit.goodsUnitName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">司磅员</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="EOperatepici" value='${exportOperate.interiorUserByEoperatePonderationMan==null?"无":exportOperate.interiorUserByEoperatePonderationMan.iuserName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过磅重量</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${exportOperate.eoperatePonderationTrue=="理算"?"0":exportOperate.eoperateRealityWeight==null?"0":exportOperate.eoperateRealityWeight }吨' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过磅时间</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${exportOperate.eoperatePonderationTime==null?"无":exportOperate.eoperatePonderationTime }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">二次作业重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${exportOperate.eoperateDefinedThree==null?"0":exportOperate.eoperateDefinedThree }吨' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">天车工</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="EOperatepici" value='${exportOperate.eoperateCraneman}' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">装卸工</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${exportOperate.eoperateStevedore}' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">车号</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${exportOperate.eoperateTruckNum==null?"无":exportOperate.eoperateTruckNum }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作状态</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${exportOperate.eoperateDefinedOne }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="EOperatepici" value='${exportOperate.eoperateRemark==null?"无":exportOperate.eoperateRemark }' autocomplete="off" class="layui-input">
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
					title: '系统提示',
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
               		skin: 'layui-layer-blue',
				}, function(index) {
					//用ajax的方式进行提交
					$.ajax({
						type: "post",
						url: "/XGProject/exportOperate.do?flag=ShengHeTongGuo",
						async: false,
						data: {
							"czId": $("#exportoperateid").val(),
							"beizhu": $("#beizhu").val(),
							"ziId": $("#eseedId").val()
						},
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("审核成功");
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
					title: '系统提示',
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
				}, function(index) {
					$.ajax({
						type: "post",
						url: "/XGProject/exportOperate.do?flag=ShengHeWeiTongGuo",
						async: false,
						data: {
							"czId": $("#exportoperateid").val(),
							"beizhu": $("#beizhu").val(),
							"ziId": $("#eseedId").val()
						},
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("审核成功");
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