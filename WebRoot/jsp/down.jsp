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

<title>My JSP 'GoEasy.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta name="viewport"
	content="minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<style type="text/css">
div {
	font-size: 20px;
	width: 200px;
	display: block;
	margin: 0 auto;
	margin-top:15%;
}
div a{
	margin-left:10px;
}
</style>
</head>
<script type="text/javascript" src="http(s)://cdn.goeasy.io/goeasy.js"></script>

<body style="width:100%;">
	<div>
		<a
			href="${pageContext.request.contextPath }/jsp/downFirst.jsp?name=androidApp.apk">安卓下载</a>
		<a
			href="${pageContext.request.contextPath }/jsp/downFirst.jsp?name=appleApp.apk">苹果下载</a>
	</div>
</body>
</html>
