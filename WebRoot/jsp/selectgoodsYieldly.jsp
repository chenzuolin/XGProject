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
    <title>查询货物产地</title>
	
  </head> 
  <body>
  	<a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <h1>查询货物产地</h1>
    	<table style="width:500px;">
    		<tr>
    			<td>产地id</td>
    			<td>产地名称</td>
    			<td>备注</td>
    			<td>操作</td>
    		</tr>
    		<c:forEach items="${goodsYieldly}" var="goodsYieldly">
    		<tr> 			
    			<td>${goodsYieldly.goodsYieldlyId}</td>  
    			<td>${goodsYieldly.goodsYieldlyName }</td>
    			<td>${goodsYieldly.goodsYieldlyRemark }</td>
    			<td><a href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goupdategoodsYieldly&id=${goodsYieldly.goodsYieldlyId}">修改</a></td>					
    		</tr> 
    		</c:forEach>  	  		
    	</table>
  </body>
</html>
