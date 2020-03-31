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
<meta name="viewport"
	content="width=device-width,initial-scale=1.0, minimum-scale=1.0,maximum-scale=1.0" />
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
<link rel="stylesheet" href="css/public.css" />
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<style type="text/css">
* {
	font-family: "微软雅黑";
	font-weight: bold;
}

a {
	cursor: pointer;
}

.top_div {
	box-shadow: 5px 0px 6px #000;
	background: #f8f8ff;
	width: 65%;
	height: 30%;
	float: left;
	border-radius: 50px;
	padding: 5% 8% 5% 8%;
	margin-left: 100px;
	margin-top: 50px;
}

.con {
	border-radius: 5px;
	height: 400px;
	padding: 5%;
	box-shadow: 0px 0px 6px #000;
	margin: 0 auto;
}

.left_div {
	float: left;
}

.left_div h4 {
	color: #03F;
}

#padding {
	margin: 0 10% 0 10%;
}

.hidden {
	border: 1px solid #000;
	height: 270px;
	width: 420px;
	position: absolute;
	top: 0px;
	left: 0px;
}

.hidden2 {
	height: 800px;
	width: 600px;
	position: absolute;
	top: 20px;
	left: 300px;
}

.imgDel {
	position: relative;
	top: 30px;
	left: 570px;
}

.bianjiA {
	margin-bottom: 10px;
}

.bianjiA a {
	background-image: url(img/zengjiahuowu.png);
	background-repeat: no-repeat;
	display: block;
	width: 110px;
	height: 32px;
	border-style: none;
	text-align: center;
	line-height: 32px;
	text-decoration: none;
}

.bianjiA a:hover {
	background-image: url(img/zengjiahuowu2.png);
}

.daohang {
	margin-top: -15px;
}
</style>
<script>
	$(function() {
		$(".hidden").hide();
		$(".hidden2").hide();

		//合同图片
		$(".img2").click(function() {
			$(".hidden2").show();
		});

		$(".imgDel").click(function() {
			$(".hidden2").hide();
		});
	});
</script>
</head>

<body>
	<div class="right" id="mainFrame">
		<div class="daohang">
			<ul>
				<i>当前位置：</i>
				<a style="cursor:pointer;">客户管理</a>
				<span>/</span>&nbsp;
				<a href="javascript:document.location.reload();">客户信息</a>
				<span>/</span>&nbsp;
			</ul>
		</div>
		<div class="top_div">
			<div class="bianjiA">
				<a
					href="${pageContext.request.contextPath}/client.do?flag=goUpdateClient&id=${client.clientId }"
					style="color:#fffafa;">编辑</a>
			</div>
			<div class="con">
				<div class="left_div">
					<h4>客户信息:</h4>
					<div>
						<table>
							<tr>
								<td>负责人：</td>
								<td>${client.clientHuman }</td>
							</tr>
							<tr>
								<td>登录名：</td>
								<td>${client.clientLoginName }</td>
							</tr>
							<tr>
								<td>邮箱：</td>
								<td>${client.clientEmail }</td>
							</tr>
							<tr>
								<td>联系电话：</td>
								<td>${client.clientTel }</td>
							</tr>
							<tr>
								<td>身份证号：</td>
								<td>${client.clientStatusNumber }</td>
							</tr>
							<tr>
								<td>身份证：</td>
							</tr>
							<tr>
								<td colspan="2"><img class="img1"
									src="<%=basePath%>userImg/${client.clientStatusImage}"
									width="250px" height="150px" alt="身份证照片">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="left_div" id="padding">
					<div class="hidden2">

						<img class="imgDel" src="img/delete_one.png" /> <img
							src="<%=basePath%>userImg/${client.clientDefinedOne}"
							height="800px" width="600px" />

					</div>
					<h4>合同信息:</h4>
					<div class="padding">
						<table>
							<tr>
								<td>合同号：</td>
								<td>${client.clientContract }</td>
							</tr>
							<tr>
								<td>合同起始日期：</td>
								<td>${client.clientStartTime }</td>
							</tr>
							<tr>
								<td>合同结束日期</td>
								<td>${client.clientFinishTime }</td>
							</tr>
							<%-- <tr>
								<td>结算方式：</td>
								<td>${client.clientAccounts }</td>
							</tr> --%>
							<tr>
								<td>折扣：</td>
								<td>${client.clientAgio }</td>
							</tr>
							<tr>
								<td>合同图片：</td>
							</tr>
							<tr id="img2">
								<td colspan="2"><img class="img2"
									src="<%=basePath%>userImg/${client.clientDefinedOne}"
									width="60px" height="80px" alt="合同图片">
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="left_div">
					<h4>企业信息:</h4>
					<div>
						<table>
							<tr>
								<td>单位名称：</td>
								<td>${client.clientFirmName }</td>
							</tr>
							<tr>
								<td>地址：</td>
								<td>${client.clientAddress }</td>
							</tr>
							<tr>
								<td>简称：</td>
								<td>${client.clientAbbreviation }</td>
							</tr>
							<tr>
								<td>助记符：</td>
								<td>${client.clientSign }</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
