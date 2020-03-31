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
    	<form name="classTForm" action="${pageContext.request.contextPath}/classT.do?flag=addClassT" method="post">
    		<table style="width:600px;">
    			<tr>
    	 	  		<td>部门：</td>
    	 	  		<td>
    	 	  			<select name="section">
    	 	  				<c:forEach items="${listSection}" var="listSection">
    	 	  					<option value="${listSection.sectionId}">${listSection.sectionName}</option>
    	 	  				</c:forEach>   	 	  				
    	 	  			</select>  	 	  		
    	 	  		</td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>班组的名称：</td>
    	 	  		<td><input type="text" name="className" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>班组的负责人：</td>
    	 	  		<td><input type="text" name="classHuman" /></td>
    	 	  	</tr>   	 	  	
    	 	  	<tr>
    	 	  		<td>备注：</td>
    	 	  		<td>
    	 	  			<textarea name="classRemark" rows="10" cols="30">  
    	 	  			 				   				
    					</textarea>   	 	  		
    	 	  		</td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td><input type="submit" value="提交"/></td>   	 	  		
    	 	  	</tr>
    		</table>
    	</form>  	
  </body>
</html>
