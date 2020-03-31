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
    <title>修改你内部人员</title>
	<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
    <script type="text/javascript" src="js/updateinteriorUser.js"></script>
  </head> 
  <body onload="chooseClassT()">
    <h1>修改内部人员</h1>
   <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <form name="interiorUserForm" id="updateForm" >
    	<table>
    		<tr>
    			<td>内部人员编号</td>
    			<td>
    				<input type="text" name="iuserId" disabled="disabled" value="${interiorUser.iuserId}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>对应类部门类</td>
    			<td>
    				<select name="section" id="selChange">
		   				<c:forEach items="${section}" var="section">
		   					<option value="${section.sectionId}">${section.sectionName}</option>
		   				</c:forEach>    					
    				</select>
    			</td>
    		</tr>	
    		<tr>
    			<td>内部人员职责</td>
    			<td id="tdInfoDuty">
    			<select name="interiorUserDuty">
    				<option>无职责</option>   					
    			</select>    			
    			</td>
    		</tr>
    		<tr>
    			<td>班组类</td>
    			<td id="tdInfoClass">
    			   	<select style="width:100px" name="classT" id="classInfo">
    			   		<option>无人员</option>
    			   	</select>
    			</td>
    		</tr>
    		<tr>
    			<td>登录名</td>
    			<td>
    				<input type="text" name="iuserLoginName" value="${interiorUser.iuserLoginName}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>密码</td>
    			<td>
    				<input type="text" name="iuserPassword" value="${interiorUser.iuserPassword}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>注册时间</td>
    			<td>
    				<input type="text" name="iuserCreateTime" value="${interiorUser.iuserCreateTime}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>联系电话</td>
    			<td>
    				<input type="text" name="iuserTel" value="${interiorUser.iuserTel}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>用户姓名</td>
    			<td>
    				<input type="text" name="iuserName" value="${interiorUser.iuserName}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>用户性别</td>
    			<td>
    				<select name="iuserSex">
    					<option>男</option>
    					<option>女</option>
    				</select>
    			</td>
    		</tr>  	
    		<tr>
    			<td>用户是否停用</td>
    			<td>
    				<select name="iuserCease">
    					<option>0</option>
    					<option>1</option>
    				</select>
    			</td>
    		</tr>     		
    		<tr>
    			<td>备注</td>
    			<td>
    				<textarea name="iuserRemark" rows="10" cols="30">
    				   	<c:out value="${interiorUser.iuserRemark}"/>
    				</textarea>     			
    			</td>
    		</tr>
    		<tr>
    			<td><input type="button" value="修改" id="updatOK"/></td>
    		</tr>		
    	</table>   	
    </form>
  </body>
</html>
