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
<meta name="format-detection" content="telephone=no" />
<!--禁止数字识自动别为电话号码-->
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--禁止百度转码-->
<link rel="shortcut icon" type="image/ico" href="/favicon.ico" />
<!--添加 favicon icon -->

<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/faqichuku.js"></script>
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

.queren {
	height: 100px;
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

<body>
	<!--内容起始处-->
	<div id="mainFrame" class="main">
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
			<strong class="yunshu_xinxi">运 输 信 息</strong>
			<!--form开始-->
			<form name="inputOperateForm" method="post" id="quedingOk"
				action="${pageContext.request.contextPath}/inputOperate.do?flag=updataSeedStatus">
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">客户：</td>
						<td width="150px">${inputSeed.input.client.clientLoginName }</td>
						<td align="right" width="80px">运输方式：</td>
						<td align="left" width="150px">
							${inputSeed.input.inputCarryType} <input type="hidden"
							name="inputSeed" value="${inputSeed.iseedId}"> <input
							type="hidden" name="ioperateId"
							value="${inputOperate.ioperateId}">
						</td>
						<td align="right" width="80px">车号：</td>
						<td class="chehao" width="150px">
							<!--
	                                        	作者：2586190195@qq.com
	                                        	时间：2017-06-06
	                                        	描述：判断选择的运输方式，显示不同的样式
	                                        --> <!--当选择的运输方式是火运的时候显示-->
							${inputSeed.input.inputPlateNumber==null?"无":inputSeed.input.inputPlateNumber}
						</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>
							${inputSeed.input.inputDriverName==null?"无":inputSeed.input.inputDriverName}
						</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">司机电话：</td>
						<td>
							${inputSeed.input.inputDriverTel==null?"无":inputSeed.input.inputDriverTel}
						</td>
						<td align="right" width="80px">提货单号：</td>
						<td>${inputSeed.input.inputClientNumber}</td>
						<td align="right" width="100px">发起时间：</td>
						<td>${inputSeed.input.inputCreateTime }</td>
						<td align="right" width="100px">订单有效期：</td>
						<td>${inputSeed.input.inputDefinedOne }天</td>
					</tr>
					<tr>
						<td align="right" width="80px">备注：</td>
						<td colspan="7">${inputSeed.input.inputRemark}</td>
					</tr>
				</table>
				<!--tabhead结束-->
				<!--货物信息开始-->
				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">
								${inputSeed.goods.goodsCategory.goodsCategoryName }</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${inputSeed.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">
								${inputSeed.goods.goodsStandard.goodsStandardName }</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">
								${inputSeed.goods.goodsQuality.goodsQualityName }</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${inputSeed.goods.goodsProperty.goodsPropertyName }</td>
							<td align="right">货物产地：</td>
							<td>${inputSeed.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">应收重量：</td>
							<td>${inputSeed.iseedShouldWeight }吨</td>
							<td align="right">实收重量：</td>
							<td>${inputSeed.iseedRealityWeight==null?"0":inputSeed.iseedRealityWeight
								}吨</td>
						</tr>
						<tr>
							<td align="right">实收件数：</td>
							<td>${inputSeed.iseedRealityNumber==null?"0":inputSeed.iseedRealityNumber
								}${inputSeed.goods.goodsUnit.goodsUnitName }</td>
							<td align="right">应收费用：</td>
							<td>${inputSeed.iseedShouldCost==null?"0":inputSeed.iseedShouldCost }元</td>
							<td align="right">实收费用：</td>
							<td>${inputSeed.iseedRealityCost==null?"0":inputSeed.iseedRealityCost }元</td>
							<td align="right">订单状态：</td>
							<td>${inputSeed.iseedOrderStatus }</td>
						</tr>
						<tr>
							<td align="right">车皮号：</td>
							<td>${inputSeed.iseedDefinedOne }</td>
							<td align="right">备注：</td>
							<td colspan="5">${inputSeed.iseedRemark }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->
				<div id="tab_context" class="tab_context">
					<strong>分 配 信 息</strong> <br />
					<c:forEach items="${listInputOperate }" var="listOperate">
						<table class="tab_bottom">
							<tr>
								<td align="right" width="80px">调度员：</td>
								<td width="150px">
									${listOperate.interiorUserByIoperateDispatcherId.iuserName }</td>
								<td align="right" width="80px">分配时间：</td>
								<td width="150px">${listOperate.ioperateDispatcherTime }</td>
								<td align="right" width="80px">过磅/理算：</td>
								<td width="150px" id="guobang_lisuan">${listOperate.ioperatePonderationTrue
									}</td>
							</tr>
							<tr>
								<td align="right">库位：</td>
								<td>${listOperate.tarehouse.tarehouseName}</td>
								<td align="right">备注：</td>
								<td>${listOperate.ioperateRemark}</td>
								<td colspan="4"></td>
							</tr>
						</table>
					</c:forEach>

				</div>

				<div id="tab_context" class="tab_context">
					<strong>操 作 信 息</strong> <br />
					<c:forEach items="${listInputOperate }" var="listOperate">
						<table class="tab_bottom">
							<tr>
								<td align="right" width="110px">执行人：</td>
								<td width="150px">${listOperate.interiorUserByIoperateStoremanId.iuserName
									}</td>
								<td align="right" width="100px">操作开始时间：</td>
								<td width="150px">${listOperate.ioperateScreateTime==null?"无":listOperate.ioperateScreateTime
									}</td>
								<td align="right" width="100px">操作结束时间：</td>
								<td width="150px">${listOperate.ioperateSfinishTime==null?"无":listOperate.ioperateSfinishTime
									}</td>
								<td align="right" width="110px">操作重量：</td>
								<td width="150px">${listOperate.ioperateRealityWeight==null?"0":listOperate.ioperateRealityWeight
									}吨</td>
							</tr>
							<tr>
								<td align="right" width="80px">操作件数：</td>
								<td width="150px">${listOperate.ioperateRealityNumber}</td>
								<td align="right" width="80px">司磅员：</td>
								<td width="150px">${listOperate.interiorUserByIoperatePonderationManId==null?"无":listOperate.interiorUserByIoperatePonderationManId.iuserName
									}</td>
								<td align="right" width="80px">过磅重量：</td>
								<td width="150px">${listOperate.ioperatePonderationTrue=="理算"?"0":listOperate.ioperateRealityWeight==null?"0":listOperate.ioperateRealityWeight
									}吨</td>
								<td align="right" width="110px">过磅时间：</td>
								<td width="150px">${listOperate.ioperatePonderationTime==null?"无":listOperate.ioperatePonderationTime
									}</td>
							</tr>
							<tr>
								<td align="right" width="80px">天车工：</td>
								<td width="150px">${listOperate.ioperateCraneman}</td>
								<td align="right" width="80px">装卸工：</td>
								<td width="150px">${listOperate.ioperateStevedore==null?"无":listOperate.ioperateStevedore
									}</td>
								<td align="right">车号：</td>
								<td>${listOperate.ioperateTruckNum==null?"无":listOperate.ioperateTruckNum }</td>
							</tr>
						</table>
					</c:forEach>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>审 核 信 息</strong> <br />
					<c:forEach items="${listInputOperate }" var="listOperate">
						<table class="tab_bottom">
							<tr style="height:30px">
								<td align="right" width="110px">审核人：</td>
								<td width="150px">${listOperate.interiorUserByIoperateAuditingId==null?"无":listOperate.interiorUserByIoperateAuditingId.iuserName
									}</td>
								<td align="right" width="100px">审核时间：</td>
								<td width="150px">${listOperate.ioperateAuditingTime==null?"无":listOperate.ioperateAuditingTime
									}</td>
								<td align="right" width="100px">审核次数：</td>
								<td width="150px">${listOperate.ioperateDefinedOne==null?"无":listOperate.ioperateDefinedOne
									}</td>
							</tr>
						</table>
					</c:forEach>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>收 费 信 息</strong> <br />
					<c:forEach items="${listInputOperate }" var="listOperate">
						<table class="tab_bottom">
							<tr>
								<td align="right" width="110px">收费人：</td>
								<td width="150px">${listOperate.interiorUserByIoperateCollectManId==null?"无":listOperate.interiorUserByIoperateCollectManId.iuserName
									}</td>
								<td align="right" width="80px">收费时间：</td>
								<td width="150px">${listOperate.ioperateCollectTime }</td>
								<td align="right" width="80px">应收费用：</td>
								<td width="150px">${listOperate.ioperateShouldCost }</td>
								<td align="right" width="80px">实收费用：</td>
								<td width="150px">${listOperate.ioperateRealityCost }</td>
							</tr>
							<tr>
								<td align="right" width="80px">结算方式：</td>
								<td width="150px">${listOperate.ioperateClientAccounts }</td>
								<td align="right" width="80px">票据类型：</td>
								<td width="150px">
									${listOperate.ioperatePaymentFashion.pfashionReceipt }</td>
								<td align="right" width="80px">支付方式：</td>
								<td width="150px">${listOperate.ioperatePaymentFashion==null?"无":listOperate.ioperatePaymentFashion.pfashionName
									}</td>
							</tr>
						</table>
					</c:forEach>
				</div>
				<div id="tab_context" class="tab_context">
					<strong>填 入 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td width="150px">备注： <textarea id="beizhu"
									style="width: 100%; resize: none;" name="ioperateRemark"></textarea>
							</td>
						</tr>
					</table>
				</div>
				<div class="queren">
					<a style="cursor: pointer;" id="tongguo">入库完成</a>
				</div>
			</form>
		</div>

		<!--出库内容结束-->
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var a = 0;
		$("#tongguo")
				.click(
						function() {
							var status = $(".caozuoStatus");
							for ( var i = 0; i < status.length; i++) {
								//获取文本后用正则表达式去掉空格后判断			
								if (status[i].innerHTML.replace(
										/(^\s*)|(\s*$)/g, "") == "入库完成"
										|| status[i].innerHTML.replace(
												/(^\s*)|(\s*$)/g, "") == "未收费") {
									a++;
								}
								;
							}
							//如果多个订单状态都为已收费或者是未收费，说明都为完成状态，就提交
							if (a == status.length) {
								$("#quedingOk").submit();
							} else {
								alert("订单还未全部完成奥！或者有多余订单，请作废后重试！");
							}
						});
	});
</script>
</html>



