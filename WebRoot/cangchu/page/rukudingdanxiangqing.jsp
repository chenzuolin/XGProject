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

#zuofei {
	background: url(cangchu/img/zengjiahuowu.png);
	display: block;
	width: 110px;
	height: 30px;
	text-align: center;
	position: absolute;
	left: 65%;
	top: 9%;
}

#zuofei i {
	color: #FFFFFF;
	font-style: normal;
	line-height: 30px;
}

#zuofei:hover {
	background: url(cangchu/img/zengjiahuowu2.png);
}

#update {
	background: url(cangchu/img/zengjiahuowu.png);
	display: block;
	width: 110px;
	height: 30px;
	text-align: center;
	position: absolute;
	left: 57%;
	top: 9%;
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

#modeal table tr {
	height: 40px;
	line-height: 40px;
}

#modeal table tr td {
	text-align: center;
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
	int update = 0;
	int zuofei = 0;
		
	List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
	for(int i=0; i<power.size(); i++){
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("出库完成")){
	x++;
		}
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单修改")){
	update++;
		}
		if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单作废")){
	zuofei++;
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
				<a href="javascript:window.history.go(-1);">订单查询</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">查看详细</a>
			</ul>
		</div>
		<input type="hidden" value="${is.iseedId }" id="seedId" />
		<script>
			function tiaozhuanruku() {
				$(function() {
					var id = $("#seedId").val();
					document.location.href = "${pageContext.request.contextPath}/inputSeed.do?flag=getXiangQing&ff=update&iseedId="
							+ encodeURI(encodeURI(id));
				});
			}
		</script>
		<div class="chuku_context">
			<div class="queren">
				<strong class="yunshu_xinxi">运 输 信 息</strong>
				<%
					if(update!=0){
				%>
				<a target="context" style="cursor:pointer;" id="update"
					onclick="tiaozhuanruku()"><i>订单修改</i> </a>
				<%
					}
				%>

				<%
					if(zuofei!=0){
				%>
				<a style="cursor:pointer;" id="zuofei"><i>订单作废</i> </a>
				<%
					}
				%>
				<%
					if(x!=0){
				%>
				<c:if
					test="${is.iseedOrderStatus != '订单作废' && is.iseedOrderStatus != '入库完成' && is.iseedOrderStatus != '已收费' && is.iseedOrderStatus != '未收费'  }">
					<a style="cursor:pointer; " target="_blank" id="chukuwancheng"><i>入库完成</i>
					</a>
				</c:if>
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
						<td width="150px">${is.input.client.clientAbbreviation }</td>
						<td align="right" width="80px">运输方式：</td>
						<td align="left" width="150px">${is.input.inputCarryType }</td>
						<td align="right" width="80px">车号：</td>

						<td class="chehao" width="150px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							${is.input.inputPlateNumber }</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>${is.input.inputDriverName }</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">司机电话：</td>
						<td>${is.input.inputDriverTel }</td>

						<td align="right" width="80px">客户单号：</td>
						<td>${is.input.inputClientNumber }</td>
						<td align="right" width="100px">发起时间：</td>
						<td>${is.input.inputCreateTime }</td>
						<td align="right" width="100px">订单有效期：</td>
						<td>${is.input.inputDefinedOne }天</td>
					</tr>
				</table>

				<!--tabhead结束-->

				<!--货物信息开始-->

				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br /> <input type="hidden"
						value="${is.iseedId }" id="kzid" />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">${is.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${is.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">${is.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">${is.goods.goodsQuality.goodsQualityName }</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${is.goods.goodsProperty.goodsPropertyName }</td>
							<td align="right">货物产地：</td>
							<td>${is.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">应收重量：</td>
							<td>${is.iseedShouldWeight }吨</td>
							<td align="right">实收重量：</td>
							<td>${is.iseedRealityWeight==null?"0":is.iseedRealityWeight
								}吨</td>
						</tr>
						<tr style="height:30px">
							<td align="right">实收件数：</td>
							<td>${is.iseedRealityNumber==null?"0":is.iseedRealityNumber
								}${is.goods.goodsUnit.goodsUnitName }</td>
							<td align="right">应收费用：</td>
							<td>${is.iseedShouldCost==null?"0":is.iseedShouldCost }元</td>
							<td align="right">实收费用：</td>
							<td>${is.iseedRealityCost==null?"0":is.iseedRealityCost }元</td>
							<td align="right">订单状态：</td>
							<td>${is.iseedOrderStatus }</td>
						</tr>
						<tr>
							<td align="right">车皮号：</td>
							<td>${is.iseedDefinedOne }</td>
							<td align="right">备注：</td>
							<td colspan="5">${is.iseedRemark }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->

				<div id="tab_context" class="tab_context">
					<strong>分 配 信 息</strong> <br />
					<c:forEach items="${iolist }" var="z">
						<table class="tab_bottom" style="padding-bottom:10px;">
							<tr>
								<td align="right" width="80px">调度员：</td>
								<td width="150px">${z.interiorUserByIoperateDispatcherId.iuserName
									}</td>
								<td align="right" width="80px">分配时间：</td>
								<td width="150px">${z.ioperateDispatcherTime }</td>
								<td align="right" width="80px">过磅/理算：</td>
								<td width="150px" id="guobang_lisuan">${z.ioperatePonderationTrue
									}</td>
								<td align="right" width="80px">库位：</td>
								<td width="150px">${z.tarehouse.tarehouseName }</td>
							</tr>
						</table>
					</c:forEach>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>操 作 信 息</strong> <br />
					<c:forEach items="${iolist }" var="z">
						<table class="tab_bottom">
							<tr style="height:30px">
								<td align="right" width="110px">执行人：</td>
								<td width="150px">${z.interiorUserByIoperateStoremanId.iuserName
									}</td>
								<td align="right" width="100px">操作开始时间：</td>
								<td width="150px">${z.ioperateScreateTime==null?"无":z.ioperateScreateTime
									}</td>
								<td align="right" width="100px">操作结束时间：</td>
								<td width="150px">${z.ioperateSfinishTime==null?"无":z.ioperateSfinishTime
									}</td>
								<td align="right" width="110px">操作重量：</td>
								<td width="150px">${z.ioperateRealityWeight==null?"0":z.ioperateRealityWeight
									}吨</td>
							</tr>
							<tr style="height:30px">
								<td align="right" width="110px">操作件数：</td>
								<td width="150px">${z.ioperateRealityNumber==null?"0":z.ioperateRealityNumber
									}${z.inputSeed.goods.goodsUnit.goodsUnitName }</td>
								<td align="right" width="80px">司磅员：</td>
								<td width="150px">${z.interiorUserByIoperatePonderationManId==null?"无":z.interiorUserByIoperatePonderationManId.iuserName
									}</td>
								<td align="right" width="80px">过磅重量：</td>
								<td width="150px">${z.ioperatePonderationTrue=="理算"?"0":z.ioperateRealityWeight==null?"0":z.ioperateRealityWeight
									}吨</td>
								<td align="right" width="110px">过磅时间：</td>
								<td width="150px">${z.ioperatePonderationTime==null?"无":z.ioperatePonderationTime
									}</td>
							</tr>
							<tr style="height:30px">
								<td align="right" width="80px">天车工：</td>
								<td width="150px">${z.ioperateCraneman==null?"无":z.ioperateCraneman
									}</td>
								<td align="right" width="80px">装卸工：</td>
								<td width="150px">${z.ioperateStevedore==null?"无":z.ioperateStevedore
									}</td>
								<td align="right">车号：</td>
								<td>${z.ioperateTruckNum==null?"无":z.ioperateTruckNum }</td>
							</tr>
							<tr style="height:30px">
								<td align="right">操作状态：</td>
								<c:choose>
									<c:when test="${z.ioperateDefinedTwo=='订单作废' }">
										<td style="color:red;">${z.ioperateDefinedTwo }</td>
									</c:when>
									<c:otherwise>
										<td>${z.ioperateDefinedTwo }</td>
									</c:otherwise>
								</c:choose>

								<td align="right">备注：</td>
								<td colspan="5">${z.ioperateRemark==null?"无":z.ioperateRemark
									}</td>
							</tr>
						</table>
					</c:forEach>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>审 核 信 息</strong> <br />
					<c:forEach items="${iolist }" var="z">
						<table class="tab_bottom" style="padding-bottom:10px;">
							<tr style="height:30px">
								<td align="right" width="110px">审核人：</td>
								<td width="150px">${z.interiorUserByIoperateAuditingId==null?"无":z.interiorUserByIoperateAuditingId.iuserName
									}</td>
								<td align="right" width="100px">审核时间：</td>
								<td width="150px">${z.ioperateAuditingTime==null?"无":z.ioperateAuditingTime
									}</td>
								<td align="right" width="100px">审核次数：</td>
								<td width="150px">${z.ioperateDefinedOne==null?"无":z.ioperateDefinedOne
									}</td>
							</tr>
						</table>
					</c:forEach>
				</div>
				<div id="tab_context" class="tab_context">
					<strong>收 费 信 息</strong> <br />
					<c:forEach items="${iolist }" var="z">
						<table class="tab_bottom">
							<tr style="height:30px">
								<td align="right" width="110px">收费人：</td>
								<td width="150px">${z.interiorUserByIoperateCollectManId==null?"无":z.interiorUserByIoperateCollectManId.iuserName
									}</td>
								<td align="right" width="100px">收费时间：</td>
								<td width="150px">${z.ioperateCollectTime==null?"无":z.ioperateCollectTime
									}</td>
								<td align="right" width="100px">应收费用：</td>
								<td width="150px">${z.ioperateShouldCost==null?"0":z.ioperateShouldCost
									}元</td>
								<td align="right" width="110px">实收费用：</td>
								<td width="150px">${z.ioperateRealityCost==null?"0":z.ioperateRealityCost
									}元</td>
							</tr>
							<tr style="height:30px">
								<td align="right" width="80px">结算方式：</td>
								<td width="150px">${z.ioperateClientAccounts==null?"无":z.ioperateClientAccounts
									}</td>
								<td align="right" width="80px">支付方式：</td>
								<td width="150px">${z.ioperatePaymentFashion==null?"无":z.ioperatePaymentFashion.pfashionName
									}</td>
								<td align="right" width="110px">票据类型：</td>
								<td width="150px">${z.ioperatePaymentFashion==null?"无":z.ioperatePaymentFashion.pfashionReceipt
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
	<script>
		$(function() {
			$("#modeal a")
					.click(
							function() {
								var id = $(this).next("#zid").val();

								if (confirm("确定要作废此操作订单吗？")) {
									document.location.href = "${pageContext.request.contextPath }/inputOperate.do?flag=DanGeZuoFei&ioperateId="
											+ encodeURI(encodeURI(id));
								}
							});
		});
	</script>

	<div class="modeal_bottom" id="modeal">
		<div>
			<div>
				<div class="modeal_bottom_top">
					<h3>再次确认入库订单</h3>
					<button id="close">X</button>
				</div>
				<div class="modeal_bottom_middle">
					<table style="width:100%;" cellspacing="0" cellpadding="0"
						border="1">
						<tr>
							<th>操作</th>
							<th>分配人</th>
							<th>执行人</th>
							<th>库位</th>
							<th>过磅/理算</th>
							<th>操作件数</th>
							<th>天车工</th>
							<th>装卸工</th>
						</tr>
						<c:forEach items="${iolist }" var="z">
							<c:if test="${z.ioperateDefinedTwo!='订单作废' }">
								<tr>
									<td><a
										style="padding:3px 8px; background: #C83C23; color:#ffffff; text-decoration: none;
										border-raduis:5px; cursor:pointer;">作废</a>
										<input type="hidden" value="${z.ioperateId }" id="zid" />
									</td>
									<td>${z.interiorUserByIoperateDispatcherId.iuserName}</td>
									<td>${z.interiorUserByIoperatePonderationManId.iuserName }</td>
									<td width="150px">${z.tarehouse.tarehouseName }</td>
									<td width="150px" id="guobang_lisuan">${z.ioperatePonderationTrue}</td>
									<td>${z.ioperateRealityNumber==null?"0":z.ioperateRealityNumber
										}${z.inputSeed.goods.goodsUnit.goodsUnitName }</td>
									<td>${z.ioperateCraneman==null?"无":z.ioperateCraneman }</td>
									<td>${z.ioperateStevedore==null?"无":z.ioperateStevedore }</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>

				</div>
				<div class="modeal_bottom_bottom">
					<button id="closes">关闭</button>
				</div>
			</div>
		</div>
		<div class="modeal_bg" style="z-index:-1"></div>
</body>
<script>
	$(function() {
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

		$("#close").click(function() {
			$(".modeal_bottom").css("display", "none");
			$(".modeal_bg").css("display", "none");
		});
		$("#closes").click(function() {
			$(".modeal_bottom").css("display", "none");
			$(".modeal_bg").css("display", "none");

		});

		//当点击订单作废的时候触发
		$("#zuofei").click(function() {
			// 确认提交
			$(".modeal_bg").animate({
				"opacity" : 0.5
			}).css("display", "block");
			$(".modeal_bottom").animate({
				"opacity" : 1,
				"top" : "25%"
			}).css("display", "block");
		});

		//当点击入库完成的时候触发
		$("#chukuwancheng")
				.click(
						function() {
							$
									.ajax({
										type : "post",
										url : "inputSeed.do?flag=RuKuWanChengAjax",
										data : {
											"iseedId" : $("#kzid").val()
										},
										dataType : "json",
										success : function(obj) {
											if (obj[0].result == "no") {
												if (confirm("该订单正在操作，是否完成入库？")) {
													document.location.href = "inputSeed.do?flag=RuKuWanCheng&ff=daichuli";
												}

											} else {
												document.location.href = "inputSeed.do?flag=RuKuWanCheng&ff=daichuli";
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