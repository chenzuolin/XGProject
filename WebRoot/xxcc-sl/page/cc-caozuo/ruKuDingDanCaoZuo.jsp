<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-入库订单操作详情</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
	</head>
	<%
		int update = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("操作修改")){
				update++;
			}
		}
	%>

	<body>
		<div class="layui-fluid">
			<form class="layui-form layui-form-pane" action="" lay-filter='tianche'>
				<div id="showContent">
					<fieldset>
						<legend style="font-size: 1.5rem;">客户信息</legend>

						<input type="hidden" value="${caozuoid}" name="ioperateId" id="exportoperateid" />
						<input type="hidden" id="seedId" value="${inputOperate.inputSeed.iseedId}" />

						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">客户名称</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.client.clientAbbreviation }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">客户单号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputClientNumber}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">发起时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputCreateTime }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">车皮号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.iseedDefinedOne }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">运输方式</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputCarryType}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">车号</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputPlateNumber}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">司机姓名</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputDriverName}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">司机电话</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputDriverTel}" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">订单有效期</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputDefinedOne }天" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.input.inputRemark}" autocomplete="off" class="layui-input">
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
									<input type="text" name="number" value="${inputOperate.inputSeed.goods.goodsCategory.goodsCategoryName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物名称</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.goods.goodsName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物规格</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.goods.goodsStandard.goodsStandardName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物材质</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.goods.goodsQuality.goodsQualityName }" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物属性</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.goods.goodsProperty.goodsPropertyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">货物产地</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.goods.goodsYieldly.goodsYieldlyName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">应收重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.iseedShouldWeight }吨" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">实收重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.inputSeed.iseedRealityWeight==null?"0":inputOperate.inputSeed.iseedRealityWeight }吨' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">实收件数</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.inputSeed.iseedRealityNumber==null?"0":inputOperate.inputSeed.iseedRealityNumber }${inputOperate.inputSeed.goods.goodsUnit.goodsUnitName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">应收费用</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.inputSeed.iseedShouldCost==null?"0":inputOperate.inputSeed.iseedShouldCost }元' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">实收费用</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.inputSeed.iseedRealityCost==null?"0":inputOperate.inputSeed.iseedRealityCost }元' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">订单状态</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.inputSeed.iseedOrderStatus }" autocomplete="off" class="layui-input">
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
									<input type="text" name="number" value="${inputOperate.interiorUserByIoperateDispatcherId.iuserName }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">分配时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.ioperateDispatcherTime }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过磅/理算</label>
								<div class="layui-input-block">
									<input type="text" name="guoli" value="${inputOperate.ioperatePonderationTrue }" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">库位</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="tarehouseName" value="${inputOperate.tarehouse.tarehouseName}" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">分配重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.inputSeed.iseedShouldWeight}吨' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${inputOperate.ioperateRemark==null?"无":inputOperate.ioperateRemark }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
					</fieldset>
				</div>
				<fieldset>
					<legend style="font-size: 1.5rem;">填写信息</legend>
					<div class="layui-row layui-col-space30">
						<c:if test="${inputOperate.ioperatePonderationTrue=='理算' }">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作重量</label>
								<div class="layui-input-block">
									<input type="text" name="weight" lay-verify="required|number" placeholder="请输入操作重量" class="layui-input">
								</div>
							</div>
						</c:if>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">操作件数</label>
							<div class="layui-input-block">
								<input type="text" name="number" lay-verify="required|number" placeholder="请输入操作件数" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">天车工</label>
							<div class="layui-input-block">
								<select name="tianche" lay-verify="required" id="tianche">
								</select>
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">装卸工</label>
							<div class="layui-input-block">
								<input type="text" name="zhuangxie" placeholder="请输入装卸工，每个人用空格隔开" lay-verify="required" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-row layui-col-space30">
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">车号</label>
							<div class="layui-input-block">
								<input type="text" name="chehao" lay-verify="required" placeholder="请输入车号" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">备注</label>
							<div class="layui-input-block">
								<input type="text" name="beizhu" placeholder="请输入备注" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<div style="margin-top: 15px; ">
					<a class="layui-btn layui-btn-warm" onclick="javascript:window.history.go(-1);" style="color: black;"><i class="layui-icon">&#xe603;</i>返回</a>
					<%
					if(update!=0){
					%>
					<a class="layui-btn" id="update" onclick="tiaozhuanchuku()"><i class="layui-icon">&#xe642;</i>订单修改</a>
					<%
					}
					%>
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				</div>
				<hr class="layui-bg-orange" />
			</form>
		</div>

		<!--修改订单对应的弹出层-->
		<div id="updateOpen" style="display: none; margin-top: 20px;">
			<form class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<label class="layui-form-label">对应库位</label>
					<div class="layui-input-block">
						<select name="kuwei" id="kuwei">
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" name="beizhu3" placeholder="请输入备注" id="beizhu3" class="layui-input">
					</div>
				</div>
			</form>
		</div>
	</body>
	<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
	<script src="/XGProject/xxcc-sl/js/call-client.js"></script>

	<!--引入自写js-->
	<script src="/XGProject/xxcc-sl/page/cc-caozuo/js/ruKuDingDanCaoZuo.js"></script>

	<script>
		inputBg();
	</script>

</html>