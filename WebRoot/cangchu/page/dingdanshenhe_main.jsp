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

<%
	int x = 0;
	int shenhe = 0;
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("过户审核")){
		x++;
	}
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单审核")){
		shenhe++;
	}
		}
%>

<!DOCTYPE html>
<html>
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
<!--引入自写的css样式-->
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />

<link rel="stylesheet" type="text/css" href="cangchu/css/daichuli.css" />
<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/dingdanshenhe_main.js"></script>

<style>
.content_tab {
	height: 50px;
	padding-bottom: 10px;
}

.tr_diaodu_bg {
	background-color: #f0f8ff;
}
</style>
</head>
<script>
	$(function() {
		$(".content_tab table .tr_diaodu_bg:first td").css({
			"border-top" : "5px solid #FFFFFF"
		});

		$(".caozuo_zhankai").click(function() {
			var h = $(this).parents(".content_tab").height();
			if (h == 50) {
				$(this).parents(".content_tab").animate({
					"height" : "250px"
				}, 200);
			} else {
				$(this).parents(".content_tab").animate({
					"height" : "50px"
				}, 200);
			}
		});

		/*当单机查看详情的时候触发*/
		/*$(".content_tab table tr td").not("td:last-child").click(function() {

			if(confirm("确定要开始作业吗？")) {
				document.location.href = "dingdanshenhe.html";
			}
		});*/

	});

	//跳转到入库操作页面
	function tiaozhuanruku(str) {
		$(function() {
			//获取总订单id
			var zongid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").eq(1).text();
			//获取子订单id
			var ziId = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").find("#ziId").val();

			//获取操作订单id
			var caozuoid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").find("#caozuoId").val();

			if (confirm("确定要开始作业吗？")) {
				// "${pageContext.request.contextPath}/checks.do?flag=ZhengZaiChecks&checkId="+
				// id
				document.location.href = "inputOperate.do?flag=selectShenHeOperat&caozuoid="
						+ caozuoid + "&ziId=" + ziId;
			}
		});
	}

	//跳转到入库操作页面
	function tiaozhuanchuku(str) {
		$(function() {
			//获取总订单id
			var zongid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").eq(1).text();
			//获取子订单id
			var ziId = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").find("#ziId").val();

			//获取操作订单id
			var caozuoid = $(str).parents(".content_tab").find("tr").eq(0)
					.children("td").find("#caozuoId").val();

			if (confirm("确定要开始作业吗？")) {
				document.location.href = "exportOperate.do?flag=getShenheChakai&caozuoid="
						+ caozuoid + "&ziId=" + ziId;
			}
		});
	}

	//跳转到过户的页面
	function tiaozhuanguohu(str) {
		$(function() {
			//获取过户子订单id
			var zid = $(str).parents(".content_tab").find("tr").eq(0).children(
					"td").eq(1).children("#ziId").val();
			if (confirm("确定要开始作业吗？")) {
				// "${pageContext.request.contextPath}/checks.do?flag=ZhengZaiChecks&checkId="+
				// id
				document.location.href = "shiftStockSeed.do?flag=getShenHeXiangXi&ssseedId="
						+ encodeURI(encodeURI(zid));
			}
			;
		});
	}
</script>
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
	if(shenhe==0){
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
<body onload="jiazaiLoad();">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<!--头部当前位置-->
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">仓储业务</a>
				<span>></span>&nbsp;
				<a style="cursor:pointer;">订单审核</a>
				<span>/</span>&nbsp;

			</ul>
		</div>
		<div class="daichuli_content">
			<div class="content_top">
				<select id="type_xuanze" onchange="jiazaiLoad();">
					<option value="出库订单">出库订单</option>
					<option value="入库订单">入库订单</option>
					<%
						if (x != 0) {
					%>
					<option value="过户订单">过户订单</option>
					<%
						}
					%>
				</select>
				<div class="sousu">
					<input type="text" placeholder="车号" class="kehudanhao"
						id="kehudanhao" /> <input type="text" placeholder="货主"
						class="huozhu" id="huozhu" /> <input type="button"
						class="but_sousu" id="sousu_but" value="查询" />
				</div>
				<img src="cangchu/img/shuaxin.png" id="shuaxin" title="刷新" />
			</div>

			<div class="content_center">
				<c:forEach items="${listInputOperate }" var="operate">
					<div class="content_tab" title="开始作业">
						<table cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<td onclick="tiaozhuanruku(this)" align="right"><strong>订单编号：</strong>
									</td>
									<td onclick="tiaozhuanruku(this)"><span>${operate.inputSeed.input.inputId}
									</span> <input type="hidden" id="ziId"
										value="${operate.inputSeed.iseedId }" /> <input type="hidden"
										id="caozuoId" value="${operate.ioperateId }" /></td>
									<td onclick="tiaozhuanruku(this)" align="right"><strong>货主：</strong>
									</td>
									<td onclick="tiaozhuanruku(this)"><span>${operate.inputSeed.input.client.clientLoginName
											}</span>
									</td>
									<td onclick="tiaozhuanruku(this)" align="right"><strong>客户单号：</strong>
									</td>
									<td onclick="tiaozhuanruku(this)"><span>${operate.inputSeed.input.inputClientNumber
											}</span>
									</td>
									<td onclick="tiaozhuanruku(this)" align="right"><strong>有效期：</strong>
									</td>
									<td onclick="tiaozhuanruku(this)"><span>${operate.inputSeed.input.inputDefinedOne
											}</span>
									</td>
									<td rowspan="4" valign="top"><img
										src="cangchu/img/xiangxia.png" width="20"
										onclick="showHidden(this)" class="caozuo_zhankai"
										style="display:block;margin-top:10px;" />
									</td>
								</tr>
								<tr>
									<td align="right"><strong>运输方式：</strong>
									</td>
									<td><span>${operate.inputSeed.input.inputCarryType
											}</span>
									</td>
									<td align="right"><strong>车号：</strong>
									</td>
									<td><span>${operate.inputSeed.input.inputPlateNumber
											}</span>
									</td>
									<td align="right"><strong>司机姓名：</strong>
									</td>
									<td><span>${operate.inputSeed.input.inputDriverName
											}</span>
									</td>
									<td align="right"><strong>司机电话：</strong>
									</td>
									<td><span>${operate.inputSeed.input.inputDriverTel
											}</span>
									</td>
								</tr>
								<tr class="tr_bg">
									<td align="right" class="tr_border_left"><strong>货物品类：</strong>
									</td>
									<td><span>${operate.inputSeed.goods.goodsCategory.goodsCategoryName
											}</span>
									</td>
									<td align="right"><strong>名称：</strong>
									</td>
									<td><span>${operate.inputSeed.goods.goodsName }</span>
									</td>
									<td align="right"><strong>规格：</strong>
									</td>
									<td><span>${operate.inputSeed.goods.goodsStandard.goodsStandardName
											}</span>
									</td>
									<td align="right"><strong>材质：</strong>
									</td>
									<td class="tr_border_right"><span>${operate.inputSeed.goods.goodsQuality.goodsQualityName
											}</span>
									</td>
								</tr>
								<tr class="tr_bg">
									<td align="right" class="tr_border_left_xia"><strong>属性：</strong>
									</td>
									<td><span>${operate.inputSeed.goods.goodsProperty.goodsPropertyName
											}</span>
									</td>
									<td align="right"><strong>产地：</strong>
									</td>
									<td><span>${operate.inputSeed.goods.goodsYieldly.goodsYieldlyName
											}</span>
									</td>
									<td align="right"><strong>重量：</strong>
									</td>
									<td><span>${operate.inputSeed.iseedShouldWeight }</span>
									</td>
									<td align="right"><strong>发起时间：</strong>
									</td>
									<td class="tr_border_right_xia"><span>${operate.inputSeed.input.inputCreateTime
											}</span>
									</td>
								</tr>
								<tr class="tr_diaodu_bg">
									<td align="right" class="tr_border_left"><strong>调度员：</strong>
									</td>
									<td><span>${operate.interiorUserByIoperateDispatcherId.iuserLoginName
											}</span>
									</td>
									<td align="right"><strong>分配时间：</strong>
									</td>
									<td><span>${operate.ioperateDispatcherTime }</span>
									</td>
									<td align="right"><strong>过磅/理算：</strong>
									</td>
									<td><span>${operate.ioperatePonderationTrue }</span>
									</td>
									<td align="right"><strong>操作时间：</strong>
									</td>
									<td class="tr_border_right"><span>${operate.ioperateDispatcherTime
											}</span>
									</td>
								</tr>
								<tr class="tr_diaodu_bg">
									<td align="right" class="tr_border_left_xia"><strong>库位：</strong>
									</td>
									<td><span>${operate.tarehouse.tarehouseName }</span>
									</td>
									<td align="right"><strong>保管员：</strong>
									</td>
									<td><span>${operate.interiorUserByIoperateStoremanId.iuserLoginName
											}</span>
									</td>
									<td align="right"><strong>备注：</strong>
									</td>
									<td><span>${operate.ioperateRemark }</span>
									</td>
									<td colspan="2" class="tr_border_right_xia"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</div>
			<!--content_center结束-->
			<div class="content_bottom">
				<a style="cursor:pointer;" onclick="selectGengduo()" id="gengduo">查看更多</a>
				<input type="hidden" value="1" id="guohupage" /> <input
					type="hidden" value="load" id="mimi" />
			</div>
		</div>
		<!--daichuli_content结束-->
	</div>
	<!--main结束-->


</body>
</html>