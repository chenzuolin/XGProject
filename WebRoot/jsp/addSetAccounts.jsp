<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>添加滞纳金费率</title>

</head>
<body>
	<h1>添加滞纳金费率</h1>
	<a href="${pageContext.request.contextPath}/index.jsp">返回到主界面</a>
	<form name="classifyForm"
		action="/XGProject/setAccounts.do?flag=saveSAccounts" method="post">
		<table>
			<tr>
				<td>轧账号数：</td>
				<td><select name="saday">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>设置费率：</td>
				<td><input type="text" name="sarate" />
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><textarea rows="5" cols="20" name="saremark"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="添加" /></td>
			</tr>
		</table>
	</form>
	<h3 style="color:red;">${savemessage==null?"":savemessage }</h3>
</body>
</html>
