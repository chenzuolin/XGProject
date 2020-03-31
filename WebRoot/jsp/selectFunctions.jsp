<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加功能分类页面</title>
	
  </head> 
  <body>
    <h1>查询功能表</h1>
    <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    	<table style="width:500px;">
    		<tr>
    			<td>功能的类别</td>
    			<td>功能的名称</td>
    			<td>备注</td>
    		</tr>
    		<c:forEach items="${listFunctions}" var="functions">
    		<tr> 			
    			<td>${functions.classify.classifyName}</td>  
    			<td>${functions.functionName }</td>
    			<td>${functions.functionRemark }</td> 					
    		</tr> 
    		</c:forEach>  	  		
    	</table>
  </body>
</html>
