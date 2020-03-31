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
<style type="text/css">
.fontcolor {
	color: red;
}

#dianji {
	cursor: pointer;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<script type="text/javascript">
	function refreshCc() //刷新图片 
	{
		var ccImg = document.getElementById("img");
		nowTime = new Date();
		ccImg.src = "${pageContext.request.contextPath}/CreateCode?e="
				+ nowTime.getTime();

	}
</script>
</head>
<body>
	<h1>用户登录</h1>
	<a href="${pageContext.request.contextPath}/index.jsp">返回到主界面</a>
	<form name="loginAllForm"
		action="${pageContext.request.contextPath}/loginAll.do?flag=loginAll"
		method="post">
		<table>
			<tr>
				<td>登录名：<input type="text" name="loginName"></td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;&nbsp;码：<input type="text" name="loginPwd">
				</td>
			</tr>
			<tr>
				<td>验证码：<input style="width: 60px;" type="text" name="code" /><img
					src="<%=basePath%>CreateCode" id="img" /> <a
					href="javascript:refreshCc();">换一张</a>
				</td>
			</tr>
			<tr>
				<td class="fontcolor">${err}</td>
			</tr>
			<tr>
				<td><input type="submit" value="登录"> <input
					type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
