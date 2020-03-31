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
	<h1>添加部门</h1>
	<a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
	<form name="sectionForm"
		action="${pageContext.request.contextPath}/section.do?flag=addSection" method="post">
		<table>
			<tr>
				<td>部门名称：</td>
				<td><input type="text" name="sectionName" /></td>
			</tr>
			<tr>
				<td>部门负责人：</td>
				<td><input type="text" name="sectionHuman" />
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><textarea rows="6" cols="20" name="sectionRemark"></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
