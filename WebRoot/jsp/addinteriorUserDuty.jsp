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
    <title>添加货物</title>	
  </head> 
  <body>
    <h1>添加系统内部人员职责</h1>
    <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <form name="interiorUserDutyForm" action="${pageContext.request.contextPath}/interiorUserDuty.do?flag=addinteriorUserDuty" method="post">
    	<table>
    		<tr>
    			<td>职责名称：</td>
    			<td><input type="text" name="interiorUserDutyName"/></td>				
    		</tr>
    		<tr>
    			<td>部门名称：</td>
    			<td>
    				<select name="section">
	    				<c:forEach items="${listSection}" var="listSection">
	    					<option value="${listSection.sectionId}">${listSection.sectionName}</option>
	    				</c:forEach>    					
    				</select>
    		</tr>
    		<tr>
    			<td>
    				<input type="submit" value="添加"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
