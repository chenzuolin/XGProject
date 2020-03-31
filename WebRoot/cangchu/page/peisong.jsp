<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinggang.project.entity.InteriorUser"%>
<%@ page import="com.xinggang.project.entity.InteriorUserDuty"%>
<%@ page import="com.xinggang.project.entity.Powers"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<link rel="stylesheet" type="text/css" href="cangchu/css/faqichuku.css" />
<link rel="stylesheet" type="text/css" href="cangchu/css/daichuli.css" />
<!--引入自写的css样式-->
<script type="text/javascript" src="cangchu/js/jquery1.9.0.min.js"></script>
<script type="text/javascript" src="cangchu/js/peisong.js"></script>
<!-- <script type="text/javascript" src="cangchu/js/faqichuku.js"></script> -->
<style>
.showpici {
	position: absolute;
	border: 1px solid #F2F2F2;
	-moz-box-shadow: 5px 5px 5px #000;
	-webkit-box-shadow: 5px 5px 5px #000;
	box-shadow: 2px 2px 10px #000;
	border-top: 1px solid #888888;
	border-left: 1px solid #888888;
	display: none;
	top: 0;
	left: 0;
	z-index: 1000;
}

.showpici span {
	font-size: 14px;
	color: red;
}
</style>
</head>

<body onload="jiazaiLoad();">
	<!--内容起始处-->
	<div id="mainFrame" class="main">
		<!--头部当前位置-->
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer">首页</a>
				<span>></span>&nbsp;
				<a href="javascript:document.location.reload();">配送订单</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="daichuli_content">
			<div class="content_top">
				<select id="type_xuanze" onchange="jiazaiLoad();"
					style="display: none">
					<option value="出库订单">出库订单</option>
				</select>
				<div class="sousu" style="padding-left:25%">
					<input type="text" placeholder="货主" class="huozhu" /> <input
						type="button" id="sousu_but" value="查询" />
				</div>
				<img src="cangchu/img/shuaxin.png" id="shuxin" title="刷新" />
			</div>
			<div class="content_center">
				<c:forEach items="${inputSeedlist }" var="seedlist" varStatus="v">
					<div class="content_tab">
						<table cellpadding="0" cellspacing="0">
							<tbody>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</div>
			<!--content_center结束-->
			<div class="content_bottom">
				<a id="chakanGenduo" style="cursor: pointer;">查看更多</a> <input
					type="hidden" value="1" id="chukupage" />
			</div>
		</div>
		<!--daichuli_content结束-->
	</div>
</body>
<script type="text/javascript">
	$(function() {
		//当点击查看更多时调用方法
		$("#chakanGenduo").click(function() {
			selectGengduo();
		});
	});

	function tianzhuanchuku(str) {
		$(function() {
			document.location.href = "exportOperate.do?flag=getDaiChuLiXQ&exportSeed="
					+ encodeURI(encodeURI($(str).children("input").val()));
		});
	}
</script>
</html>

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
		if(power!=null){
	for(int i=0; i<power.size(); i++){
	if(power.get(i).getFunctions().getFunctionName()!=null && power.get(i).getFunctions().getFunctionName().equals("配送订单")){
		x++;
	}
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

