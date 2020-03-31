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
<script type="text/javascript" src="cangchu/d/tableExport.js"></script>
<script type="text/javascript" src="cangchu/d/jquery.base64.js"></script>
<script type="text/javascript" src="cangchu/d/jszip-utils.js"></script>
<script type="text/javascript" src="cangchu/d/xlsx.core.min.js"></script>
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

.queren a {
	background: url(cangchu/img/zengjiahuowu.png);
}

.queren a:hover {
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
	border-top: 1px solid #888888;
	border-left: 1px solid #888888;
}
</style>
</head>

<body onload="load()">

	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1)">订单收费</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">开始操作</a>
			</ul>
		</div>

		<div class="chuku_context">
			<strong class="yunshu_xinxi">订 单 信 息</strong>

			<!--form开始-->
			<form
				action="${pageContext.request.contextPath }/shiftStockSeed.do?flag=ShouFeiShiftStock"
				method="post" name="shiftStockSeedForm" id="guohuForm">
				<input type="hidden" value="${sss.ssseedId }" name="ssseedId"
					id="zid" /> <input type="hidden"
					value="<%=request.getSession().getAttribute("iulistId")%>"
					name="interiorUserBySsseedCollectMan" />
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">订单编号：</td>
						<td width="150px">${sss.shiftStock.sstockId }</td>
						<td align="right" width="80px">转出单位：</td>
						<td width="150px"><input type="hidden"
							value="${sss.shiftStock.clientBySstockClientId.clientId}"
							name="clientBySstockClientId" />${sss.shiftStock.clientBySstockClientId.clientAbbreviation
							}</td>
						<td align="right" width="80px">转入单位：</td>
						<td align="left" width="150px"><input type="hidden"
							value="${sss.shiftStock.clientBySstockShiftToFirm.clientId}"
							name="clientBySstockShiftToFirm" />${sss.shiftStock.clientBySstockShiftToFirm.clientAbbreviation
							}</td>
						<td align="right" width="80px">过户时间：</td>

						<td class="chehao" width="150px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							${sss.shiftStock.sstockReateTime }</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right" width="80px">客户单号：</td>
						<td>${sss.shiftStock.sstockClientNumber }</td>
						<td align="right">订单状态：</td>
						<td>${sss.ssseedOrderStatus }</td>

						<td align="right" width="80px">备注：</td>
						<td colspan="3">${sss.ssseedRemark }</td>
					</tr>
				</table>

				<!--tabhead结束-->

				<!--货物信息开始-->

				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">${sss.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${sss.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">${sss.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">${sss.goods.goodsQuality.goodsQualityName
								}</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${sss.goods.goodsProperty.goodsPropertyName }</td>
							<td align="right">货物产地：</td>
							<td>${sss.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">过户重量：</td>
							<td>${sss.ssseedWeight }吨</td>
							<td align="right">转库类型：</td>
							<td>${sss.shiftStock.sstockDefinedOne==null?"无":sss.shiftStock.sstockDefinedOne }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->

				<div id="tab_context" class="tab_context">
					<strong>填 入 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="110px">应收费用：</td>
							<td width="150px">${sss.ssseedShouldCost}元<input
								type="hidden" value="${sss.ssseedShouldCost}" id="yingshou" />
							</td>
							<td align="right" width="80px">实收费用：</td>
							<td width="150px"><input type="text" id="shishoufei"
								name="ssseedRealityCost" />
							</td>
							<td align="right" width="80px">结算方式：</td>
							<td width="150px">
								<!-- 起个名字，提交到form中获取，用于修改订单状态 --> <select id="jiesuan"
								name="ssseedClientAccounts" onchange="panduanjiesuan()">
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
							<td><select id="piaoju" name="paymentFashion">
							</select>
							</td>
							<td align="right" width="80px">备注：</td>
							<td colspan="2" width="150px"><input type="text"
								name="ssseedRemark" />
							</td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
				<div class="queren">
					<a id="tanxiazhan"
						style="cursor:pointer; color:#ffffff; text-align:center; line-height:30px;">下站费</a>
					<a
						style="background: url(cangchu/img/zengjiahuowu.png); cursor:pointer; float:right; display:none;"
						id="dayin" onclick="tiaozhuanguohu()"><i>打印</i> </a> <a
						id="shoufei_tijiao" style="cursor:pointer;">提交</a>
				</div>
			</form>
		</div>
		<!--出库内容结束-->
	</div>

	<script>
		function tiaozhuanguohu() {
			$(function() {
				var id = $("#zid").val();
				document.location.href = "${pageContext.request.contextPath}/shiftStockSeed.do?flag=getXiangQing&ff=dayin&ssseedId="
						+ encodeURI(encodeURI(id));
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
		<input type="hidden" value="${sss.shiftStock.sstockId }"
			name="xadefinedtwo" /> <input type="hidden" value="过户"
			name="xzdefinedthree" /> <input type="hidden"
			value="${sss.shiftStock.clientBySstockClientId.clientId}"
			name="clientByXzzhuanchuclient" /> <input type="hidden"
			value="${sss.shiftStock.clientBySstockShiftToFirm.clientId}"
			name="clientByXzzhuanruclient" />
		<div class="modeal_bottom" id="modeal">
			<div>
				<div>
					<div class="modeal_bottom_top">
						<h3>下 站 费 信 息</h3>
					</div>
					<div class="modeal_bottom_middle">
						<div id="xiazhan" class="tab_context" style="margin-top: 5%;">
							<table class="tab_bottom" style="width:90%; margin: 0 auto;">
								<tr>
									<td align="right" width="110px">客户名称：</td>
									<td width="150px">${sss.shiftStock.clientBySstockShiftToFirm.clientAbbreviation
										}</td>
									<td align="right" width="100px">下站费单价：</td>
									<td width="150px"><input type="text" id="xiazhandanjia"
										name="xzdanjia" /></td>
									<td align="right" width="80px">重量：</td>
									<td width="150px"><input type="text" readonly="readonly"
										value="${sss.ssseedWeight }" id="exportweight" name="xzweight" />
									</td>
								</tr>
								<tr>
									<td align="right" width="100px">下站费合计：</td>
									<td width="150px"><input type="text" readonly="readonly"
										id="xiazhanheji" name="xzcount" /></td>
									<td align="right" width="80px">结算方式：</td>
									<td width="150px"><select id="xzjiesuan"
										onchange="xzpanduanjiesuan()" name="xzdefinedone">
											<option value="现结">现结</option>
											<option value="月结">月结</option>
									</select></td>
									<td align="right" width="110px">支付方式：</td>
									<td width="150px"><select id="xzzhifu" name="xzzhifu">
									</select></td>

								</tr>
								<tr>
									<td align="right">票据类型：</td>
									<td><select id="xzpiaoju" name="xzpiaoju">
									</select></td>
									<td align="right" width="80px">实收费用：</td>
									<td width="150px"><input type="text" name="xadefinedfour" />
									</td>
									<td align="right" width="80px">备注：</td>
									<td width="150px"><input type="text" name="xzremark" /></td>
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
		$("#xzqueding").click(
				function() {
					if ($("#xiazhanheji").val() == ""
							|| $("#xiazhanheji").val() == "NaN") {
						alert("请计算下站费合计！");
						return false;
					}
					if (confirm("确定提交吗？")) {
						$("#xzform").submit();
					}
				});

		//当收费完成点击提交的时候进行判断
		$("#shoufei_tijiao").click(
				function() {
					if ($("#jiesuan").val() == "月结") {
						if (confirm("确定提交？")) {
							$("#guohuForm").submit();
						}
					} else {
						if ($("#shishoufei").val() == ""
								|| $("#shishoufei").val() == "0") {
							alert("实收费用不能为空！");
							return false;
						}
						if ($("#zhifu").val() == ""
								|| $("#shishoufei").val() == "无") {
							alert("请选择支付方式！");
							return false;
						}
						if ($("#piaoju").val() == ""
								|| $("#piaoju").val() == "无") {
							alert("请选择票据类型！");
							return false;
						}
						if (parseFloat($("#shishoufei").val()) != parseFloat($(
								"#yingshou").val())) {
							if (confirm("应收费用和实收费用不匹配，是否确认提交！")) {
								$("#guohuForm").submit();
								return false;
							}
						}

						if (confirm("确定提交吗？")) {
							$("#guohuForm").submit();
						}

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
				$("#zhifu").html("");
				$("#zhifu").append("<option value='无'>无</option>");
				$("#piaoju").html("");
				$("#piaoju").append("<option value='无'>无</option>");
			} else {
				zhifufangshi();
			}
		});
	}

	function load() {
		zhifufangshi();
		xzzhifufangshi();
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
				$("#xzzhifu").html("");
				$("#xzzhifu").append("<option value='无'>无</option>");
				$("#xzpiaoju").html("");
				$("#xzpiaoju").append("<option value='无'>无</option>");
			} else {
				xzzhifufangshi();
			}
		});
	}
</script>

<script>
	$(function() {
		//拖动模态框
		// 拖动模态框
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