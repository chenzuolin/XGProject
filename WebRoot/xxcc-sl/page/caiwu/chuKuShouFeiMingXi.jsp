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
				<th lay-data="{field:'i', width:120}">二次作业费</th>
				<th lay-data="{field:'t', width:130}">应收下站费</th>
				<th lay-data="{field:'u', width:130}">实收下站费</th>
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
			<c:forEach items="${eolist }" var="z" varStatus="zi">
				<tr>
					<td>${es.export.exportId }</td>
					<td>${z.eoperateId }</td>
					<td>${es.export.client.clientAbbreviation }</td>
					<td>${es.goods.goodsName }</td>
					<td>${z.interiorUserByEoperateCollectMan==null?"无":z.interiorUserByEoperateCollectMan.iuserName
						}</td>
					<td>${z.eoperateCollectTime==null?"无":z.eoperateCollectTime }</td>
					<td>${z.eoperateShouldCost==null?"0":z.eoperateShouldCost }元</td>
					<td>${z.eoperateRealityCost==null?"0":z.eoperateRealityCost }元</td>
					<td>${z.eoperateDefinedFour==null?"0":z.eoperateDefinedFour }元</td>
					<td><c:forEach items="${xzlist }" var="x">
							<c:if test="${x.xadefinedfive==z.eoperateId }">
									${x.xzcount }元
								</c:if>
						</c:forEach></td>
					<td><c:forEach items="${xzlist }" var="x">
							<c:if test="${x.xadefinedfive==z.eoperateId }">
									${x.xadefinedfour }元
								</c:if>
						</c:forEach></td>
					<td>${z.eoperateClientAccounts==null?"无":z.eoperateClientAccounts
						}</td>
					<td>${z.eoperatePaymentFashion==null?"无":z.eoperatePaymentFashion.pfashionName
						}</td>
					<td>${z.eoperatePaymentFashion==null?"无":z.eoperatePaymentFashion.pfashionReceipt
						}</td>
					<td>${z.interiorUserByEoperateDispatcher.iuserName }</td>
					<td>${z.eoperateDispatcherTime }</td>
					<td>${z.interiorUserByEoperateStoreman.iuserName}</td>
					<td>${z.eoperateRealityWeight==null?"0":z.eoperateRealityWeight}吨</td>
					<td>${z.eoperateRealityNumber==null?"0":z.eoperateRealityNumber
						}${z.exportSeed.goods.goodsUnit.goodsUnitName }</td>
					<td>${z.interiorUserByEoperateAuditing==null?"无":z.interiorUserByEoperateAuditing.iuserName
						}</td>
					<td>${z.eoperateAuditingTime==null?"无":z.eoperateAuditingTime
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