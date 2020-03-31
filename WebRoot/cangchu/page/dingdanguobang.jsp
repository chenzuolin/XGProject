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
		
		List<Powers> power = (List<Powers>)request.getSession().getAttribute("power");
		for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("订单过磅")){
		x++;
	}
		}
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

.tab_context .tab_bottom {
	
}

#caozuotijiao {
	background: url(cangchu/img/zengjiahuowu.png);
}

#caozuotijiao:hover {
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
				<a style="cursor:pointer;">仓储业务</a>
				<span>/</span>&nbsp;
				<a href="javascript:window.history.go(-1);">订单过磅</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">开始操作</a>
			</ul>
		</div>

		<div class="chuku_context">
			<strong class="yunshu_xinxi">运 输 信 息</strong>

			<!--form开始-->
			<form
				action="${pageContext.request.contextPath }/exportOperate.do?flag=GuoBangTiJiao"
				name="exportOperateForm" id="guobangForm" method="post">
				<input type="hidden" value="${eo.eoperateId}" name="eoperateId" />
				<input type="hidden"
					value="<%=request.getSession().getAttribute("iulistId")%>"
					name="interiorUserByEoperatePonderationMan" />
				<!--table开始-->
				<table class="tab_head">
					<tr>
						<td align="right" width="80px">货主：</td>
						<td width="150px">${eo.exportSeed.export.client.clientAbbreviation
							}</td>
						<td align="right" width="80px">运输方式：</td>
						<td align="left" width="150px">${eo.exportSeed.export.exportCarryType}</td>
						<td align="right" width="80px">车号：</td>

						<td class="chehao" width="150px">
							<!--
                                        	作者：2586190195@qq.com
                                        	时间：2017-06-06
                                        	描述：判断选择的运输方式，显示不同的样式
                                        --> <!--当选择的运输方式是火运的时候显示-->
							甘A12345</td>
						<td align="right" width="80px">司机姓名：</td>
						<td>${eo.exportSeed.export.exportWagonNumber}</td>
						<!--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++未做完-->
					</tr>
					<tr>
						<td align="right">司机电话：</td>
						<td>${eo.exportSeed.export.exportDriverTel}</td>

						<td align="right" width="80px">客户单号：</td>
						<td>${eo.exportSeed.export.exportClientNumber}</td>
						<td>是否超发：</td>
						<td>${eo.exportSeed.export.exportDefinedOne}<input
							type="hidden" value="${eo.exportSeed.export.exportDefinedOne}"
							id="chaofa" />
						</td>
						<td align="right" width="100px">订单有效期：</td>
						<td>${eo.exportSeed.export.exportDefinedTwo}天</td>
					</tr>
				</table>

				<!--tabhead结束-->

				<!--货物信息开始-->

				<div id="tab_context" class="tab_context">
					<strong>货 物 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">货物品类：</td>
							<td width="150px">${eo.exportSeed.goods.goodsCategory.goodsCategoryName
								}</td>
							<td align="right" width="80px">货物名称：</td>
							<td width="150px">${eo.exportSeed.goods.goodsName }</td>
							<td align="right" width="80px">货物规格：</td>
							<td width="150px">${eo.exportSeed.goods.goodsStandard.goodsStandardName
								}</td>
							<td align="right" width="80px">货物材质：</td>
							<td width="150px">${eo.exportSeed.goods.goodsQuality.goodsQualityName
								}</td>
						</tr>
						<tr>
							<td align="right">货物属性：</td>
							<td>${eo.exportSeed.goods.goodsProperty.goodsPropertyName }</td>
							<td align="right">货物产地：</td>
							<td>${eo.exportSeed.goods.goodsYieldly.goodsYieldlyName }</td>
							<td align="right">应出重量：</td>
							<td>${eo.exportSeed.eseedShouldWeight }吨<input type="hidden"
								value="${eo.exportSeed.eseedShouldWeight }" id="yczl" /></td>
							<td align="right">备注：</td>
							<td>${eo.exportSeed.eseedRemark }</td>
						</tr>
					</table>
				</div>
				<!--tab_context结束-->

				<div id="tab_context" class="tab_context">
					<strong>分 配 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">调度员：</td>
							<td width="150px">${eo.interiorUserByEoperateDispatcher.iuserName
								}</td>
							<td align="right" width="80px">分配时间：</td>
							<td width="150px">${eo.eoperateDispatcherTime }</td>
							<td align="right" width="80px">过磅/理算：</td>
							<td width="150px" id="guobang_lisuan">${eo.eoperatePonderationTrue
								}</td>
							<td align="right" width="80px">分配重量：</td>
							<td width="150px">${eo.eoperateDefinedTwo=="null"?"0":eo.eoperateDefinedTwo}吨</td>
						</tr>
						<tr>
							<td align="right">库位：</td>
							<td>${eo.tarehouse.tarehouseName}</td>
							<td>订单状态：</td><td>${eo.eoperateDefinedOne}</td>
							<td align="right">备注：</td>
							<td colspan="3">${eo.eoperateRemark}</td>
						</tr>
					</table>
				</div>

				<div id="tab_context" class="tab_context">
					<strong>填 写 信 息</strong> <br />
					<table class="tab_bottom">
						<tr>
							<td align="right" width="80px">重量：</td>
							<td width="150px"><input type="text" id="weight"
								name="eoperateRealityWeight" />
							</td>

							<td align="right">备注：</td>
							<td><input type="text" name="eoperateRemark" />
							</td>
						</tr>
						<tr style="height: 10px;">
							<td></td>
						</tr>
					</table>
				</div>
				<div class="queren">
					<a style="cursor:pointer;" id="caozuotijiao">提 交</a>
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

		//当点击提交的时候
		$("#caozuotijiao").click(function() {
			var weight = $("#weight").val(); //出库重量

			var zhongyan = /^\d+(\.\d+)?$|^$|^(\d+|\-){7,}$/;
			if (weight == "") {
				alert("重量不可以为空，请重新填写！");
				return false;
			}
			if (zhongyan.test(weight) == false) {
				alert("请核对重量的填写！");
				return false;
			}

			var chaofa = $("#chaofa").val();
			if (chaofa == "不接受超发") {
				if (parseFloat(weight) > parseFloat($("#yczl").val())) {
					alert("填写重量不能大于应出重量！");
					return false;
				}
			}

			if (confirm("确定要提交吗？")) {
				$("#guobangForm").submit();
			}
		});
	});
</script>

</html>