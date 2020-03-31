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
<link href="css/bianji.css" rel="stylesheet" type="text/css" />
<link href="css/jquery.ui.datepicker.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" href="css/public.css" />
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<script type="text/javascript" src="js/bianji.js"></script>
<script type="text/javascript" src="js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="js/jquery.ui.datepicker.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<style type="text/css">
a {
	cursor: pointer;
}

h2 {
	
}

.daohang {
	margin-top: -15px;
}
</style>
</head>
<body>
	<div class="daohang">
		<ul>
			<i>当前位置：</i>
			<a>客户管理</a>
			<span>/</span>&nbsp;
			<a>编辑客户</a>
			<span>/</span>&nbsp;
		</ul>
	</div>
	<div class="zhuceDiv">
		<h2>编辑客户：</h2>
		<div class="conDiv">
			<form
				action="${pageContext.request.contextPath}/client.do?flag=updateClient"
				enctype="multipart/form-data" name="clientForm" method="post"
				onSubmit="return fun()">
				<input type="hidden" name="clientId" value="${client.clientId }" />
				<table align="center" cellpadding="5" cellspacing="0"
					class="rightDiv">
					<tr>
						<td>负责人:</td>
						<td><input type="text" name="clientHuman" id="human"
							value="${client.clientHuman }">
						</td>
					</tr>
					<tr>
						<td>合同起始日期:</td>
						<td><input type="text" name="clientStartTime" id="beginDate"
							class="datepicker" value="${client.clientStartTime }">
						</td>
					</tr>
					<tr>
						<td>合同结束日期:</td>
						<td><input type="text" name="clientFinishTime"
							id="finishDate" class="datepicker"
							value="${client.clientFinishTime }">
						</td>
					</tr>
					<!-- <tr>
						<td>结算方式：</td>
						<td><select name="clientAccounts" id="jisuan">
								<option>现结</option>
								<option>月结</option>
						</select>
						</td>
					</tr> -->
					<tr>
						<td>简称:</td>
						<td><input type="text" name="clientAbbreviation"
							id="abbreviation" value="${client.clientAbbreviation }">
						</td>
					</tr>
					<tr>
						<td>助记符:</td>
						<td><input type="text" name="clientSign" id="sign"
							value="${client.clientSign }">
						</td>
					</tr>
					<tr>
						<td>折扣:</td>
						<td><input type="text" name="clientAgio" id="zhekou"
							value="${client.clientAgio }">
						</td>
					</tr>
					<tr>
						<td colspan="2" class="img" style="text-align: right;"><input
							type="submit" style="color:#ffffff;" id="zhuce" value="确定">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

</body>
</html>
