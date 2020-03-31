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
	int x = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("出库完成")){
		x++;
	}
		}
%>
<body>

	<!--内容起始处-->
	<div id="mainFrame" class="main" style="padding-bottom:20px;">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">首页</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">待处理订单</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">查看详细</a>
			</ul>
		</div>

		<div class="chuku_context">
			<div class="queren">
				<strong class="yunshu_xinxi">运 输 信 息</strong>
				<%
					if(x!=0){
				%>
				<a style="cursor:pointer;" target="_blank" id="chukuwancheng"><i>出库完成</i>
				</a>
				<%
					}
				%>
			</div>
			<!--form开始-->
			<form>
				<!--table开始-->
				<table class="tab_head">
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
							${es.export.exportWagonNumber=="null"?"无":es.export.exportWagonNumber
							}</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>${es.export.exportDriverName=="null"?"无":es.export.exportDriverName
							}</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">司机电话：</td>
						<td>${es.export.exportDriverTel=="null"?"无":es.export.exportDriverTel
							}</td>
						<td align="right" width="80px">提货单号：</td>
						<td>${es.export.exportClientNumber }</td>
						<td align="right">发起时间：</td>
						<td>${es.export.exportReateTime }</td>
						<td align="right" width="100px">订单有效期：</td>
						<td>${es.export.exportDefinedTwo }天</td>
					</tr>
					<tr>
						<td align="right">是否超发：</td>
						<td>${es.export.exportDefinedOne }</td>
						<td align="right" width="100px">是否配送：</td>
						<td>${es.export.exportRemark==null?"无":es.export.exportRemark
							}</td>
						<td align="right" width="100px">联系电话：</td>
						<td>${es.eseedDefinedOne==null?"无":es.eseedDefinedOne }</td>
						<td align="right" width="100px">备注：</td>
						<td>${es.eseedRemark }</td>
					</tr>
				</table>

				<!--tabhead结束-->

				<!--货物信息开始-->

				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br /> <input type="hidden"
						value="${es.eseedId }" id="kzid" />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">${es.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${es.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">${es.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">${es.goods.goodsQuality.goodsQualityName }</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${es.goods.goodsProperty.goodsPropertyName }</td>
							<td align="right">货物产地：</td>
							<td>${es.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">应出重量：</td>
							<td>${es.eseedShouldWeight }吨</td>
							<td align="right">实出重量：</td>
							<td>${es.eseedRealityWeight==null?"0":es.eseedRealityWeight
								}吨</td>
						</tr>
						<tr style="height:30px">
							<td align="right">实出件数：</td>
							<td>${es.eseedRealityNumber==null?"0":es.eseedRealityNumber
								}${es.goods.goodsUnit.goodsUnitName }</td>
							<td align="right">应收费用：</td>
							<td>${es.eseedShouldCost==null?"0":es.eseedShouldCost }元</td>
							<td align="right">实收费用：</td>
							<td>${es.eseedRealityCost==null?"0":es.eseedRealityCost }元</td>
							<td align="right">订单状态：</td>
							<td>${es.eseedOrderStatus }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->

				<div id="tab_context" class="tab_context">
					<strong>分 配 信 息</strong> <br />
					<c:forEach items="${zlist }" var="z">
						<table class="tab_bottom" style="padding-bottom:10px;">

							<tr>
								<td align="right" width="80px">调度员：</td>
								<td width="150px">${z.interiorUserByEoperateDispatcher.iuserName
									}</td>
								<td align="right" width="80px">分配时间：</td>
								<td width="150px">${z.eoperateDispatcherTime }</td>
								<td align="right" width="80px">过磅/理算：</td>
								<td width="150px" id="guobang_lisuan">${z.eoperatePonderationTrue
									}</td>
								<td align="right" width="80px">库位：</td>
								<td width="150px">${z.tarehouse.tarehouseName }</td>
							</tr>
							<tr>
								<td align="right" width="80px">分配重量：</td>
								<td>${z.eoperateDefinedTwo==null?"无":z.eoperateDefinedTwo}</td>
								<td align="right" width="80px">批次号：</td>
								<td colspan="5">${z.EOperatepici==null?"无":z.EOperatepici}</td>
							</tr>
						</table>
					</c:forEach>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>操 作 信 息</strong> <br />
					<c:forEach items="${zlist }" var="z">
						<table class="tab_bottom">
							<tr style="height:30px">
								<td align="right" width="110px">执行人：</td>
								<td width="150px">${z.interiorUserByEoperateStoreman.iuserName
									}</td>
								<td align="right" width="100px">操作开始时间：</td>
								<td width="150px">${z.eoperateScreateTime==null?"无":z.eoperateScreateTime
									}</td>
								<td align="right" width="100px">操作结束时间：</td>
								<td width="150px">${z.eoperateSfinishTime==null?"无":z.eoperateSfinishTime
									}</td>
								<td align="right" width="110px">操作重量：</td>
								<td width="150px">${z.eoperateRealityWeight==null?"0":z.eoperateRealityWeight
									}吨</td>
							</tr>
							<tr style="height:30px">
								<td align="right" width="110px">操作件数：</td>
								<td width="150px">${z.eoperateRealityNumber==null?"0":z.eoperateRealityNumber
									}${z.exportSeed.goods.goodsUnit.goodsUnitName }</td>
								<td align="right" width="80px">司磅员：</td>
								<td width="150px">${z.interiorUserByEoperatePonderationMan==null?"无":z.interiorUserByEoperatePonderationMan.iuserName
									}</td>
								<td align="right" width="80px">过磅重量：</td>
								<td width="150px">${z.eoperatePonderationTrue=="理算"?"0":z.eoperateRealityWeight==null?"0":z.eoperateRealityWeight
									}吨</td>
								<td align="right" width="110px">过磅时间：</td>
								<td width="150px">${z.eoperatePonderationTime==null?"无":z.eoperatePonderationTime
									}</td>
							</tr>
							<tr style="height:30px">
								<td align="right" width="110px">二次作业重量：</td>
								<td width="150px">${z.eoperateDefinedThree==null?"0":z.eoperateDefinedThree
									}吨</td>
								<td align="right" width="80px">天车工：</td>
								<td width="150px">${z.eoperateCraneman==null?"无":z.eoperateCraneman
									}</td>
								<td align="right" width="80px">装卸工：</td>
								<td width="150px">${z.eoperateStevedore==null?"无":z.eoperateStevedore
									}</td>
								<td align="right">车号：</td>
								<td>${z.eoperateTruckNum==null?"无":z.eoperateTruckNum }</td>
							</tr>
							<tr style="height:30px">
								<td align="right">操作状态：</td>
								<td>${z.eoperateDefinedOne }</td>
								<td align="right">备注：</td>
								<td colspan="3">${z.eoperateRemark==null?"无":z.eoperateRemark
									}</td>
							</tr>
						</table>
					</c:forEach>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>审 核 信 息</strong> <br />
					<c:forEach items="${zlist }" var="z">
						<table class="tab_bottom" style="padding-bottom:10px;">
							<tr style="height:30px">
								<td align="right" width="110px">审核人：</td>
								<td width="150px">${z.interiorUserByEoperateAuditing==null?"无":z.interiorUserByEoperateAuditing.iuserName
									}</td>
								<td align="right" width="100px">审核时间：</td>
								<td width="150px">${z.eoperateAuditingTime==null?"无":z.eoperateAuditingTime
									}</td>
								<td align="right" width="100px">审核次数：</td>
								<td width="150px">${z.eoperateAuditingTrue==null?"无":z.eoperateAuditingTrue
									}</td>
							</tr>
						</table>
					</c:forEach>
				</div>
				<div id="tab_context" class="tab_context">
					<strong>收 费 信 息</strong> <br />
					<c:forEach items="${zlist }" var="z">
						<table class="tab_bottom">
							<tr style="height:30px">
								<td align="right" width="110px">收费人：</td>
								<td width="150px">${z.interiorUserByEoperateCollectMan==null?"无":z.interiorUserByEoperateCollectMan.iuserName
									}</td>
								<td align="right" width="100px">收费时间：</td>
								<td width="150px">${z.eoperateCollectTime==null?"无":z.eoperateCollectTime
									}</td>
								<td align="right" width="100px">应收费用：</td>
								<td width="150px">${z.eoperateShouldCost==null?"0":z.eoperateShouldCost
									}元</td>
								<td align="right" width="110px">实收费用：</td>
								<td width="150px">${z.eoperateRealityCost==null?"0":z.eoperateRealityCost
									}元</td>
							</tr>
							<tr style="height:30px">
								<td align="right" width="130px">二次作业应收费用：</td>
								<td width="150px">${z.eoperateDefinedFour==null?"0":z.eoperateDefinedFour
									}元</td>
								<td align="right" width="80px">结算方式：</td>
								<td width="150px">${z.eoperateClientAccounts==null?"无":z.eoperateClientAccounts
									}</td>
								<td align="right" width="80px">支付方式：</td>
								<td width="150px">${z.eoperatePaymentFashion==null?"无":z.eoperatePaymentFashion.pfashionName
									}</td>
								<td align="right" width="110px">票据类型：</td>
								<td width="150px">${z.eoperatePaymentFashion==null?"无":z.eoperatePaymentFashion.pfashionReceipt
									}</td>
							</tr>
						</table>
					</c:forEach>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
	<!--当点击提交入库订单的时候触发-->
	<!--在向服务器提交数据的时候要讲隐藏域进行设置name，并且重新设置与form相同的name-->

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