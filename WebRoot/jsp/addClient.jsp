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
    <title>添加客户</title>
    <style type="text/css">
    	.client{ 
    			width:500px;
    			margin-left: 300px;}
    </style>
  </head> 
  <body>   
    <div class="client">
    <h1>添加客户</h1>
    <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    	<form enctype="multipart/form-data" name="clientForm" action="${pageContext.request.contextPath}/client.do?flag=addClient" method="post">
    		<table >
    			<tr>
    	 	  		<td>客户登录名：</td>
    	 	  		<td><input type="text" name="clientLoginName" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>密码：</td>
    	 	  		<td><input type="text" name="clientPassword" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>联系电话：</td>
    	 	  		<td><input type="text" name="clientTel" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>单位名称：</td>
    	 	  		<td><input type="text" name="clientFirmName" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>简称：</td>
    	 	  		<td><input type="text" name="clientAbbreviation" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>助记符：</td>
    	 	  		<td><input type="text" name="clientSign" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>合同号：</td>
    	 	  		<td><input type="text" name="clientContract" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>省份证照片：</td>
    	 	  		<td><input type="file" name="clientStatusImage" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>省份证号码：</td>
    	 	  		<td><input type="text" name="clientStatusNumber" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>地址：</td>
    	 	  		<td><input type="text" name="clientAddress" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>负责人：</td>
    	 	  		<td><input type="text" name="clientHuman" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>邮箱：</td>
    	 	  		<td><input type="text" name="clientEmail" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>合同起始日期：</td>
    	 	  		<td><input type="text" name="clientStartTime" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>合同结束日期：</td>
    	 	  		<td><input type="text" name="clientFinishTime" /></td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>是否停用：</td>
    	 	  		<td>
    	 	  			<input type="radio" name="clientCease" value="0" />是
    	 	  			<input type="radio" name="clientCease" value="1" />否
    	 	  		</td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>结算方式：</td>
    	 	  		<td>
    	 	  			<select name="clientAccounts">
    	 	  				<option>现结</option>
    	 	  				<option>月结</option>
    	 	  			</select>
    	 	  		</td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td>折扣：</td>
    	 	  		<td><input type="text" name="clientAgio" /></td>
    	 	  	</tr>	 	  	
    	 	  	<tr>
    	 	  		<td>备注：</td>
    	 	  		<td>
    	 	  			<textarea name="clientRemark" rows="10" cols="30">  
    	 	  			 				   				
    					</textarea>   	 	  		
    	 	  		</td>
    	 	  	</tr>
    	 	  	<tr>
    	 	  		<td><input type="submit" value="提交"/></td>   	 	  		
    	 	  	</tr>
    		</table>
    	</form>
    	</div>  	
  </body>
</html>
