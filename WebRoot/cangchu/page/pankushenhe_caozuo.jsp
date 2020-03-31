﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.Checks"%>
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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--让IE浏览器用最高级内核渲染页面 还有用 Chrome 框架的页面用webkit内核-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--为移动设备添加 viewport-->
<title>仓储管理系统</title>
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

#tongguo {
	background: url(cangchu/img/zengjiahuowu.png);
}

#tongguo:hover {
	background: url(cangchu/img/zengjiahuowu2.png);
}

#butongguo {
	background: url(cangchu/img/zengjiahuowu.png);
}

#butongguo:hover {
	background: url(cangchu/img/zengjiahuowu2.png);
}
table {
	-moz-box-shadow: 5px 5px 5px #000;
	-webkit-box-shadow: 5px 5px 5px #000;
	box-shadow: 2px 2px 10px #000;
	border-top:1px solid #888888;
	border-left:1px solid #888888;
}
</style>
</head>

<body>

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">盘库审核</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">开始操作</a>
			</ul>
		</div>

		<div class="chuku_context">
			<strong class="yunshu_xinxi">订 单 信 息</strong>

			<!--form开始-->
			<form method="post" name="checksForm" id="shenhepankuForm">
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">编号：</td>
						<td width="150px">${clist.checkId } <input type="hidden"
							value="${clist.checkId }" name="checkId" />
						</td>
						<td align="right" width="80px">发起人：</td>
						<td align="left" width="150px">${clist.interiorUserByCheckFounderMember.iuserName
							}</td>
						<td align="right" width="80px">发起时间：</td>

						<td class="chehao" width="200px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							${clist.checkTime }</td>
						<td align="right" width="80px">库位：</td>
						<td>${clist.tarehouse.tarehouseName }</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">订单状态：</td>
						<td>${clist.checkAuditingTrue }</td>

						<td align="right" width="80px">备注：</td>
						<td colspan="5">${clist.checkRemark }</td>
					</tr>
				</table>

				<!--tabhead结束-->

				<!--货物信息开始-->

				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">${clist.tarehouseGoods.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${clist.tarehouseGoods.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">${clist.tarehouseGoods.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">${clist.tarehouseGoods.goods.goodsQuality.goodsQualityName
								}</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${clist.tarehouseGoods.goods.goodsProperty.goodsPropertyName
								}</td>
							<td align="right">货物产地：</td>
							<td>${clist.tarehouseGoods.goods.goodsYieldly.goodsYieldlyName
								}</td>
							<td align="right">库存件数：</td>
							<td>${clist.checkTarehouseNumber
								}${clist.tarehouseGoods.goods.goodsUnit.goodsUnitName }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->

				<div id="tab_context" class="tab_context">
					<strong>操 作 信 息</strong> <br />
					<table class="tab_bottom" style="padding-bottom: 10px;">
						<tr>
							<td align="right" width="80px">盘库人：</td>
							<td width="150px">${clist.interiorUserByCheckHuman.iuserName
								}</td>
							<td align="right" width="80px">盘库结果：</td>
							<td width="150px">${clist.checkResultNumber
								}${clist.tarehouseGoods.goods.goodsUnit.goodsUnitName }</td>
							<td align="right">盘库时间：</td>
							<td width="200px">${clist.checkDefinedOne }</td>
							<td width="200px"></td>
							<td></td>
						</tr>
					</table>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>填 入 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td width="150px">备注： <textarea
									style="width: 100%; resize: none;" name="checkRemark"></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="queren">
					<a style="cursor:pointer;" id="butongguo"><i>审核不通过</i> </a> <a
						style="cursor:pointer;" id="tongguo">审核通过</a>
				</div>
				<input type="hidden"
					value="<%=request.getSession().getAttribute("iulistId")%>"
					name="interiorUserByCheckAuditing" />
			</form>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
</body>
<script>
	$(function() {
		//当点击审核不通过的时候
		$("#butongguo")
				.click(
						function() {
							if (confirm("确定不通过吗？")) {
								$("#shenhepankuForm")
										.attr("action",
												"${pageContext.request.contextPath}/checks.do?flag=NotTongGuoChecks");
								$("#shenhepankuForm").submit();
							}
						});
		//当点击审核通过的时候
		$("#tongguo")
				.click(
						function() {
							if (confirm("确定通过吗？")) {
								$("#shenhepankuForm")
										.attr("action",
												"${pageContext.request.contextPath}/checks.do?flag=TongGuoChecks");
								$("#shenhepankuForm").submit();
							}
						});
	});
</script>

</html>

<%
	if (request.getSession().getAttribute("loginName") == null
	|| request.getSession().getAttribute("iulist") == null
	|| request.getSession().getAttribute("power") == null) {
%>
<script type="text/javascript">
	$(function() {
		$("html").html("");
	});
</script>
<%
	}
%>
<%
	int x = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("盘库审核")){
		x++;
	}
		}
		if(x==0){
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