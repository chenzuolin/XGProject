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
<link rel="stylesheet" href="css/public.css" />
<!--添加 favicon icon -->
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<script type="text/javascript" src="js/clientbianji.js"></script>
<style>
a {
	cursor: pointer;
}

input {
	border: 1px solid #CCC;
}

.center {
	border: 1px solid #999;
	width: 300px;
	height: 450px;
	padding: 30px 60px 30px 100px;
	margin: 0 auto;
	border-radius: 10px;
	margin-top: 150px;
}

.center table {
	width: 250px;
}

.trCenter {
	text-align: center;
	height: 70px;
}

.trCenter input,.trCenter a {
	background-image: url(img/zengjiahuowu2.png);
	background-repeat: no-repeat;
	width: 110px;
	height: 32px;
	border-style: none;
	display: block;
	float: left;
	margin-left: 10px;
	color: #FF3;
	text-decoration: none;
	line-height: 32px;
	font-size: 16px;
}

</style>
</head>

<body>
	<div class="daohang">
		<ul>
			<i>当前位置：</i>
			<a>客户管理</a>
			<span>/</span>&nbsp;
			<a
				href="${pageContext.request.contextPath}/client.do?flag=selectClient">客户信息</a>
			<span>/</span>&nbsp;
			<a>编辑客户</a>
			<span>/</span>&nbsp;
		</ul>
	</div>
	<div class="center">
		<form name="clientForm"
			action="${pageContext.request.contextPath}/client.do?flag=updateClient"
			method="post" onSubmit="return fun()">
			<input type="hidden" name="clientId" value="${client.clientId }" />
			<table>

				<tr>
					<td>邮箱：</td>
					<td><input type="text" name="clientEmail" id="email"
						value="${client.clientEmail }" />
					</td>
				</tr>
				<tr>
					<td>联系电话：</td>
					<td><input type="text" name="clientTel" id="phone"
						value="${client.clientTel }" />
					</td>
				</tr>
				<tr>
					<td>地址：</td>
					<td><input type="text" name="clientAddress" id="address"
						value="${client.clientAddress }" />
					</td>
				</tr>
				<tr>
					<td>简称：</td>
					<td><input type="text" name="clientAbbreviation"
						id="abbreviation" value="${client.clientAbbreviation }" />
					</td>
				</tr>
				<tr>
					<td>助记符：</td>
					<td><input type="text" name="clientSign" id="sign"
						value="${client.clientSign }" />
					</td>
				</tr>
				<tr>
					<td>负责人：</td>
					<td><input type="text" name="clientHuman" id="fuzeren"
						value="${client.clientHuman }" />
					</td>
				</tr>
				<tr>
					<td>登录名：</td>
					<td><input type="text" name="clientLoginName" id="loginName"
						value="${client.clientLoginName }" />
					</td>
				</tr>
				<tr>
					<td>省份证号：</td>
					<td><input type="text" name="clientStatusNumber"
						id="shenfenNum" value="${client.clientStatusNumber }" />
					</td>
				</tr>
				<tr>
					<td>合同号：</td>
					<td><input type="text" name="clientContract" id="hegonghao"
						value="${client.clientContract }" />
					</td>
				</tr>
				<%-- <tr>
					<td>结算方式：</td>
					<td><input type="text" name="clientAccounts" id="jiesuanType"
						value="${client.clientAccounts }" />
					</td>
				</tr>--%>
				<tr>
					<td>折扣：</td>
					<td><input type="text" name="clientAgio" id="zhekou"
						value="${client.clientAgio }" />
					</td>
				</tr> 
				<tr>
					<td>单位名称：</td>
					<td><input type="text" name="clientFirmName" id="firmName"
						value="${client.clientFirmName }" />
					</td>
				</tr>

				<tr class="trCenter">
					<td colspan="2"><input type="submit" id="ok" value="确定" /> <a
						href="${pageContext.request.contextPath}/client.do?flag=selectClient">取消</a>
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>

</html>
