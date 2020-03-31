<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-入库订单详情</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
	</head>
	<%
		int x = 0;
		int update = 0;
		int zuofei = 0;
		int timeupdate = 0;
		int bgupdate= 0;
		int ddupdate = 0;
		int cwupdate = 0;
		int updateshenpi = 0;
		String zhiwu ="";
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		InteriorUser iu = (InteriorUser)request.getSession().getAttribute("iulist");
		if(iu==null){
			zhiwu ="无";
		}else{
			zhiwu = iu.getInteriorUserDuty()==null?"无":iu.getInteriorUserDuty().getInteriorUserDutyName();
		}
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("入库完成")){
				x++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改")){
				update++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单作废")){
				zuofei++;
			}
			//修改订单时间的权限
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单时间修改")){
				timeupdate++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("保管修改")){
				bgupdate++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("调度修改")){
				ddupdate++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("财务修改")){
				cwupdate++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改审批")){
				updateshenpi++;
			}
		}
	%>

	<body>
		<div class="layui-fluid">
			<div style="margin-top: 15px; ">
				<%
				if(update!=0){
				%>
				<a class="layui-btn" id="update" id="update"><i class="layui-icon">&#xe642;</i>订单修改</a>
				<%
					}
				%>
				<%
				if(zuofei!=0){
				%>
				<a class="layui-btn layui-btn-danger" id="zuofei"> <i class="layui-icon">&#xe640;</i>订单作废</a>
				<%
				}
				%>
				<%
					if(x!=0){
				%>
				<c:if test="${is.iseedOrderStatus != '订单作废' && is.iseedOrderStatus != '入库完成' && is.iseedOrderStatus != '已收费' && is.iseedOrderStatus != '未收费'  }">
					<a class="layui-btn layui-btn-normal" id="rukuwancheng"><i class="layui-icon">&#xe616;</i>入库完成</a>
				</c:if>
				<%
				}
				%>
				<%
				if (timeupdate != 0) {
				%>
				<a class="layui-btn" id="timeupdate"><i class="layui-icon">&#xe637;</i>订单时间修改</a>
				<%
				}
				%>
				<%
				if(zhiwu.equals("收费员") || zhiwu.equals("管理员")){
				%>
				<a class="layui-btn layui-btn-normal" id="dayin"><i class="layui-icon">&#xe60a;</i>打印</a>
				<%
				}
				%>
				<a class="layui-btn layui-btn-warm" onclick="javascript:window.history.go(-1);" style="color: black;"><i class="layui-icon">&#xe603;</i>返回</a>
			</div>
			<form class="layui-form layui-form-pane" action="" id="showContent">
				<fieldset>
					<legend style="font-size: 1.5rem;">客户信息</legend>
					<input type="hidden" value="${is.iseedId }" id="kzid" />
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">客户名称</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.client.clientAbbreviation }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">客户单号</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputClientNumber }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">发起时间</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputCreateTime }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">运输方式</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputCarryType }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">车号</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputPlateNumber }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">司机姓名</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputDriverName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">司机电话</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputDriverTel }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">订单有效期</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputDefinedOne }天" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.iseedRemark }" autocomplete="off" class="layui-input">
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
								<input type="text" name="number" value="${is.goods.goodsCategory.goodsCategoryName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物名称</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.goods.goodsName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物规格</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.goods.goodsStandard.goodsStandardName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物材质</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.goods.goodsQuality.goodsQualityName }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物属性</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.goods.goodsProperty.goodsPropertyName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">货物产地</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.goods.goodsYieldly.goodsYieldlyName }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">应收重量</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.iseedShouldWeight }吨" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实收重量</label>
							<div class="layui-input-block">
								<input type="text" name="number" value='${is.iseedRealityWeight==null?"0":is.iseedRealityWeight }吨' autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实收件数</label>
							<div class="layui-input-block">
								<input type="text" name="number" value='${is.iseedRealityNumber==null?"0":is.iseedRealityNumber }${is.goods.goodsUnit.goodsUnitName }' autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">应收费用</label>
							<div class="layui-input-block">
								<input type="text" name="number" value='${is.iseedShouldCost==null?"0":is.iseedShouldCost }元' autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实收费用</label>
							<div class="layui-input-block">
								<input type="text" name="number" value='${is.iseedRealityCost==null?"0":is.iseedRealityCost }元' autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">订单状态</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.iseedOrderStatus }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">车皮号</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.iseedDefinedOne }" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">订单发起人</label>
							<div class="layui-input-block">
								<input type="text" name="number" value="${is.input.inputFaQiRen }" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<c:forEach items="${iolist }" var="z" varStatus="zi">
					<input type="hidden" value="${z.ioperateId }" id="exportoperateid" />
					<div>
						<fieldset>
							<legend style="font-size: 1.5rem;">分配信息${zi.index + 1 }</legend>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">调度员</label>
									<div class="layui-input-block">
										<input type="text" name="number" value="${z.interiorUserByIoperateDispatcherId.iuserName }" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">分配时间</label>
									<div class="layui-input-block">
										<input type="text" name="number" value="${z.ioperateDispatcherTime }" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">过磅/理算</label>
									<div class="layui-input-block">
										<input type="text" name="number" value="${z.ioperatePonderationTrue }" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">库位</label>
									<div class="layui-input-block">
										<input type="text" name="number" value="${z.tarehouse.tarehouseName }" autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 1.5rem;">操作信息${zi.index + 1 }</legend>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">操作员</label>
									<div class="layui-input-block">
										<input type="text" name="number" value='${z.interiorUserByIoperateStoremanId.iuserName }' autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">开始时间</label>
									<div class="layui-input-block">
										<input type="text" name="number" value='${z.ioperateScreateTime==null?"无":z.ioperateScreateTime }' autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">结束时间</label>
									<div class="layui-input-block">
										<input type="text" name="number" value='${z.ioperateSfinishTime==null?"无":z.ioperateSfinishTime }' autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">作业重量</label>
									<div class="layui-input-block">
										<input type="text" name="number" value='${z.ioperateRealityWeight==null?"0":z.ioperateRealityWeight }吨' autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">作业件数</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateRealityNumber==null?"0":z.ioperateRealityNumber }${z.inputSeed.goods.goodsUnit.goodsUnitName }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">司磅员</label>
									<div class="layui-input-block">
										<input type="text" name="number" value='${z.interiorUserByIoperatePonderationManId==null?"无":z.interiorUserByIoperatePonderationManId.iuserName }' autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">过磅重量</label>
									<div class="layui-input-block">
										<input type="text" name="number" value='${z.ioperatePonderationTrue=="理算"?"0":z.ioperateRealityWeight==null?"0":z.ioperateRealityWeight }吨' autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">过磅时间</label>
									<div class="layui-input-block">
										<input type="text" name="number" value='${z.ioperatePonderationTime==null?"无":z.ioperatePonderationTime }' autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">天车工</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateCraneman==null?"无":z.ioperateCraneman }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">装卸工</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateStevedore==null?"无":z.ioperateStevedore }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">车号</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateTruckNum==null?"无":z.ioperateTruckNum }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">操作状态</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateDefinedTwo }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">备注</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateRemark==null?"无":z.ioperateRemark }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 1.5rem;">审核信息${zi.index + 1 }</legend>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">审核人</label>
									<div class="layui-input-block">
										<input type="text" value='${z.interiorUserByIoperateAuditingId==null?"无":z.interiorUserByIoperateAuditingId.iuserName }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">审核时间</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateAuditingTime==null?"无":z.ioperateAuditingTime }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">审核次数</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateDefinedOne==null?"无":z.ioperateDefinedOne }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend style="font-size: 1.5rem;">收费信息${zi.index + 1 }</legend>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">收费人</label>
									<div class="layui-input-block">
										<input type="text" value='${z.interiorUserByIoperateCollectManId==null?"无":z.interiorUserByIoperateCollectManId.iuserName }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">收费时间</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateCollectTime==null?"无":z.ioperateCollectTime }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">应收费用</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateShouldCost==null?"0":z.ioperateShouldCost }元' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">实收费用</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateRealityCost==null?"0":z.ioperateRealityCost }元' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
							<div class="layui-row layui-col-space30">
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">结算方式</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperateClientAccounts==null?"无":z.ioperateClientAccounts }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">支付方式</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperatePaymentFashion==null?"无":z.ioperatePaymentFashion.pfashionName }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
								<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
									<label class="layui-form-label">票据类型</label>
									<div class="layui-input-block">
										<input type="text" value='${z.ioperatePaymentFashion==null?"无":z.ioperatePaymentFashion.pfashionReceipt }' name="number" autocomplete="off" class="layui-input">
									</div>
								</div>
							</div>
						</fieldset>
						<br />
						<br />
						<hr class="layui-bg-orange" />
					</div>
				</c:forEach>
			</form>
		</div>

		<!--作废弹出框-->
		<div id="zuofeiModeal" style="display: none;">
			<table lay-filter="zuofeiModeal">
				<thead>
					<tr>
						<th lay-data="{field:'bianhao', width:180}">操作编号</th>
						<th lay-data="{field:'fenpeiren', width:120}">分配人</th>
						<th lay-data="{field:'zhixingren', width:150}">分配时间</th>
						<th lay-data="{field:'kuwei', width:120}">执行人</th>
						<th lay-data="{field:'guoli', width:100}">过磅/理算</th>
						<th lay-data="{field:'jianshu', width:100}">库位</th>
						<th lay-data="{field:'zhongliang', width:100}">操作重量</th>
						<th lay-data="{field:'jianshu', width:100}">操作件数</th>
						<th lay-data="{field:'tianche', width:100}">天车工</th>
						<th lay-data="{field:'zhuangxie', width:160}">装卸工</th>
						<th lay-data="{fixed: 'right', width:120, align:'center', toolbar: '#barDemo'}"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${iolist }" var="io">
						<c:if test="${io.ioperateDefinedTwo!='订单作废' }">
							<tr>
								<td>${io.ioperateId }</td>
								<td>${io.interiorUserByIoperateDispatcherId.iuserName }</td>
								<td>${io.ioperateDispatcherTime }</td>
								<td>${io.interiorUserByIoperateStoremanId.iuserName }</td>
								<td>${io.ioperatePonderationTrue }</td>
								<td>${io.tarehouse.tarehouseName }</td>
								<td>${io.ioperateRealityWeight }</td>
								<td>${io.ioperateRealityNumber }</td>
								<td>${io.ioperateCraneman }</td>
								<td>${io.ioperateStevedore }</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
			<script type="text/html" id="barDemo">
				<a class="layui-btn layui-btn-danger layui-btn-small" lay-event="del">作废</a>
			</script>
		</div>

		<!--订单时间修改弹出框-->
		<div id="timeUpdateOpen" style="display: none; margin-top: 20px;">
			<form class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label">订单编号</label>
					<div class="layui-input-block">
						<input type="text" name="timeUId" readonly='readonly' id="timeUId" value="${is.input.inputId }" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">订单时间</label>
					<div class="layui-input-block">
						<input type="text" name="indentTime" id="indentTime" value="${is.input.inputCreateTime }" class="layui-input">
					</div>
				</div>
			</form>
		</div>

		<!--订单修改弹出模态框-->
		<div id="updateOpen" style="display: none;">
			<form class="layui-form layui-form-pane">
				<input type="hidden" value="<%=bgupdate%>" id="bgupdate" />
				<input type="hidden" value="<%=ddupdate%>" id="ddupdate" />
				<input type="hidden" value="<%=cwupdate%>" id="cwupdate" />
				<input type="hidden" value="<%=updateshenpi%>" id="updateshenpi" />

				<div class="layui-tab">
					<ul class="layui-tab-title">
						<%
						if (bgupdate != 0 || ddupdate != 0) {
						%>
						<li class="layui-this">仓储修改</li>
						<%
						}
						%>
					</ul>
					<div class="layui-tab-content">
						<div class="layui-tab-item">
							<table lay-filter="updatecangchuModeal">
								<thead>
									<tr>
										<th lay-data="{field:'bianhao',fixed: 'left', width:200}">操作编号</th>
										<th lay-data="{field:'fenpeiren', width:100}">分配人</th>
										<th lay-data="{field:'fenpeitime', width:180}">分配时间</th>
										<th lay-data="{field:'zhixingren', width:120}">执行人</th>
										<th lay-data="{field:'guoli', width:100}">过磅/理算</th>
										<th lay-data="{field:'kuwei', width:120}">库位</th>
										<th lay-data="{field:'zhongliang', width:120}">操作重量</th>
										<th lay-data="{field:'jianshu', width:120}">操作件数</th>
										<th lay-data="{field:'tianche', width:120}">天车工</th>
										<th lay-data="{field:'zhuangxie', width:150}">装卸工</th>
										<th lay-data="{fixed: 'right', width:150, align:'center', toolbar:'#cangchudemo'}"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${iolist }" var="io" varStatus="eos">
										<c:if test="${io.ioperateDefinedTwo!='订单作废' }">
											<tr>
												<td>${io.ioperateId }</td>
												<td>${io.interiorUserByIoperateDispatcherId.iuserName }</td>
												<td>${io.ioperateDispatcherTime }</td>
												<td>${io.interiorUserByIoperateStoremanId.iuserName }</td>
												<td>${io.ioperatePonderationTrue }</td>
												<td>${io.tarehouse.tarehouseName }</td>
												<td>${io.ioperateRealityWeight }</td>
												<td>${io.ioperateRealityNumber }</td>
												<td>${io.ioperateCraneman }</td>
												<td>${io.ioperateStevedore }</td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<script type="text/html" id="cangchudemo">
								<a class="layui-btn layui-btn-small" lay-event="edit">编辑</a>
							</script>
						</div>
						<div class="layui-tab-item">
						</div>
					</div>
				</div>
			</form>
		</div>

		<!--仓储修改实际操作弹出层-->
		<div id="cangchuUpdate" style="display: none; margin: 10px;">
			<form class="layui-form layui-form-pane">
				<input type="hidden" name="bianhao" id="ccbianhao" />
				<div class="layui-form-item">
					<label class="layui-form-label">执行人</label>
					<div class="layui-input-block">
						<select name="zhixingren" id="zhixingren" lay-verify="required"></select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">过磅/理算</label>
					<div class="layui-input-block">
						<select name="guoli" id="guoli" lay-verify="required">
							<option value="理算">理算</option>
							<option value="过磅">过磅</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">库位</label>
					<div class="layui-input-block">
						<select name="kuwei" id="kuwei" lay-filter="kuwei" lay-verify="required">
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">操作重量</label>
					<div class="layui-input-block">
						<input type="text" name="weight" id="weight" lay-verify="required|number" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">操作件数</label>
					<div class="layui-input-block">
						<input type="text" name="number" id="number" lay-verify="required|number" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">天车工</label>
					<div class="layui-input-block">
						<select name="tianche" id="tianche" lay-verify="required">
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">装卸工</label>
					<div class="layui-input-block">
						<input type="text" name="zhuangxie" id="zhuangxie" lay-verify="required" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="ccbeizhu" id="ccbeizhu" class="layui-input">
					</div>
				</div>
				<button class="layui-btn" id="cangchuTijiao" style="display: none;" lay-submit lay-filter="cangchuTijiao">立即提交</button>
			</form>
		</div>

		<!--填写订单修改的审批弹出层-->
		<div id="updateShenpi" style="margin: 10px; display: none;">
			<form class="layui-form layui-form-pane">
				<input type="hidden" value="${is.iseedId }" name="urziid" id="urziid" />
				<input type="hidden" value="${is.input.inputId }" name="urzongid" id="urzongid" />
				<input type="hidden" value="${is.input.client.clientAbbreviation }" name="huozhu" id="huozhu" />
				<input type="hidden" name="urcaozuoid" id="urcaozuoid" />
				<input type="hidden" value="入库订单" name="urupdatetype" id="urupdatetype" />

				<div class="layui-form-item">
					<label class="layui-form-label">申请描述</label>
					<div class="layui-input-block">
						<input type="text" name="miaoshu" id="miaoshu" lay-verify="required" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
               <label class="layui-form-label">审批人</label>
               <div class="layui-input-block">
                  <select name="shenpiren" id="shenpiren" lay-verify="required" class="layui-select" lay-search>
                     <option value="">请选择审批人</option>
                  </select>
               </div>
            </div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="urupdateremark" id="urupdateremark" class="layui-input">
					</div>
				</div>
				<button class="layui-btn" id="shengpiTijiao" style="display: none;" lay-submit lay-filter="shengpiTijiao">立即提交</button>
			</form>
		</div>
	</body>
	<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
	<script src="/XGProject/xxcc-sl/js/call-client.js"></script>

	<!--引入自写js-->
	<script src="/XGProject/xxcc-sl/page/data/js/dingDanRuKuXiangQing.js"></script>

	<script>
		inputBg();
	</script>

</html>