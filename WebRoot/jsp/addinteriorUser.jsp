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
    <title>添加内部人员</title>	
    <script type="text/javascript" src="js/jquery-1.8.1.js"></script>
    <script type="text/javascript" src="js/all.js"></script>
    <script type="text/javascript" src="js/addinteriorUser.js"></script>
  </head> 
  <body onload="chooseClassT()">
    <h1>添加内部人员</h1>
    <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <form name="interiorUserForm" method="post" id="InterForm">
    	<input type="hidden" name="flag" value="addInteriorUserOk"/>
    	<table>
    		<tr>
    			<td>登录名：</td>
    			<td><input type="text" name="iuserLoginName"/></td>
    		</tr>
    		<tr>
    			<td>登录密码：</td>
    			<td><input type="text" name="iuserPassword"/></td>
    		</tr>
    		<tr>
    			<td>联系电话：</td>
    			<td><input type="text" name="iuserTel"/></td>
    		</tr>
    		<tr>
    			<td>用户姓名：</td>
    			<td><input type="text" name="iuserName"/></td>
    		</tr>
    		<tr>
    			<td>性别：</td>
    			<td>
    				<input type="radio" name="iuserSex" value="男"/>男
    				<input type="radio" name="iuserSex" value="女"/>女
    			</td>
    		</tr>
    		<tr>
    			<td>部门：</td>
    			<td>
    				<select name="section" id="selChange">
		   				<c:forEach items="${section}" var="section">
		   					<option value="${section.sectionId}">${section.sectionName}</option>
		   				</c:forEach>    					
    				</select>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>班组：</td>
    			<td id="tdInfoClass">
    			   	<select style="width:100px" name="classT" id="classInfo">
    			   		<option>无人员</option>
    			   	</select>
    			</td>
    		</tr>    		
    		<tr>
    			<td>是否作业：</td>
    			<td>
    				<input type="radio" name="iuserWork" value="0"/>是
    				<input type="radio" name="iuserWork" value="1" checked="checked"/>否
    			</td>
    		</tr>
    		<tr>
    			<td>职责：</td>
    			<td id="tdInfoDuty">
    			<select name="interiorUserDuty">
    				<option>无职责</option>   					
    			</select>    			
    			</td>
    		</tr>    
    		<tr>
    			<td>备注：</td>
    			<td>
    				<textarea name="iuserRemark" rows="10" cols="30"></textarea>   			
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<input type="button" value="添加" id="sub"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
