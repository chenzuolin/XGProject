<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>仓储管理系统-出库收费明细详情</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet"
	href="/XGProject/xxcc-sl/plugin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="/XGProject/xxcc-sl/css/public.css" />
</head>

<body>
	<table class="layui-table" lay-filter="demo">
		<thead>
			<tr>
				<th lay-data="{field:'a', width:130}">订单编号</th>
				<th lay-data="{field:'b', width:170}">操作编号</th>
				<th lay-data="{field:'c', width:130}">客户名称</th>
				<th lay-data="{field:'d', width:120}">货物名称</th>
				<th lay-data="{field:'e', width:100}">收费员</th>
				<th lay-data="{field:'f', width:160}">收费时间</th>
				<th lay-data="{field:'g', width:120}">应收费用</th>
				<th lay-data="{field:'h', width:120}">实收费用</th>
				<th lay-data="{field:'j', width:100}">结算方式</th>
				<th lay-data="{field:'k', width:100}">支付方式</th>
				<th lay-data="{field:'l', width:100}">票据类型</th>
				<th lay-data="{field:'m', width:100}">调度员</th>
				<th lay-data="{field:'n', width:160}">分配时间</th>
				<th lay-data="{field:'o', width:100}">保管员</th>
				<th lay-data="{field:'p', width:100}">操作重量</th>
				<th lay-data="{field:'q', width:100}">操作件数</th>
				<th lay-data="{field:'r', width:100}">审核员</th>
				<th lay-data="{field:'s', width:160}">审核时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${iolist }" var="z">
				<tr>
					<td>${is.input.inputId }</td>
					<td>${z.ioperateId }</td>
					<td>${is.input.client.clientAbbreviation }</td>
					<td>${is.goods.goodsName }</td>
					<td>${z.interiorUserByIoperateCollectManId==null?"无":z.interiorUserByIoperateCollectManId.iuserName
						}</td>
					<td>${z.ioperateCollectTime==null?"无":z.ioperateCollectTime }</td>
					<td>${z.ioperateShouldCost==null?"0":z.ioperateShouldCost }元</td>
					<td>${z.ioperateRealityCost==null?"0":z.ioperateRealityCost }元</td>
					<td>${z.ioperateClientAccounts==null?"无":z.ioperateClientAccounts
						}</td>
					<td>${z.ioperatePaymentFashion==null?"无":z.ioperatePaymentFashion.pfashionName
						}</td>
					<td>${z.ioperatePaymentFashion==null?"无":z.ioperatePaymentFashion.pfashionReceipt
						}</td>
					<td>${z.interiorUserByIoperateDispatcherId.iuserName }</td>
					<td>${z.ioperateDispatcherTime }"</td>
					<td>${z.interiorUserByIoperateStoremanId.iuserName }</td>
					<td>${z.ioperateRealityWeight==null?"0":z.ioperateRealityWeight
						}吨</td>
					<td>${z.ioperateRealityNumber==null?"0":z.ioperateRealityNumber
						}${z.inputSeed.goods.goodsUnit.goodsUnitName }</td>
					<td>${z.interiorUserByIoperateAuditingId==null?"无":z.interiorUserByIoperateAuditingId.iuserName
						}</td>
					<td>${z.ioperateAuditingTime==null?"无":z.ioperateAuditingTime
						}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script src="/XGProject/xxcc-sl/plugin/layui/layui.js" charset="utf-8"></script>
<script src="/XGProject/xxcc-sl/js/call-client.js"></script>
<script>
		inputBg();
		layui.use(['jquery', 'table'], function() {
			var $ = layui.jquery;
			var table = layui.table;
			table.init('demo', {
				height: 'full-20',
				limit: 300
			});
		});
	</script>

</html>