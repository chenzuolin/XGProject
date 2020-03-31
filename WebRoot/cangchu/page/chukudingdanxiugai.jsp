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

<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />

<style>
.tab_context .tab_bottom {
	width: 1100px;
	padding-top: 10px;
}

.tab_context .tab_bottom tr td select[class=zhuangxie] {
	width: 110px;
}

.qingkong {
	background-color: blue;
	color: #FFFFFF;
	text-decoration: none;
	padding: 1px 5px;
}

.chuku_context {
	width: 1100px;
	margin: 0 auto;
	padding-top: 20px;
}

.modeal_bg {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: #000000;
	opacity: 0.5;
}

.modeal_bottom_middle p {
	text-align: center;
	margin-top: 10%;
}

#chukuwancheng {
	background: url(cangchu/img/zengjiahuowu.png);
}

#chukuwancheng:hover {
	background: url(cangchu/img/zengjiahuowu2.png);
}

#update {
	background: url(cangchu/img/zengjiahuowu.png);
	display: block;
	width: 110px;
	height: 30px;
	text-align: center;
	position: absolute;
	left: 65%;
	top: 5%;
}

#update i {
	color: #FFFFFF;
	font-style: normal;
	line-height: 30px;
}

#update:hover {
	background: url(cangchu/img/zengjiahuowu2.png);
}

table {
	-moz-box-shadow: 5px 5px 5px #000;
	-webkit-box-shadow: 5px 5px 5px #000;
	box-shadow: 2px 2px 10px #000;
	border-top: 1px solid #888888;
	border-left: 1px solid #888888;
}
</style>
</head>
<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null
	|| request.getSession().getAttribute("power") == null) {
%>
<script type="text/javascript">
	$(function() {
		$("body")
				.append(
						"<div style='width:100%; height:100%; background-color:#000000; position:absolute; left:0px; top:0px; z-index:1000'></div>");
	});
</script>
<%
	}
%>
<%
	int update = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改")){
				update++;
			}
		}
%>
<body>

	<!--内容起始处-->
	<div id="mainFrame" class="main" style="padding-bottom:20px;">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-2);">订单查询</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-2);">出库详细</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">订单修改</a>
			</ul>
		</div>

		<div class="chuku_context" style="width:100%;">
			<!--form开始-->
			<form>
				<!--table开始-->
				<table class="tab_head" style="width:100%">
					<tr>
						<th>操作</th>
						<th>订单编号</th>
						<th>分配人</th>
						<th>执行人</th>
						<th>库位</th>
						<th>批次</th>
						<th>重量</th>
						<th>件数</th>
						<th>天车工</th>
						<th>装卸工</th>
						<th>是否理算</th>
						<th>应收费用</th>
						<th>实收费用</th>
						<th>结算方式</th>
						<th></th>
					</tr>
					<tr>
						<td align="right" width="80px">客户：</td>
						<td width="150px">${es.export.client.clientAbbreviation }</td>
						<td align="right" width="80px">运输方式：</td>
						<td align="left" width="150px">${es.export.exportCarryType }</td>
						<td align="right" width="80px">车号：</td>

						<td class="chehao" width="150px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							${es.export.exportWagonNumber }</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>${es.export.exportDriverName }</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">司机电话：</td>
						<td>${es.export.exportDriverTel }</td>

						<td align="right" width="80px">客户单号：</td>
						<td>${es.export.exportClientNumber }</td>
						<td>是否超发：</td>
						<td>${es.export.exportDefinedOne }</td>
						<td align="right" width="100px">订单有效期：</td>
						<td>${es.export.exportDefinedTwo }天</td>
					</tr>
					<tr>
						<td align="right" width="100px">备注：</td>
						<td colspan="7">${es.eseedRemark }</td>
					</tr>
				</table>

				<!--tabhead结束-->


</body>
<script>
	$(function() {
		//当点击出库完成的时候触发
		$("#chukuwancheng")
				.click(
						function() {
							$
									.ajax({
										type : "post",
										url : "exportSeed.do?flag=ChuKuWanChengAjax",
										data : {
											"eseedId" : $("#kzid").val()
										},
										dataType : "json",
										success : function(obj) {
											if (obj[0].result == "no") {
												if (confirm("该订单正在操作，是否完成出库？")) {
													document.location.href = "exportSeed.do?flag=ChuKuWanCheng&ff=daichuli";
												}

											} else {
												document.location.href = "exportSeed.do?flag=ChuKuWanCheng&ff=daichuli";
											}
										},
										error : function() {
											document.location.href = "/XGProject/cangchu/page/login.jsp";
										}
									});
						});
	});
</script>

</html>