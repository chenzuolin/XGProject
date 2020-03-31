<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>仓储管理系统-月结收费明细</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css"
	media="all">
</head>

<body>
	<div class="layui-fluid">
		<!--头部填写表单，搜索表单-->
		<fieldset>
			<legend>月结收费明细</legend>
			<form class="layui-form layui-form-pane" id="showContent">
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">客户名称</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value="${ac.client.clientAbbreviation }" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">起始日期</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value="${ac.accountsCreateTime }" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">结束日期</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value="${ac.accountsFinishTime }" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">汽运入库费</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.rukuCost }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">火运入库费</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.zidingyiFour }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">汽运出库费</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.zidingyiFive }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">火运出库费</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.zidingyiSix }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">下站费（出）</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.chukumaxtime }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">过户费</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.guohuCost }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">下站费（过）</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value="${ac.zhuanchukumaxtime }元" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">仓储费</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.cangchuCost }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">轧账费用合计</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value="${ac.accountsExpensesSeed }元" autocomplete="off"
								class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">滞纳金起征日</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.shoufeiqixian }"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">滞纳金结束日</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.accountsCollectTime==null?"无":ac.accountsCollectTime }'
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">滞纳金费率</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.zhinaFeilv }元/天"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">滞纳金合计</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.accountsSeed==null?0.0:ac.accountsSeed }元'
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">总费用合计</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value="${ac.accountsSeed+ac.accountsExpensesSeed }元"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">结算人</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value="${ac.jiesuanren.iuserName }" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">结算时间</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.jiesuantime }"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">收费人</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.shoufeiren==null?"无":ac.shoufeiren.iuserName }'
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">收费时间</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.accountsCollectTime==null?"无":ac.accountsCollectTime }'
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">缴费人</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.jiaofeiren==null?"无":ac.jiaofeiren }'
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">支付方式</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionName }'
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">票据类型</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionReceipt }'
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				<div class="layui-row layui-col-space30">
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">实收费用</label>
						<div class="layui-input-block">
							<input type="text" name="number"
								value='${ac.shishouCost==null?0:ac.shishouCost }元'
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-col-xs12  layui-col-sm6 layui-col-md3 ">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-block">
							<input type="text" name="number" value="${ac.accountsRemark }"
								autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
			</form>
		</fieldset>
	</div>
</body>

<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
<script src="/XGProject/xxcc-sl/js/call-client.js" charset="utf-8"></script>
<script>
		inputBg();
		layui.use('jquery', function() {
			var $ = layui.jquery;
			//给input添加只读属性
			$('#showContent input').attr('readonly', 'readonly');
			//给关键字添加背景,突出显示
			$('label').each(function() {
				if($(this).text().indexOf("轧账费用合计") != -1 || $(this).text().indexOf("滞纳金合计") != -1 || $(this).text().indexOf("总费用合计") != -1) {
					$(this).css({
						'color': 'red',
					});
					$(this).next('div').find("input").css({
						'color': 'red',
					});
				}
			});
		});
	</script>

</html>