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

<link rel="stylesheet" href="cangchu/css/selectize.default.css">


<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/faqichuku.js"></script>
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<script type="text/javascript" src="cangchu/js/selectize.js"></script>
<script type="text/javascript" src="cangchu/js/bootstrap.min.js"></script>
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

td {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
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

td {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}

.queren {
	height: 100px;
}

.queren a {
	background: url(cangchu/img/zengjiahuowu.png);
}

.queren a:hover {
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
<script>
	$('#zhuanchu').selectize({
		dropdownParent : "form"
	});
</script>
<body onload="load();">

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">订单收费</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">开始操作</a>
			</ul>
		</div>

		<div class="chuku_context">
			<strong class="yunshu_xinxi">运 输 信 息</strong>
			<!--form开始-->
			<form name="exportOperateForm" method="post" id="shoufeiOk"
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
								${exportOperate.interiorUserByEoperateDispatcher.iuserName }</td>
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
							<td align="right" width="80px">开始操作时间：</td>
							<td width="150px">${exportOperate.eoperateScreateTime}</td>
							<td align="right" width="80px">结束操作时间：</td>
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
					<strong>审 核 信 息</strong><br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">审核人员：</td>
							<td width="150px">
								${exportOperate.interiorUserByEoperateAuditing.iuserName }</td>
							<td align="right" width="80px">审核时间：</td>
							<td width="150px">${exportOperate.eoperateAuditingTime }</td>
							<td align="right" width="100px">审核次数：</td>
								<td width="150px">${exportOperate.eoperateAuditingTrue==null?"无":exportOperate.eoperateAuditingTrue
									}</td>
						</tr>
					</table>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>填 入 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="110px">应收费用：</td>
							<td width="150px">${exportOperate.eoperateShouldCost}</td>
							<td align="right" width="80px">实收费用：</td>
							<td width="150px"><input type="text" id="shishoufei"
								name="eoperateRealityCost" />
							</td>
							<td align="right" width="80px">结算方式：</td>
							<td width="150px">
								<!-- 起个名字，提交到form中获取，用于修改订单状态 --> <select id="jiesuan"
								onchange="panduanjiesuan();" name="eoperateClientAccounts">
									<option value="日结">日结</option>
									<option value="月结">月结</option>
							</select>
							</td>
							<td align="right" width="110px">支付方式：</td>
							<td width="150px"><select id="zhifu"
								onchange="piaojuleixing()">
							</select>
							</td>
						</tr>
						<tr>
							<td align="right">票据类型：</td>
							<td><select id="piaoju" name="eoperatePaymentFashion">

							</select>
							</td>
							<c:choose>
								<c:when test="${exportOperate.eoperateDefinedFour!=null}">
									<td align="right" width="150px">二次作业应收费用：</td>
									<td id="dd">${exportOperate.eoperateDefinedFour} 元</td>
									<td align="right">二次作业实收费用：</td>
									<td><input type="text" id="ercifei" name="ercishishou" />
									</td>
								</c:when>
							</c:choose>
							<td align="right" width="80px">备注：</td>
							<td width="150px"><input type="text" name="eoperateRemark" />
							</td>
						</tr>

					</table>
				</div>

				<div class="queren">
					<a id="tanxiazhan"
						style="cursor:pointer; color:#ffffff; text-align:center; line-height:30px;">下站费</a>
					<a
						style="background: url(cangchu/img/zengjiahuowu.png); cursor:pointer; display: none;"
						target="_blank" id="dayin" onclick="tiaozhuanchuku()"><i>打印</i>
					</a> <a id="shoufei_tijiao">提交</a>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
	</div>
	<!--页面起始处结束-->
	<!--当点击提交入库订单的时候触发-->
	<!--在向服务器提交数据的时候要讲隐藏域进行设置name，并且重新设置与form相同的name-->


	<!-- 出库下站费模态框 -->
	<script>
		function tiaozhuanchuku() {
			$(function() {
				var id = $("#ziId").val();
				if (confirm("你确定此操作吗？")) {
					document.location.href = "${pageContext.request.contextPath}/exportSeed.do?flag=getXiangQing&ff=dayin&eseedId="
							+ encodeURI(encodeURI(id));
				}

			});
		}
		$(function() {

			$("#xiazhandanjia").blur(
					function() {
						var danjia = $(this).val();
						var weight = $("#exportweight").val();
						if (danjia != "" && danjia != null && weight != "") {
							$("#xiazhanheji").val(
									parseFloat(danjia) * parseFloat(weight));
						}
					});

			//打开模态框
			$("#tanxiazhan").click(function() {

				$(".modeal_bg").animate({
					"opacity" : 0.5
				}).css("display", "block");
				$(".modeal_bottom").animate({
					"opacity" : 1,
					"top" : "25%"
				}).css("display", "block");
			});
			//关闭模态框
			$(".close").click(function() {
				$(".modeal_bg").animate({
					"opacity" : 0
				}).css("display", "none");
				$(".modeal_bottom").animate({
					"opacity" : 0,
					"top" : "10%"
				}).css("display", "none");
			});
		});
	</script>


	<form
		action="${pageContext.request.contextPath }/xiazhanfeiAction.do?flag=SaveXiaZhan"
		name="xiazhanfeiForm" method="post" id="xzform">
		<input type="hidden"
			value="${exportOperate.exportSeed.export.exportId }"
			name="xadefinedtwo" /> <input type="hidden" value="出库"
			name="xzdefinedthree" /> <input type="hidden"
			value="${exportOperate.exportSeed.export.client.clientId}" id="clientId" /> <input type="hidden"
			value="${exportOperate.eoperateId }" name="xadefinedfive" />
		<div class="modeal_bottom" id="modeal">
			<div>
				<div>
					<div class="modeal_bottom_top">
						<h3>下 站 费 信 息</h3>
					</div>
					<div class="modeal_bottom_middle">
						<div id="xiazhan" class="tab_context" style="margin-top: 5%;">
							<div class="kehu_xuanze">
								<!-- 如果是客户发起入库，那么将此div隐藏  --><strong
									style="float:left; border: 0px; margin-left:10%;">对应客户：</strong>
								<div style="float:left;">
									<select id="zhuanchu" style="width:250px;" name="clientByXzzhuanruclient">
									</select>
								</div>
							</div>
							<table class="tab_bottom" style="width:90%; margin: 0 auto;">
								<tr>
									<%-- <td align="right" width="110px">对应客户：</td>
									<td width="150px">${exportOperate.exportSeed.export.client.clientAbbreviation
										}</td> --%>
									<td align="right" width="100px">下站费单价：</td>
									<td width="150px"><input type="text" id="xiazhandanjia"
										name="xzdanjia" /></td>
									<td align="right" width="80px">重量：</td>
									<td width="150px"><input type="text" readonly="readonly"
										value="${exportOperate.eoperateRealityWeight}"
										id="exportweight" name="xzweight" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">下站费合计：</td>
									<td width="150px"><input type="text" readonly="readonly"
										id="xiazhanheji" name="xzcount" /></td>
									<td align="right" width="80px">结算方式：</td>
									<td width="150px"><select id="xzjiesuan"
										onchange="xzpanduanjiesuan()" name="xzdefinedone">
											<option value="日结">日结</option>
											<option value="月结">月结</option>
									</select></td>
									<td align="right" width="110px">支付方式：</td>
									<td width="150px"><select id="xzzhifu" name="xzzhifu">

									</select>
									</td>

								</tr>
								<tr>
									<td align="right">票据类型：</td>
									<td><select id="xzpiaoju" name="xzpiaoju">
									</select>
									</td>
									<td align="right">实收费用：</td>
									<td><input type="text" id="shishou" name="xadefinedfour">
									</td>
									<td align="right" width="80px">备注：</td>
									<td colspan="2" width="150px"><input type="text"
										name="xzremark" /></td>
								</tr>
							</table>
						</div>
					</div>
					<div class="modeal_bottom_bottom">
						<a id="xzqueding">确定提交</a> <a class="close">关闭</a>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div class="modeal_bg"></div>

</body>

<script>
	$(function() {

		//点击下站费中提交
		$("#xzqueding").click(function() {
			if ($("#xzjiesuan").val() == "日结" && $("#shishou").val() == "") {
				alert("实收费用不能为空");
				return false;
			}
			if ($("#xiazhandanjia").val() == "") {
				alert("下站单价不能为空!");
				return false;
			}
			if (confirm("确定提交吗？")) {
				$("#xzform").submit();
			}

		});

		//当点击提交的时候
		$("#shoufei_tijiao")
				.click(
						function() {

							var feiyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
							var shishoufei = $("#shishoufei").val();
							var ercifei = $("#ercifei").val();
							var dd = $("#dd").text();

							if ($("#jiesuan").val() == "日结") {
								if (!feiyan.test(shishoufei)) {
									alert("实收费用不合法！");
									return false;
								}
							}
							//如果应收二次作业不为空，再进行判断
							if (dd != "") {
								if (feiyan.test(ercifei) == false) {
									alert("二次作业费输入不合法！");
									return false;
								}
							}

							if (confirm("如果产生下站费，请先收取下站费！确定提交吗？")) {
								$("#shoufeiOk").submit();
								//tiaozhuanchuku();
							}
						});

	});

	function zhifufangshi() {

		$(function() {
			$("#zhifu").html("");
			$
					.ajax({
						type : "post",
						url : "paymentFashion.do?flag=getGuoHuQuery&ff=zhifu",
						dataType : "json",
						success : function(obj) {
							if (obj == null || obj.length <= 0) {
								$("#zhifu").append(
										"<option value='无'>无</option>");
								return false;
							}
							if (obj[0].result == null) {
								$("#zhifu").append(
										"<option value='无'>无</option>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$("#zhifu").append(
										"<option value='"+obj[i].zhifu+"'>"
												+ obj[i].zhifu + "</option>");
							}
							piaojuleixing();
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});

		});
	}

	function piaojuleixing() {
		$(function() {
			$("#piaoju").html("");
			$
					.ajax({
						type : "post",
						url : "paymentFashion.do?flag=getGuoHuQuery&ff=piaoju",
						data : {
							"pfashionName" : $("#zhifu").val()
						},
						dataType : "json",
						success : function(obj) {
							if (obj == null || obj.length <= 0) {
								$("#piaoju").append(
										"<option value='无'>无</option>");
								return false;
							}
							if (obj[0].result == null) {
								$("#piaoju").append(
										"<option value='无'>无</option>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$("#piaoju").append(
										"<option value='"+obj[i].zhifuid+"'>"
												+ obj[i].piaoju + "</option>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});

		});
	}

	function panduanjiesuan() {
		$(function() {
			if ($("#jiesuan").val() == "月结") {
				//设置实收费用为只读
				$("#shishoufei").attr("disabled", "true");
				$("#zhifu").html("");
				$("#zhifu").append("<option value='无'>无</option>");
				$("#piaoju").html("");
				$("#piaoju").append("<option value='无'>无</option>");
			} else {
				//设置实收费用为只读
				$("#shishoufei").attr("disabled", false);
				zhifufangshi();
			}
		});
	}

	//---------下站费	

	function load() {
		zhifufangshi();
		xzzhifufangshi();
		QueryZhuanChu();
	}
	function xzzhifufangshi() {
		$(function() {
			$("#xzzhifu").html("");
			$
					.ajax({
						type : "post",
						url : "paymentFashion.do?flag=getGuoHuQuery&ff=zhifu",
						dataType : "json",
						success : function(obj) {
							if (obj == null || obj.length <= 0) {
								$("#xzzhifu").append(
										"<option value='无'>无</option>");
								return false;
							}
							if (obj[0].result == null) {
								$("#xzzhifu").append(
										"<option value='无'>无</option>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$("#xzzhifu").append(
										"<option value='"+obj[i].zhifu+"'>"
												+ obj[i].zhifu + "</option>");
							}
							xzpiaojuleixing();
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});

		});
	}

	function xzpiaojuleixing() {
		$(function() {
			$("#xzpiaoju").html("");
			$
					.ajax({
						type : "post",
						url : "paymentFashion.do?flag=getGuoHuQuery&ff=piaoju",
						data : {
							"pfashionName" : $("#xzzhifu").val()
						},
						dataType : "json",
						success : function(obj) {
							if (obj == null || obj.length <= 0) {
								$("#xzpiaoju").append(
										"<option value='无'>无</option>");
								return false;
							}
							if (obj[0].result == null) {
								$("#xzpiaoju").append(
										"<option value='无'>无</option>");
								return false;
							}
							for ( var i = 0; i < obj.length; i++) {
								$("#xzpiaoju").append(
										"<option value='"+obj[i].piaoju+"'>"
												+ obj[i].piaoju + "</option>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});

		});
	}

	function xzpanduanjiesuan() {
		$(function() {
			if ($("#xzjiesuan").val() == "月结") {
				//设置实收费用为只读
				$("#shishou").attr("disabled", "true");

				$("#xzzhifu").html("");
				$("#xzzhifu").append("<option value='无'>无</option>");
				$("#xzpiaoju").html("");
				$("#xzpiaoju").append("<option value='无'>无</option>");
			} else {
				$("#shishou").attr("disabled", false);
				xzzhifufangshi();
			}
		});
	}

	var eventHandler = function(name) {
		return function() {
			console.log(name);
			QueryZhuanChu();
		};
	};
	function QueryZhuanChu() {

		$(function() {
			$
					.ajax({
						type : "post",
						url : "shiftStock.do?flag=QueryZhuanChu&ff=zhuanchu",
						async : true,
						dataType : "json",
						success : function(obj) {
							$("#zhuanchu").html("");
							if (obj != null) {
								for ( var i = 0; i < obj.length; i++) {
									$("#zhuanchu").append(
											"<option value='" + obj[i].id + "'>"
													+ obj[i].jiancheng
													+ "</option>");
									if($("#clientId").val()==obj[i].id){
										$("#zhuanchu").val(obj[i].id);
									}
								}
								$("#zhuanchu").selectize();
							} else {
								$("#zhuanchu").append(
										"<option value='无客户'>无客户</option>");
							}
						},
						error : function() {
							document.location.href = "/XGProject/cangchu/page/login.jsp";
						}
					});
		});
	}
</script>


<script>
	$(function() {
		//拖动模态框
		var $div = $(".modeal_bottom_top"); /* 绑定鼠标左键按住事件 */
		$div.bind("mousedown", function(event) { /* 获取需要拖动节点的坐标 */
			var offset_x = $("#modeal")[0].offsetLeft; // x坐标
			var offset_y = $("#modeal")[0].offsetTop; // y坐标 /* 获取当前鼠标的坐标 */
			var mouse_x = event.pageX;
			var mouse_y = event.pageY; /* 绑定拖动事件 *//* 由于拖动时，可能鼠标会移出元素，所以应该使用全局（document）元素 */
			$(document).bind("mousemove", function(ev) {
				/* 计算鼠标移动了的位置 */
				var _x = ev.pageX - mouse_x;
				var _y = ev.pageY - mouse_y; /* 设置移动后的元素坐标 */
				var now_x = (offset_x + _x) + "px";
				var now_y = (offset_y + _y) + "px"; /* 改变目标元素的位置 */
				$("#modeal").css({
					top : now_y,
					left : now_x
				});
			}); /* 当鼠标左键松开，接触事件绑定 */
			$(document).bind("mouseup", function() {
				$(this).unbind("mousemove");
			});

		});
	});
</script>

</html>