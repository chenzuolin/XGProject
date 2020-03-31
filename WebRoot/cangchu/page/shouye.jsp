<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<script src="cangchu/js/jquery1.9.0.min.js"></script>
<title>仓储管理系统</title>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	width: 100%;
	height: 100%;
	background: url(cangchu/img/shouye_bg.png);
	background-repeat: repeat-x;
}

ul li {
	list-style: none;
}

.middle ul .ruku {
	width: 320px;
	height: 100px;
	background: url(cangchu/img/shouye_ruku.png) no-repeat;
	font-size: 30px;
	font-weight: bold;
	line-height: 100px;
	padding-left: 250px;
	color: #FFFFFF;
}

.middle ul .ruku:hover {
	background: url(cangchu/img/ruku2.png) no-repeat;
	cursor: pointer;
}

.middle ul .chuku {
	width: 380px;
	height: 100px;
	background: url(cangchu/img/chuku.png) no-repeat;
	font-size: 30px;
	font-weight: bold;
	line-height: 100px;
	padding-left: 230px;
	color: #FFFFFF;
}

.middle ul .chuku:hover {
	background: url(cangchu/img/chuku2.png) no-repeat;
	cursor: pointer;
}

.middle ul .guohu {
	width: 450px;
	height: 100px;
	background: url(cangchu/img/guohu.png) no-repeat;
	font-size: 30px;
	font-weight: bold;
	line-height: 100px;
	padding-left: 200px;
	color: #FFFFFF;
}

.middle ul .guohu:hover {
	background: url(cangchu/img/guohu2.png) no-repeat;
	cursor: pointer;
}

.middle ul .panku {
	width: 520px;
	height: 106px;
	background: url(cangchu/img/panku.png) no-repeat;
	font-size: 30px;
	font-weight: bold;
	line-height: 100px;
	padding-left: 170px;
	color: #FFFFFF;
	margin-top: 5px;
}

.middle ul .panku:hover {
	background: url(cangchu/img/panku2.png) no-repeat;
	cursor: pointer;
}

.middle ul .nuoku {
	width: 590px;
	height: 130px;
	background: url(cangchu/img/nuoku.png) no-repeat;
	font-size: 30px;
	font-weight: bold;
	line-height: 100px;
	padding-left: 140px;
	color: #FFFFFF;
}

.middle ul .nuoku:hover {
	background: url(cangchu/img/nuoku2.png) no-repeat;
	cursor: pointer;
}

.middle {
	background: url(cangchu/img/shouye-top.png) no-repeat;
	padding: 50px;
	width: 800px;
	height: 550px;
	margin-top: 2%;
	margin-left: 20%;
	display: block;
}
</style>
</head>

<body ondragstart="return false">

	<div style="position: fixed; top: 10px; right: 100px; z-index: -333;">
		<iframe name="weather_inc"
			src="http://i.tianqi.com/index.php?c=code&id=11" width="500px"
			height="50" frameborder="0" scrolling="no"></iframe>
	</div>
	<div class="content_shouye">
		<div class="middle">
			<ul>
				<li class="ruku" id="ruku">待处理订单</li>
				<li class="chuku" id="chuku">订单操作</li>
				<li class="guohu" id="guohu">订单审核</li>
				<li class="panku" id="panku">订单收费</li>
				<li class="nuoku" id="nuoku">订单查询</li>
			</ul>
		</div>
	</div>
</body>
<script>
	$(function() {
		//当点击入库的时候触发
		$("#ruku")
				.click(
						function() {
							document.location.href = "${pageContext.request.contextPath}/cangchu/page/daichuli.jsp";
						});
		//当点击出库的时候触发
		$("#chuku")
				.click(
						function() {
							document.location.href = "${pageContext.request.contextPath}/cangchu/page/caozuodingdan_main.jsp";
						});
		//当点击过户的时候触发
		$("#guohu")
				.click(
						function() {
							document.location.href = "${pageContext.request.contextPath}/cangchu/page/dingdanshenhe_main.jsp";
						});
		//当点击盘库的时候触发
		$("#panku")
				.click(
						function() {
							document.location.href = "${pageContext.request.contextPath}/cangchu/page/dingdanshoufei_main.jsp";
						});
		//当点击挪库的时候触发
		$("#nuoku")
				.click(
						function() {
							document.location.href = "${pageContext.request.contextPath}/date-page/indent.jsp";
						});
	});
</script>

</html>
