<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-出库订单收费详情</title>
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

						<input type="hidden" id="czId" name="eoperateId" value="${caozuoid}" />
						<input type="hidden" id="ziId" value="${ziId}" />
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
									<input type="text" name="number" id="tarehouseName" value="${exportOperate.eoperateRealityWeight==null?'0':exportOperate.eoperateRealityWeight}吨" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作件数</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${exportOperate.eoperateRealityNumber==null?"0":exportOperate.eoperateRealityNumber}${exportOperate.exportSeed.goods.goodsUnit.goodsUnitName }' autocomplete="off" class="layui-input">
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
					<fieldset>
						<legend style="font-size: 1.5rem;">审核信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核人员</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.interiorUserByEoperateAuditing.iuserName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核次数</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${exportOperate.eoperateAuditingTrue==null?"无":exportOperate.eoperateAuditingTrue }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${exportOperate.eoperateAuditingTime }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				<fieldset>
					<legend style="font-size: 1.5rem;">填写信息</legend>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">应收费用</label>
							<div class="layui-input-block">
								<input type="text" value='${exportOperate.eoperateShouldCost==null?"0":exportOperate.eoperateShouldCost}元' readonly='readonly' class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实收费用</label>
							<div class="layui-input-block">
								<input type="text" name="shishou" lay-verify="required|number" id="shishou" placeholder="请输入实收费用" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">结算方式</label>
							<div class="layui-input-block">
								<select name="jiesuan" id="jiesuan" lay-filter="jiesuan">
									<option value="日结">日结</option>
									<option value="月结">月结</option>
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">支付方式</label>
							<div class="layui-input-block">
								<select name="zhifu" id="zhifu" lay-filter='zhifu'>

								</select>
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">票据类型</label>
							<div class="layui-input-block">
								<select name="piaoju" id="piaoju">

								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">二次应收费用</label>
							<div class="layui-input-block">
								<input type="text" readonly='readonly' value='${exportOperate.eoperateDefinedFour==null?"0":exportOperate.eoperateDefinedFour} 元' class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">二次实收费用</label>
							<div class="layui-input-block">
								<input type="text" name="ercishishou" id="ercishishou" placeholder="请输入二次作业实收费用" class="layui-input">
							</div>
						</div>
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
					<a class="layui-btn layui-btn-danger" id="xiazhan"> <i class="layui-icon">&#xe601;</i>下站费</a>
					<button class="layui-btn" lay-submit lay-filter="formDemo"><i class="layui-icon">&#xe618;</i>立即提交</button>
					<a class="layui-btn layui-btn-normal" id="dayin"><i class="layui-icon">&#xe60a;</i>打印</a>
				</div>
				<hr class="layui-bg-orange" />
			</form>
		</div>

		<!--下站费弹出层-->
		<div id="xiazhanOpen" style="display: none; margin: 10px;">
			<form class="layui-form layui-form-pane">
				<input type="hidden" value="${exportOperate.exportSeed.export.exportId }" name="xadefinedtwo" />
				<input type="hidden" value="出库" name="xzdefinedthree" />
				<input type="hidden" value="${exportOperate.exportSeed.export.client.clientId}" id="clientId" />
				<input type="hidden" value="${exportOperate.eoperateId }" name="xadefinedfive" />
				<div class="layui-form-item">
					<label class="layui-form-label">对应客户</label>
					<div class="layui-input-block">
						<select name="clientByXzzhuanruclient" lay-verify="required" id="clientByXzzhuanruclient" lay-search>

						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">下站费单价</label>
					<div class="layui-input-block">
						<input type="text" name="danjia" lay-verify="required|number" id="danjia" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">重量</label>
					<div class="layui-input-block">
						<input type="text" name="weight" readonly="readonly" value="${exportOperate.eoperateRealityWeight}" id="weight" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">下站费合计</label>
					<div class="layui-input-block">
						<input type="text" name="heji" id="heji" readonly='readonly' class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">结算方式</label>
					<div class="layui-input-block">
						<select name="xiajiesuan" id="xiajiesuan" lay-filter="xiajiesuan">
							<option value="日结">日结</option>
							<option value="月结">月结</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">支付方式</label>
					<div class="layui-input-block">
						<select name="xiazhifu" id="xiazhifu"></select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">票据类型</label>
					<div class="layui-input-block">
						<select name="xiapiaoju" id="xiapiaoju"></select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">实收费用</label>
					<div class="layui-input-block">
						<input type="text" lay-verify="required|number" name="xiashishou" id="xiashishou" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="xiabeizhu" id="xiabeizhu" class="layui-input" />
					</div>
				</div>
				<button class="layui-btn" lay-submit lay-filter="xiaformDemo" style="display: none;" id="formDemo">立即提交</button>
			</form>
		</div>
	</body>
	<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
	<script src="/XGProject/xxcc-sl/js/call-client.js"></script>

	<!--引入查询支付方式和票据类型的js-->
	<script src="/XGProject/xxcc-sl/page/cc-caozuo/js/zhiFuPiaoJu.js"></script>

	<!--引入自写js-->
	<script src="/XGProject/xxcc-sl/page/cc-caozuo/js/chuKuDingDanShouFei.js"></script>

</html>