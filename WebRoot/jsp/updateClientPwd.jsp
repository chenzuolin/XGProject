<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>添加功能分类页面</title>

</head>
<body>

	<h1>添加功能类别</h1>
	<a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
	<form name="clientForm"
		action="${pageContext.request.contextPath}/client.do?flag=updateClientPwd" method="post">
		<input type="hidden" name="clientId" value="${client.clientId}"/>
		<table>
			<tr>
				<td>登录名：</td>
				<td>
					<input type="text" name="clientLoginName" value="${client.clientLoginName}"/>
				</td>
			</tr>
			<tr>
				<td>密码：</td>
				<td>
					<input type="text" name="clientPassword" value="${client.clientPassword}" />
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="修改" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
