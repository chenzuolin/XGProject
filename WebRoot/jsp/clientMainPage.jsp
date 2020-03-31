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
    <style type="text/css">
    	.div1{width:800px;
    			border: 1px solid black;
    			text-align: center;
    			padding: 20px 0 20px 0;
    			margin-left: 200px;
    			}
    	.div1 a{margin: 0 10px 0 10px;}
    	span{color:blue;}
    </style>
  </head> 
  <body>
  <h1>客户首页首页</h1>
  <h3>欢迎<span>${client.clientLoginName }</span>登录</h3>
  <div class="div1">
	    <a href="${pageContext.request.contextPath}/client.do?flag=goUpdateClientPwd">修改密码</a>
	    <a href="${pageContext.request.contextPath}/client.do?flag=goUpdateClientPwd">入库</a>
	    <a href="">出库</a>
	    <a href="">过户</a>
	    <a href="">查看</a>
  	</div>
  </body>
</html>
