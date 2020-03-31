<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/zhuce.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.ui.datepicker.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="css/public.css" />
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<script type="text/javascript" src="js/zhuce.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.datepicker.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<style type="text/css">
a {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="daohang" style="margin-top:-15px;">
		<ul>
			<i>当前位置：</i>
			<a>客户管理</a>
			<span>/</span>&nbsp;
			<a>注册客户</a>
			<span>/</span>&nbsp;
		</ul>
	</div>
	<div class="zhuceDiv">
		<h2>注册客户：</h2>
		<div class="conDiv">
			<form
				action="${pageContext.request.contextPath}/client.do?flag=addClient"
				enctype="multipart/form-data" name="clientForm" method="post"
				onSubmit="return fun()">
				<table align="center" cellpadding="5" cellspacing="0"
					class="rightDiv">
					<tr>
						<td width="80px">客户登录名:</td>
						<td><input type="text" name="clientLoginName" id="username">
						</td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input name="clientPassword" id="password">
						</td>
					</tr>
					<tr>
						<td>确认密码:</td>
						<td><input type="text" id="confirm">
						</td>
					</tr>
					<tr>
						<td>邮箱:</td>
						<td><input type="text" name="clientEmail" id="email">
						</td>
					</tr>
					<tr>
						<td>联系电话:</td>
						<td><input type="text" name="clientTel" id="phone">
						</td>
					</tr>
					<!-- <tr>
						<td>身份证照片:</td>
						<td><input type="file" name="clientStatusImage"
							id="clientStatusImage" />
						</td>
					</tr>
					<tr>
						<td>身份证号码:</td>
						<td><input type="text" name="clientStatusNumber"
							id="statusNumber">
						</td>
					</tr> -->
					<tr>
						<td>负责人:</td>
						<td><input type="text" name="clientHuman" id="human">
						</td>
					</tr>
					<tr>
						<td width="150px">合同起始日期:</td>
						<td><input type="text" name="clientStartTime" id="beginDate"
							class="datepicker">
						</td>
					</tr>
					<tr>
						<td>合同结束日期:</td>
						<td><input type="text" name="clientFinishTime"
							id="finishDate" class="datepicker">
						</td>
					</tr>
					<!-- <td>结算方式：</td>
					<td><select name="clientAccounts" id="jisuan">
							<option>现结</option>
							<option>月结</option>
					</select>
					</td> -->
					<tr>
						<td>单位名称:</td>
						<td><input type="text" name="clientFirmName" id="firmName">
						</td>
					</tr>
				</table>

				<table align="center" cellpadding="5" cellspacing="0"
					class="rightDiv2">
					<tr>
						<td>简称:</td>
						<td><input type="text" name="clientAbbreviation"
							id="abbreviation">
						</td>
					</tr>
					<tr>
						<td>助记符:</td>
						<td><input type="text" name="clientSign" id="sign">
						</td>
					</tr>
					<tr>
						<td>合同号:</td>
						<td><input type="text" name="clientContract" id="contract">
						</td>
					</tr>
					<tr>
						<td>合同图片:</td>
						<td><input type="file" name="clientDefinedOne"
							id="statusImage" />
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
						<td>折扣:</td>
						<td><input type="text" value="1.0" name="clientAgio" id="zhekou">
						</td>
					</tr>
					<tr>
						<td>地址:</td>
						<td><input type="text" name="clientAddress" id="address">
						</td>
					</tr>
					<tr>
					<tr>
						<td colspan="2" class="img"><input type="submit" id="zhuce"
							value="点击注册"> <input type="reset" value="重置">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>
