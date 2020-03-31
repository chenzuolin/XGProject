<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>仓储管理系统-过户订单收费详情</title>
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
					<fieldset>
						<legend style="font-size: 1.5rem;">审核信息</legend>
						<div class="layui-row layui-col-space30">
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核人</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${sss.interiorUserBySsseedAuditing==null?"无":sss.interiorUserBySsseedAuditing.iuserName }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核时间</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${sss.ssseedAuditingTime==null?"无":sss.ssseedAuditingTime }' autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
								<label class="layui-form-label">审核结果</label>
								<div class="layui-input-block">
									<input type="text" name="number" value='${sss.ssseedAuditingTrue==null?"无":sss.ssseedAuditingTrue }' autocomplete="off" class="layui-input">
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
								<input type="text" value="${sss.ssseedShouldCost}元" readonly="readonly" class="layui-input">
							</div>
						</div>
						<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
							<label class="layui-form-label">实收费用</label>
							<div class="layui-input-block">
								<input type="text" name="shishou" id="shishou" placeholder="请输入实收费用" lay-verify="required|number" class="layui-input">
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
								<select name="piaoju" id="piaoju"></select>
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
				<input type="hidden" value="${sss.shiftStock.sstockId }" name="xadefinedtwo" />
				<input type="hidden" value="过户" name="xzdefinedthree" />
				<input type="hidden" value="${sss.shiftStock.clientBySstockClientId.clientId}" name="zhuanchuclient" />
				<input type="hidden" value="${sss.shiftStock.clientBySstockShiftToFirm.clientId}" name="zhuanruclient" />
				<input type="hidden" value="${sss.ssseedId }" name="xadefinedfive" />
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
						<input type="text" name="weight" readonly="readonly" value="${sss.ssseedWeight }" id="weight" class="layui-input" />
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

	<script>
		inputBg();
		layui.use(['jquery', 'form', 'layer'], function() {
			var $ = layui.jquery;
			var form = layui.form;
			var layer = layui.layer;

			//当单机打印的时候转换到对应的打印的页面
			$("#dayin").click(function() {
				document.location.href = "/XGProject/shiftStockSeed.do?flag=getXiangQing&ff=dayin&ssseedId=" + encodeURI(encodeURI($("#exportoperateid").val()));
			});
			
			layer.tips('若产生下站费，点击此处收取相应的下站费，然后收取过户费用！', '#xiazhan', {
				tips: 1,
				time:6000
			});

			//当单机下站费按钮的时候打开对应的弹出层
			$("#xiazhan").click(function() {
				layer.open({
					type: 1,
					title: ['收取下站费', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
					closeBtn: 2,
					anim: 4,
					content: $('#xiazhanOpen'),
					area: ['500px', '600px'],
					btn: ['立即提交', '关闭'],
					yes: function(index, obj) {
						$("#formDemo").click();
					},
					btn2: function(index, obj) {
						//点击关闭的时候触发
						layer.close(index);
					}
				});
			});

			//查询下站费中选择的客户
			client("#clientByXzzhuanruclient");

			//当光标移除下站费单价的时候自动计算对应的下站费合计
			$("#danjia").blur(function() {
				var reg_zhekou = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
				if($(this).val() != null && $(this).val() != undefined && $(this) != "" && reg_zhekou.test($(this).val())) {
					var sum = parseFloat($(this).val()) * parseFloat($("#weight").val());
					$("#heji").val(sum);
				}
			});

			//加载下站费中的支付方式和票据类型
			zhifu('#xiazhifu');
			piaoju("#xiapiaoju", $("#xiazhifu").val(), 'text');

			//当下站费中的结算方式切换的时候触发
			form.on('select(xiajiesuan)', function(obj) {
				if(obj.value == "日结") {
					//查询对应的支付方式
					zhifu('#xiazhifu');
					//查询对应的票据类型
					piaoju('#xiapiaoju', $("#xiazhifu").val(), 'text');
					//当为日结的时候实收费用可用					
					$("#xiashishou").attr("disabled", false);
					//表单提交验证的属性开放
					$("#xiashishou").attr("lay-verify", "required|number");

				} else if(obj.value == "月结") {

					//为月结的时候支付方式和票据类型无
					$("#xiazhifu").html("<option value='无'>无</option>");
					$("#xiapiaoju").html("<option value='无'>无</option>");
					form.render('select');
					//实收费用文本框不可用
					$("#xiashishou").attr("disabled", 'disabled');
					//表单验证取消
					$("#xiashishou").attr("lay-verify", "");
				}
			});

			form.on('submit(xiaformDemo)', function(obj) {
				//用ajax的方式进行提交
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
						url: "/XGProject/xiazhanfeiAction.do?flag=SaveXiaZhan",
						async: false,
						data: {
							"xadefinedtwo": data.xadefinedtwo,
							"xzdefinedthree": data.xzdefinedthree,
							"xadefinedfive": data.xadefinedfive,
							"zhuanchuclient": data.zhuanchuclient,
							"clientByXzzhuanruclient": data.clientByXzzhuanruclient,
							"xzdanjia": data.danjia,
							"xzweight": data.weight,
							"xzcount": data.heji,
							"xzdefinedone": data.xiajiesuan,
							"xzzhifu": data.xiazhifu,
							"xzpiaoju": data.xiapiaoju,
							"xadefinedfour": data.xiashishou,
							"xzremark": data.xiabeizhu
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

			//查询对应的支付方式
			zhifu('#zhifu');
			//查询对应的票据类型
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

			//表单提交
			form.on('submit(formDemo)', function(obj) {
				var data = obj.field;
				layer.confirm('如果产生下站费，请先收取下站费！确定提交吗？', {
					icon: 3,
					closeBtn: 2,
					anim: 4,
					title: ['系统提示', 'font-size:16px;'],
                	skin: 'layui-layer-blue',
				}, function() {
					//用ajax的方式进行提交
					$.ajax({
						type: "post",
						url: "/XGProject/shiftStockSeed.do?flag=ShouFeiShiftStock",
						async: false,
						data: {
							"ssseedId": data.ioperateId,
							"interiorUserBySsseedCollectMan": data.interiorUserBySsseedAuditing,
							"clientBySstockClientId": data.clientBySstockClientId,
							"clientBySstockShiftToFirm": data.clientBySstockShiftToFirm,
							"ssseedRealityCost": data.shishou,
							"ssseedClientAccounts": data.jiesuan,
							"paymentFashion": data.piaoju,
							"ssseedRemark": data.beizhu
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
									document.location.reload();
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
				})
				return false;
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
		});
	</script>

</html>