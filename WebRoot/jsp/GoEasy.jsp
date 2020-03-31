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

</head>
<script type="text/javascript" src="http(s)://cdn.goeasy.io/goeasy.js"></script>

<script type="text/javascript">
	var goEasy = new GoEasy({
		appkey : '您的app key'
	});
	goEasy.publish({
		channel : 'demo_channel',
		message : 'Hello world!'
	});
	goEasy.subscribe({
		channel : 'demo_channel',
		onMessage : function(message) {
			alert('收到：' + message.content);
		}
	});
	//GoEasy-OTP可以对appkey进行有效保护，详情请参考：GoEasy-Reference
</script>
<body>
	This is my JSP page.
	<br>
</body>
</html>
