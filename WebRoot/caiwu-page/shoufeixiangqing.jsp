<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit内核-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--为移动设备添加 viewport-->
<title>仓储管理系统登陆</title>
<meta name="description"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。致力于为广大商户提供快速、安全、便捷的仓储物流服务" />
<meta name="keywords"
	content="甘肃鑫港物流有限公司仓储管理系统。快速、安全、便捷。钢材、木材、百货、物流、汽车货运、铁路货运" />
<meta name="author" content="">
<meta name="format-detection" content="telephone=no" />
<!--禁止数字识自动别为电话号码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--禁止百度转码-->
<link rel="shortcut icon" type="image/ico" href="/favicon.ico" />
<!--添加 favicon icon -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/selectize.default.css">
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="js/selectize.js"></script>
<style type="text/css">
.selectize-control.demo-default.single {
	height: 34px;
}

.input-group>.input-group-addon {
	background: #337AB7;
	color: #fff;
}

.table-hover {
	font-size: 16px;
	font-weight: bold;
}
</style>
</head>

<body>
	<div class="container">
		<div>
			<ol class="breadcrumb">
				<span>当前位置：</span>
				<li>结算中心</li>
				<li><a href="javascript:window.history.go(-1);">返回上一级</a></li>
				<li onclick="javascript:document.location.reload();"
					style="cursor:pointer;">收费明细</li>
			</ol>
		</div>

		<div class="panel panel-primary">
			<div class="panel-heading">
				<p class="panel-title" style="text-align: center; font-size: 18px;">收费明细</p>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<tr>
						<td>客户名称</td>
						<td style="border-right:1px solid #888888;">${ac.client.clientAbbreviation
							}</td>
						<td>起始日期</td>
						<td>${ac.accountsCreateTime }</td>
					</tr>
					<tr>
						<td>结束日期</td>
						<td style="border-right:1px solid #888888;">${ac.accountsFinishTime
							}</td>
						<td>汽运入库费</td>
						<td>${ac.rukuCost }元</td>
					</tr>
					<tr>
						<td>火运入库费</td>
						<td style="border-right:1px solid #888888;">${ac.zidingyiFour
							}元</td>
						<td>汽运出库费</td>
						<td>${ac.zidingyiFive }元</td>
					</tr>
					<tr>
						<td>火运出库费</td>
						<td style="border-right:1px solid #888888;">${ac.zidingyiSix
							}元</td>
						<td>下站费（出）</td>
						<td>${ac.chukumaxtime }元</td>
					</tr>
					<tr>
						<td>过户费</td>
						<td style="border-right:1px solid #888888;">${ac.guohuCost }元</td>
						<td>下站费（过）</td>
						<td>${ac.zhuanchukumaxtime }元</td>
					</tr>
					<tr>
						<td>仓储费</td>
						<td style="border-right:1px solid #888888;">${ac.cangchuCost
							}元</td>
						<td style="color: red;">轧账费用合计</td>
						<td style="color: red;">${ac.accountsExpensesSeed }元</td>
					</tr>
					<tr>
						<td>滞纳金起征时间</td>
						<td style="border-right:1px solid #888888;">${ac.shoufeiqixian
							}</td>
						<td>滞纳金结束时间</td>
						<td>${ac.accountsCollectTime==null?"无":ac.accountsCollectTime
							}</td>
					</tr>
					<tr>
						<td>滞纳金费率</td>
						<td style="border-right:1px solid #888888;">${ac.zhinaFeilv
							}元</td>
						<td style="color: red;">滞纳金合计</td>
						<td style="color: red;">${ac.accountsSeed==null?0.0:ac.accountsSeed
							}元</td>
					</tr>
					<tr>
						<td style="color: red;">总费用合计</td>
						<td style="color: red; border-right:1px solid #888888;">${ac.accountsSeed+ac.accountsExpensesSeed
							}元</td>
						<td>结算人</td>
						<td>${ac.jiesuanren.iuserName }</td>
					</tr>
					<tr>
						<td>结算时间</td>
						<td style="border-right:1px solid #888888;">${ac.jiesuantime
							}</td>
						<td>收费人</td>
						<td>${ac.shoufeiren==null?"无":ac.shoufeiren.iuserName }</td>
					</tr>
					<tr>
						<td>收费时间</td>
						<td style="border-right:1px solid #888888;">${ac.accountsCollectTime==null?"无":ac.accountsCollectTime
							}</td>
						<td>缴费人</td>
						<td>${ac.jiaofeiren==null?"无":ac.jiaofeiren }</td>
					</tr>
					<tr>
						<td>支付方式</td>
						<td style="border-right:1px solid #888888;">${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionName
							}</td>
						<td>票据类型</td>
						<td>${ac.accountsDefinedTwo==null?"无":ac.accountsDefinedTwo.pfashionReceipt
							}</td>
					</tr>
					<tr>
						<td>实收费用</td>
						<td style="border-right:1px solid #888888;">${ac.shishouCost==null?0:ac.shishouCost }元</td>
						<td>备注</td>
						<td>${ac.accountsRemark }</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	$(document).ready(function() {
		$("a.1ji").click(function() {
			$(this).next("ol").slideToggle()
			// alert("你进入了 p1!");
			// $(this).css("color", "red")

		});
		$("ol.jszx").slideDown();

	});
</script>

</html>
