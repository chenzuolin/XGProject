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
	<form name="functionsForm"
		action="/XGProject/functions.do?flag=addFunctions" method="post">
		<table>
			<tr>
				<td>功能的类别：</td>
				<td><select name="classify">
						<c:forEach items="${listClassify}" var="classify">
							<option value="${classify.classifyId }">${classify.classifyName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>功能的名称：</td>
				<td><input type="text" name="functionName" />
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><textarea rows="6" cols="20" name="functionRemark"></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
