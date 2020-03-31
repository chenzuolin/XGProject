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

<title>修改功能的产地</title>

</head>
<body>

	<h1>修改功能的产地</h1>
	<a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
	<form name="goodsYieldlyForm"
		action="${pageContext.request.contextPath}/goodsYieldly.do?flag=updategoodsYieldly" method="post">
		<table>
			<tr>
				<td>产地id：</td>
				<td><input type="text" readonly="true" name="goodsYieldlyId" value="${goodsYieldly.goodsYieldlyId}" /></td>
			</tr>
			<tr>
				<td>货物的名称：</td>
				<td><input type="text" value="${goodsYieldly.goodsYieldlyName}" name="goodsYieldlyName" />
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td>
					<textarea rows="6" cols="20" name="goodsYieldlyRemark">
						<c:out value="${goodsYieldly.goodsYieldlyRemark}"/>
					</textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="修改" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
