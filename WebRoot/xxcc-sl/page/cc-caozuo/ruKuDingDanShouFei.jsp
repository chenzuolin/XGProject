<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-入库订单收费详情</title>
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

						<input type="hidden" value="${caozuoid}" name="ioperateId" id="exportoperateid" />
						<input type="hidden" id="eseedId" value="${ziId}" />

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
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3">
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
					<fieldset>
						<legend style="font-size: 1.5rem;">操作信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">保管员</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.interiorUserByIoperateStoremanId.iuserName}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">开始操作时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value="${inputOperate.ioperateScreateTime}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">结束操作时间</label>
								<div class="layui-input-block">
									<input type="text" name="guoli" value="${inputOperate.ioperateSfinishTime}" autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" id="tarehouseName" value="${inputOperate.ioperateRealityWeight}吨" autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作件数</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.ioperateRealityNumber}${inputOperate.inputSeed.goods.goodsUnit.goodsUnitName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">天车工</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${inputOperate.ioperateCraneman}' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">装卸工</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${inputOperate.ioperateStevedore}' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">司磅员</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${inputOperate.interiorUserByIoperatePonderationManId==null?"无":inputOperate.interiorUserByIoperatePonderationManId.iuserName }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过磅重量</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.ioperatePonderationTrue=="理算"?"0":inputOperate.ioperateRealityWeight==null?"0":inputOperate.ioperateRealityWeight }吨' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">过磅时间</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${inputOperate.ioperatePonderationTime==null?"无":inputOperate.ioperatePonderationTime }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">车号</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${inputOperate.ioperateTruckNum==null?"无":inputOperate.ioperateTruckNum }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">操作状态</label>
								<div class="layui-input-block">
									<input type="text" name="beizhu" value='${inputOperate.ioperateDefinedTwo }' autocomplete="off" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">备注</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${inputOperate.ioperateRemark==null?"无":z.ioperateRemark }' autocomplete="off" class="layui-input">
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
									<input type="text" value="${inputOperate.interiorUserByIoperateAuditingId.iuserName}" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核时间</label>
								<div class="layui-input-block">
									<input type="text" value="${inputOperate.ioperateAuditingTime}" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核次数</label>
								<div class="layui-input-block">
									<input type="text" value='${inputOperate.ioperateDefinedOne==null?"无":inputOperate.ioperateDefinedOne }' class="layui-input">
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
								<input type="text" value='${inputOperate.ioperateShouldCost}元' readonly='readonly' class="layui-input">
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
								<select name="zhifu" id="zhifu" lay-filter="zhifu"></select>
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
							<label class="layui-form-label">备注</label>
							<div class="layui-input-block">
								<input type="text" name="beizhu" id="beizhu" placeholder="请输入备注" class="layui-input">
							</div>
						</div>
					</div>
				</fieldset>
				<div style="margin-top: 15px; ">
					<a class="layui-btn layui-btn-warm" onclick="javascript:window.history.go(-1);" style="color: black;"><i class="layui-icon">&#xe603;</i>返回</a>
					<button class="layui-btn" lay-submit lay-filter="formDemo"><i class="layui-icon">&#xe618;</i>立即提交</button>
					<a class="layui-btn layui-btn-normal" id="dayin"><i class="layui-icon">&#xe60a;</i>打印</a>
				</div>
				<hr class="layui-bg-orange" />
			</form>
		</div>

	</body>
	<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
	<script src="/XGProject/xxcc-sl/js/call-client.js"></script>

	<!--引入支付方式，票据类型js-->
	<script src="/XGProject/xxcc-sl/page/cc-caozuo/js/zhiFuPiaoJu.js"></script>

	<script>
		inputBg();
		layui.use(['jquery', 'form', 'layer'], function() {
			var $ = layui.jquery;
			var form = layui.form;
			var layer = layui.layer;

			//当单机打印的时候转换到打印页面
			$("#dayin").click(function() {
				var id = $("#exportoperateid").val();
				document.location.href = "/XGProject/inputOperate.do?flag=getDaYin&ioperateId=" + id+
				"&zid="+encodeURI(encodeURI($('#eseedId').val()));
			});

			//调用支付方式和票据类型加载函数
			zhifu('#zhifu');
			piaoju('#piaoju', $("#zhifu").val(), 'value');

			//当支付方式切换的时候触发
			form.on('select(zhifu)', function(obj) {
				//查询对应的票据类型
				piaoju('#piaoju', obj.value);
			});

			//当结算方式下拉框切的时候
			form.on('select(jiesuan)', function(obj) {
				if(obj.value == "日结") {
					//查询对应的支付方式
					zhifu('#zhifu');
					//查询对应的票据类型
					piaoju('#piaoju', $("#zhifu").val(), 'value');
					//当为日结的时候实收费用可用					
					$("#shishou").attr("disabled", false);
					//表单提交验证的属性开放
					$("#shishou").attr("lay-verify", "required|number");

				} else if(obj.value == "月结") {

					//为月结的时候支付方式和票据类型无
					$("#zhifu").html("<option value='无'>无</option>");
					$("#piaoju").html("<option value='无'>无</option>");
					form.render('select');
					//实收费用文本框不可用
					$("#shishou").attr("disabled", 'disabled');
					//表单验证取消
					$("#shishou").attr("lay-verify", "");
				}
			});

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

			//form提交监听
			form.on('submit(formDemo)', function(obj) {
				var data = obj.field;
				layer.confirm("确定提交吗？", {
					icon: 3,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
				}, function() {
					$.ajax({
						type: "post",
						url: "/XGProject/inputOperate.do?flag=getMoneyInputOperat",
						async: false,
						data: {
							"ioperateId": data.ioperateId,
							"ioperateRealityCost": data.shishou,
							"ioperateClientAccounts": data.jiesuan,
							"ioperatePaymentFashion": data.piaoju,
							"ioperateRemark": data.beizhu
						},
						dataType: "text",
						success: function(data) {
							var ok = data.indexOf("提交成功");
							if(ok != -1) {
								layer.alert("提交成功！", {
									icon: 1,
									anim: 4,
									closeBtn: 2,
									title: ['系统提示', 'font-size:16px;'],
                					skin: 'layui-layer-blue',
								}, function() {
									document.location.reload();
								});
							} else {
								layer.alert("提交失败！", {
									icon: 1,
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
								closeBtn: 2,
								anim: 4,
								title: ['系统提示', 'font-size:16px;'],
               					skin: 'layui-layer-blue',
							});
						}
					});
				});
				return false;
			});
		});
	</script>

</html>