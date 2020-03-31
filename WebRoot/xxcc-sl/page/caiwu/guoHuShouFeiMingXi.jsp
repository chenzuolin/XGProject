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
				<th lay-data="{field:'b', width:130}">转出单位</th>
				<th lay-data="{field:'c', width:130}">转入单位</th>
				<th lay-data="{field:'d', width:160}">过户时间</th>
				<th lay-data="{field:'e', width:100}">货物名称</th>
				<th lay-data="{field:'f', width:100}">过户重量</th>
				<th lay-data="{field:'g', width:100}">转库类型</th>
				<th lay-data="{field:'h', width:100}">收费员</th>
				<th lay-data="{field:'i', width:160}">收费时间</th>
				<th lay-data="{field:'j', width:120}">应收费用</th>
				<th lay-data="{field:'k', width:120}">实收费用</th>
				<th lay-data="{field:'q', width:120}">应收下站费</th>
				<th lay-data="{field:'r', width:120}">实收下站费</th>
				<th lay-data="{field:'l', width:100}">结算方式</th>
				<th lay-data="{field:'m', width:100}">支付方式</th>
				<th lay-data="{field:'n', width:100}">票据类型</th>
				<th lay-data="{field:'o', width:100}">审核员</th>
				<th lay-data="{field:'p', width:160}">审核时间</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${ss.shiftStock.sstockId }</td>
				<td>${ss.shiftStock.clientBySstockClientId.clientAbbreviation }</td>
				<td>${ss.shiftStock.clientBySstockShiftToFirm.clientAbbreviation
					}</td>
				<td>${ss.shiftStock.sstockReateTime }</td>
				<td>${ss.goods.goodsName }</td>
				<td>${ss.ssseedWeight }吨</td>
				<td>${ss.shiftStock.sstockDefinedOne==null?"无":ss.shiftStock.sstockDefinedOne
					}</td>
				<td>${ss.interiorUserBySsseedCollectMan==null?"无":ss.interiorUserBySsseedCollectMan.iuserName
					}</td>
				<td>${ss.ssseedCollectTime==null?"无":ss.ssseedCollectTime }</td>
				<td>${ss.ssseedShouldCost==null?"0":ss.ssseedShouldCost }元</td>
				<td>${ss.ssseedRealityCost==null?"0":ss.ssseedRealityCost }元</td>
				<td><c:forEach items="${xzlist }" var="x">
						<c:if test="${x.xadefinedfive==ss.ssseedId }">
								${x.xzcount }元
							</c:if>
					</c:forEach></td>
				<td><c:forEach items="${xzlist }" var="x">
						<c:if test="${x.xadefinedfive==ss.ssseedId }">
								${x.xadefinedfour }元
							</c:if>
					</c:forEach></td>
				<td>${ss.ssseedClientAccounts==null?"无":ss.ssseedClientAccounts
					}</td>
				<td>${ss.paymentFashion==null?"无":ss.paymentFashion.pfashionName
					}</td>
				<td>${ss.paymentFashion==null?"无":ss.paymentFashion.pfashionReceipt
					}</td>
				<td>${ss.interiorUserBySsseedAuditing==null?"无":ss.interiorUserBySsseedAuditing.iuserName
					}</td>
				<td>${ss.ssseedAuditingTime==null?"无":ss.ssseedAuditingTime }</td>
			</tr>
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