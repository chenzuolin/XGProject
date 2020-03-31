<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<object id=”WebBrowser”
	classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=”0″ width=”0″>
</object>
<title>${is.input.client.clientFirmName }入库订单</title>
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<style>
* {
	padding: 0;
	margin: 0;
}

.context_print {
	width: 1300px;
	margin: 0 auto;
}

.context_print table {
	width: 100%;
	border-color: #F5E79E;
}

.context_print h4 {
	text-align: center;
	margin-bottom: 20px;
}

.context_print table tr td {
	
}

.context_print table tr {
	height: 30px;
	line-height: 30px;
}

.tr_center td {
	text-align: center;
}

.middle_tab {
	border: 0;
}

.padding_top strong {
	display: block;
	padding-top: 10px;
	padding-bottom: 10px;
}
</style>
<script>
	alert("提交成功！");
	function printPage() {
		$("#printImg").css("display", "none");
		$("#houtui").css("display", "none");
		window.print();
		$("#printImg").css("display", "");
		$("#houtui").css("display", "");
		return false;
	}
</script>
</head>

<body>
	<div class="context_print">
		<h4>${is.input.client.clientFirmName }入库订单</h4>

		<table cellpadding="0" cellspacing="0" border="1">
			<tr class="padding_top">
				<th colspan="13"><strong>${is.input.inputId }</strong></th>
			</tr>
			<tr>
				<td colspan="10">
					<table border="1" cellpadding="0" cellspacing="0"
						class="middle_tab">
						<tr>
							<td align="right">客户名称：</td>
							<td>${is.input.client.clientFirmName }</td>
							<td align="right">发起日期：</td>
							<td>${is.input.inputCreateTime }</td>
							<td align="right">客户单号：</td>
							<td>${is.input.inputClientNumber }</td>
							<td align="right">运输方式：</td>
							<td>${is.input.inputCarryType }</td>
						</tr>
						<tr>
							<td align="right">司机姓名：</td>
							<td>${is.input.inputDriverName }</td>
							<td align="right">司机电话：</td>
							<td>${is.input.inputDriverTel }</td>
							<td align="right">车号：</td>
							<td>${is.input.inputPlateNumber }</td>
							<td colspan="2"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr class="padding_top">
				<th colspan="13"><strong>货物详情</strong></th>
			</tr>
			<tr>
				<td colspan="10">
					<table border="1" cellpadding="0" cellspacing="0"
						class="middle_tab">
						<tr>
							<th>货物品类</th>
							<th>名称</th>
							<th>规格</th>
							<th>材质</th>
							<th>属性</th>
							<th>产地</th>

						</tr>
						<tr class="tr_center">
							<td>${is.goods.goodsCategory.goodsCategoryName }</td>
							<td>${is.goods.goodsName }</td>
							<td>${is.goods.goodsStandard.goodsStandardName }</td>
							<td>${is.goods.goodsQuality.goodsQualityName }</td>
							<td>${is.goods.goodsProperty.goodsPropertyName }</td>
							<td>${is.goods.goodsYieldly.goodsYieldlyName }</td>

						</tr>
					</table>
				</td>
			</tr>
			<tr class="padding_top">
				<th colspan="13"><strong>操作详情</strong></th>
			</tr>
			<tr>
				<td colspan="10">
					<table border="1" cellpadding="0" cellspacing="0"
						class="middle_tab">
						<tr>
							<th>应收重量</th>
							<th>实收重量</th>
							<th>应收费用</th>
							<th>实收费用</th>
							<th>结算方式</th>
							<th>支付方式</th>
							<th>票据类型</th>
						</tr>
						<tr class="tr_center">
							<td>${is.iseedShouldWeight }吨</td>
							<td>${io.ioperateRealityWeight }吨</td>
							<td>${io.ioperateShouldCost }元</td>
							<td>${io.ioperateRealityCost }元</td>
							<td>${is.iseedClientAccounts==null?"无":is.iseedClientAccounts
								}</td>
							<td>${is.paymentFashion==null?"无":is.paymentFashion.pfashionName
								}</td>
							<td>${is.paymentFashion==null?"无":is.paymentFashion.pfashionReceipt
								}</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<a onclick="printPage()"
			style="cursor:pointer; padding:3px 8px; background-color:#337AB7; color:#FFFFFF; margin-top:5px;"
			id="printImg">打印</a><a href="javascript:window.history.go(-1);"
			id="houtui"
			style="cursor:pointer;background-color:#337AB7; color:#FFFFFF; padding:3px 8px; margin-left:2px; margin-top:5px; display: none;">后退</a>
	</div>
</body>

</html>