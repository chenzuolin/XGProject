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
<meta name="author" content="">
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
				<a style="cursor:pointer;">仓储业务</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">订单审核</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">开始操作</a>
			</ul>
		</div>

		<div class="chuku_context">
			<strong class="yunshu_xinxi">运 输 信 息</strong>

			<!--form开始-->
			<form name="exportOperateForm" method="post" id="shenheOk"
				action="${pageContext.request.contextPath}/exportOperate.do?flag=ShouFei">
				<!-- 保存操作订单id -->
				<input type="hidden" id="czId" name="eoperateId" value="${caozuoid}" />
				<!-- 保存子订单id -->
				<input type="hidden" id="ziId" value="${ziId}" />
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">客户：</td>
						<td width="150px">${exportOperate.exportSeed.export.client.clientAbbreviation
							}</td>
						<td align="right" width="80px">运输方式：</td>
						<td align="left" width="150px">
							${exportOperate.exportSeed.export.exportCarryType}</td>
						<td align="right" width="80px">车号：</td>
						<td class="chehao" width="150px">
							<!--
	                                        	作者：2586190195@qq.com
	                                        	时间：2017-06-06
	                                        	描述：判断选择的运输方式，显示不同的样式
	                                        --> <!--当选择的运输方式是火运的时候显示-->
							${exportOperate.exportSeed.export.exportWagonNumber}</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>${exportOperate.exportSeed.export.exportDriverName}</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">司机电话：</td>
						<td>${exportOperate.exportSeed.export.exportDriverTel}</td>
						<td align="right" width="80px">提货单号：</td>
						<td>${exportOperate.exportSeed.export.exportClientNumber}</td>
						<td align="right" width="80px">备注：</td>
						<td colspan="3">${exportOperate.exportSeed.export.exportRemark}</td>
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
								${exportOperate.exportSeed.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">
								${exportOperate.exportSeed.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">
								${exportOperate.exportSeed.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">
								${exportOperate.exportSeed.goods.goodsQuality.goodsQualityName }
							</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>
								${exportOperate.exportSeed.goods.goodsProperty.goodsPropertyName
								}</td>
							<td align="right">货物产地：</td>
							<td>
								${exportOperate.exportSeed.goods.goodsYieldly.goodsYieldlyName }
							</td>
							<td align="right">应出重量：</td>
							<td>${exportOperate.exportSeed.eseedShouldWeight }吨</td>
							<td align="right">备注：</td>
							<td>${exportOperate.exportSeed.goods.goodsRemark }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->
				<div id="tab_context" class="tab_context">
					<strong>分 配 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">调度员：</td>
							<td width="150px">
								${exportOperate.interiorUserByEoperateDispatcher.iuserLoginName
								}</td>
							<td align="right" width="80px">分配时间：</td>
							<td width="150px">${exportOperate.eoperateDispatcherTime }</td>
							<td align="right" width="80px">过磅/理算：</td>
							<td width="150px" id="guobang_lisuan">${exportOperate.eoperatePonderationTrue
								}</td>
							<td align="right" width="80px">分配重量：</td>
							<td width="150px">${exportOperate.eoperateDefinedTwo}吨</td>
						</tr>
						<tr>
							<td align="right">库位：</td>
							<td>${exportOperate.tarehouse.tarehouseName}</td>
							<td align="right" width="80px">批次号：</td>
							<td colspan="3">${exportOperate.EOperatepici==null?"无":exportOperate.EOperatepici}</td>
						</tr>
						<tr>
							<td align="right">备注：</td>
							<td colspan="7" style="cursor: pointer;"
								title="${exportOperate.eoperateRemark}">
								${exportOperate.eoperateRemark}</td>
						</tr>
					</table>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>操 作 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="110px">保管员：</td>
							<td width="150px">
								${exportOperate.interiorUserByEoperateStoreman.iuserName}</td>
							<td align="right" width="100px">开始操作时间：</td>
							<td width="150px">${exportOperate.eoperateScreateTime}</td>
							<td align="right" width="100px">结束操作时间：</td>
							<td width="150px">${exportOperate.eoperateSfinishTime}</td>
							<td align="right" width="110px">操作重量：</td>
							<td width="150px">${exportOperate.eoperateRealityWeight}吨</td>

						</tr>
						<tr>

							<td align="right" width="80px">操作件数：</td>
							<td width="150px">${exportOperate.eoperateRealityNumber}件</td>
							<td align="right" width="80px">司磅员：</td>
							<td width="150px">${exportOperate.interiorUserByEoperatePonderationMan==null?"无":exportOperate.interiorUserByEoperatePonderationMan.iuserName
								}</td>
							<td align="right" width="80px">过磅重量：</td>
							<td width="150px">${exportOperate.eoperatePonderationTrue=="理算"?"0":exportOperate.eoperateRealityWeight==null?"0":exportOperate.eoperateRealityWeight
								}吨</td>
							<td align="right" width="110px">过磅时间：</td>
							<td width="150px">${exportOperate.eoperatePonderationTime==null?"无":exportOperate.eoperatePonderationTime
								}</td>

						</tr>
						<tr>
							<td align="right" width="110px">二次作业重量：</td>
							<td width="150px">${exportOperate.eoperateDefinedThree==null?"0":exportOperate.eoperateDefinedThree
								}吨</td>
							<td align="right" width="80px">天车工：</td>
							<td width="150px">${exportOperate.eoperateCraneman}</td>
							<td align="right" width="80px">装卸工：</td>
							<td width="150px">${exportOperate.eoperateStevedore}</td>
							<td align="right">车号：</td>
							<td>${exportOperate.eoperateTruckNum==null?"无":exportOperate.eoperateTruckNum
								}</td>
						</tr>
						<tr style="height:30px">
							<td align="right">操作状态：</td>
							<c:choose>
								<c:when test="${exportOperate.eoperateDefinedOne=='订单作废' }">
									<td style="color:red;">${exportOperate.eoperateDefinedOne
										}</td>
								</c:when>
								<c:otherwise>
									<td>${exportOperate.eoperateDefinedOne }</td>
								</c:otherwise>
							</c:choose>

							<td align="right">备注：</td>
							<td colspan="5">${exportOperate.eoperateRemark==null?"无":exportOperate.eoperateRemark
								}</td>
						</tr>
					</table>
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
					<a id="butongguo"><i
						style="background: url(cangchu/img/zengjiahuowu.png);cursor: pointer;">审核不通过</i>
					</a> <a id="tongguo" style="cursor: pointer;">审核通过</a>
				</div>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
	<!--当点击提交入库订单的时候触发-->
	<!--在向服务器提交数据的时候要讲隐藏域进行设置name，并且重新设置与form相同的name-->


</body>
<script>
	$(function() {

		//当点击审核通过
		$("#tongguo")
				.click(
						function() {
							var $cId = $("#czId").val();
							var $beizhu = $("#beizhu").val();
							var $ziId = $("#ziId").val();

							if (confirm("确定审核通过吗？")) {
								document.location.href = "${pageContext.request.contextPath}/exportOperate.do?flag=ShengHeTongGuo&czId="
										+ $cId
										+ "&beizhu="
										+ $beizhu
										+ "&ziId=" + $ziId;
							}

						});

		//点击审核不通过
		$("#butongguo")
				.click(
						function() {
							var $cId = $("#czId").val();
							var $beizhu = $("#beizhu").val();
							var $ziId = $("#ziId").val();
							if (confirm("确定审核不通过吗？")) {
								document.location.href = "${pageContext.request.contextPath}/exportOperate.do?flag=ShengHeWeiTongGuo&czId="
										+ $cId
										+ "&beizhu="
										+ $beizhu
										+ "&ziId=" + $ziId;
							}
						});
	});
</script>

</html>