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
<script type="text/javascript" src="js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="js/faqichuku.js"></script>
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="css/faqichuku.css" />
<link rel="stylesheet" href="css/publick.css">
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

.panel-foot {
	width: 60px;
	height: 35px;
	text-align: center;
	line-height: 35px;
	font-weight: bold;
	background-color: #D9534F;
	border-radius: 5px;
	cursor: pointer;
	margin-top: 10px;
}

.panel-foot:hover {
	background-color: #B41F1F;
}

.panel-foot a {
	color: #FFFFFF;
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
	|| request.getSession().getAttribute("iulist") == null || request.getSession().getAttribute("power")==null) {
%>
<script type="text/javascript">
	$(function() {
		$("html").html("");
	});
</script>
<%
	}
%>
<body>
	<%
		int x = 0;
		int zuofei = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("过户订单查询")){
		x++;
			}
			if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("过户订单作废")){
		zuofei++;
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

<body>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor: pointer;">数据中心</a>
				<span>/</span>&nbsp;
				<a style="cursor: pointer;" href="javascript:window.history.go(-1)">订单查询</a>
				<span>/</span>&nbsp;
				<a style="cursor: pointer;">过户明细</a>
			</ul>
		</div>
		<div class="chuku_context">
			<strong class="yunshu_xinxi">订 单 信 息</strong>
			<!--form开始-->
			<form>
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">订单编	号：</td>
						<td width="150px">${ss.shiftStock.sstockId }</td>
						<td align="right" width="80px">转出单位：</td>
						<td align="left" width="150px">${ss.shiftStock.clientBySstockClientId.clientAbbreviation
							}</td>
						<td align="right" width="80px">转入单位：</td>
						<td class="chehao" width="150px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							${ss.shiftStock.clientBySstockShiftToFirm.clientAbbreviation }</td>
						<td align="right" width="80px">客户单号：</td>
						<td width="150px">${ss.shiftStock.sstockClientNumber }</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">发起日期：</td>
						<td>${ss.shiftStock.sstockReateTime }</td>
						<td align="right" width="80px">订单状态：</td>
						<td>${ss.ssseedOrderStatus }</td>
						<td align="right">备注：</td>
						<td colspan="3">${ss.ssseedRemark }</td>
					</tr>
				</table>
				<!--tabhead结束-->
				<!--货物信息开始-->
				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">${ss.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${ss.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">${ss.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">${ss.goods.goodsQuality.goodsQualityName }</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${ss.goods.goodsProperty.goodsPropertyName }</td>
							<td align="right">货物产地：</td>
							<td>${ss.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">过户重量：</td>
							<td>${ss.ssseedWeight }吨</td>
							<td align="right">转库类型：</td>
							<td>${ss.shiftStock.sstockDefinedOne==null?"无":ss.shiftStock.sstockDefinedOne }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->
				<div id="tab_context" class="tab_context">
					<strong>审 核 信 息</strong> <br />
					<table class="tab_bottom" style="padding-bottom: 10px;">
						<tr>
							<td align="right" width="80px">审核人：</td>
							<td width="150px">${ss.interiorUserBySsseedAuditing==null?"无":ss.interiorUserBySsseedAuditing.iuserName
								}</td>
							<td align="right" width="80px">审核时间：</td>
							<td width="150px">${ss.ssseedAuditingTime==null?"无":ss.ssseedAuditingTime
								}</td>
							<td align="right" width="80px">审核结果：</td>
							<td width="150px" id="guobang_lisuan">${ss.ssseedAuditingTrue==null?"无":ss.ssseedAuditingTrue
								}</td>
							<td align="right" width="80px"></td>
							<td width="150px" id="guobang_lisuan"></td>
						</tr>
					</table>
				</div>
				<!--收费信息-->
				<div id="tab_context" class="tab_context">
					<strong>收 费 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">收费人：</td>
							<td width="150px">${ss.interiorUserBySsseedCollectMan==null?"无":ss.interiorUserBySsseedCollectMan.iuserName
								}</td>
							<td align="right" width="80px">收费时间：</td>
							<td width="150px">${ss.ssseedCollectTime==null?"无":ss.ssseedCollectTime
								}</td>
							<td align="right" width="80px">应收费用：</td>
							<td width="150px">${ss.ssseedShouldCost==null?"无":ss.ssseedShouldCost
								}元</td>
							<td align="right" width="80px">实收费用：</td>
							<td width="150px">${ss.ssseedRealityCost==null?"无":ss.ssseedRealityCost
								}元</td>
						</tr>
						<tr>
							<td align="right">支付方式：</td>
							<td>${ss.paymentFashion==null?"无":ss.paymentFashion.pfashionName
								}</td>
							<td align="right">票据类型：</td>
							<td>${ss.paymentFashion==null?"无":ss.paymentFashion.pfashionReceipt
								}</td>
							<td align="right">结算方式：</td>
							<td>${ss.ssseedClientAccounts==null?"无":ss.ssseedClientAccounts
								}</td>
							<td align="right"></td>
							<td></td>
						</tr>
					</table>
				</div>

			</form>
			<%
				if(zuofei!=0){
			%>
			<input type="hidden" value="${ss.ssseedOrderStatus }" id="zhuangtai" />
			<div class="panel-foot" style="float: right;" id="zuofei">
				<a type="button" class="btn btn-danger">作废</a> <input type="hidden"
					value="${ss.ssseedId }" />
			</div>
			<%
				}
			%>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
	<script>
		//当点击作废的时候触发
		$(function() {
			$("#zuofei")
					.click(
							function() {
								if ($("#zhuangtai").val() == "正在审核") {
									alert("请选择审核通过或不通过，此订单不可以作废！");
									return false;
								}
								var id = $(this).children("input").val();
								if (confirm("你确定要作废此订单吗？")) {
									document.location.href = "shiftStockSeed.do?flag=deleteShiftStockSeed&ssseedId="
											+ encodeURI(encodeURI(id));
								}
							});
		});
	</script>
</body>

</html>