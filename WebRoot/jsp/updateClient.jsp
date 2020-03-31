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
    <title>查询客户信息</title>
	<style type="text/css">
		.div2{
  	 		margin-top: 20px;
  	 	}
  		.div2 a{border: 1px solid black;
  			padding-left: 5px;
  			padding-right: 5px;
  			
  		}
  		.tab1{width:2500px;}
	</style>
  </head> 
  <body>
  
    <h1>查询客户信息</h1>
    	<a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    	<form name="clientForm" action="${pageContext.request.contextPath}/client.do?flag=updateClient" method="post">
    		<table>
    			<tr>
    				<td>客户编号：</td>
	    			<td><input type="text" name="clientId" disabled="disabled" value="${client.clientId}"/></td>	
    			</tr>	
    			<tr>
    				<td>联系电话：</td>
	    			<td><input type="text" name="clientTel" value="${client.clientTel}"/></td>	 
    			</tr>	
    			<tr>
    				<td>负责人：</td>
	    			<td><input type="text" name="clientHuman" value="${client.clientHuman}"/></td>
    			</tr>
    			<tr>
    				<td>负责人邮箱：</td>
	    			<td><input type="text" name="clientEmail" value="${client.clientEmail}"/></td>
    			</tr>
    			<tr>
    				<td>单位名称：</td>
	    			<td><input type="text" name="clientFirmName" value="${client.clientFirmName}"/></td>
    			</tr>
    			<tr>
    				<td>单位地址：</td>
	    			<td><input type="text" name="clientAddress" value="${client.clientAddress}"/></td>
    			</tr>
    			<tr>
    				<td>折扣：</td>
	    			<td><input type="text" name="clientAgio" value="${client.clientAgio}"/></td>	
    			</tr>
    			<tr>
    				<td>是否停用：</td>
	    			<td>
    	 	  			<input type="radio" name="clientCease" value="0" />是
    	 	  			<input type="radio" name="clientCease" value="1" />否
    	 	  		</td>
    			</tr>	
    			<tr>
    				<td>备注：</td>
	    			<td>
    	 	  			<textarea name="clientRemark" rows="10" cols="30">  
    	 	  			 	${client.clientRemark}	   				
    					</textarea>   	 	  		
    	 	  		</td>
    			</tr>
    			<tr>
    				<td>
    					<input type="submit" value="修改">
    				</td>
    			</tr>
    		</table>
    	</form>    	
  </body>
</html>
