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
<title>首页</title>
<script>
	document.location.href="/XGProject/cangchu/page/login.jsp";
</script>

</head>
<body>
	<div id="loader"
		style="position: absolute; left: 48%; top: 40%; color: #337AB7; width: 70px; display: block;" ></div>
</body>
</html>
