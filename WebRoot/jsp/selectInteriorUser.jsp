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
    <h1>查询系统内部人员</h1>
    	 <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>  	
    	<table style="width:1200px;" border="1">   		
		   	<tr class="tr">
		   		<td>内部人员编号</td>
		   		<td>对应类部门类</td>
		   		<td>职责</td>
		   		<td>班组</td>
		   		<td>登录名</td>
		   		<td>密码</td>
		   		<td>注册时间</td>
		   		<td>联系电话</td>
		   		<td>用户姓名</td>
		   		<td>用户性别</td>
		   		<td>工作状态</td>
		   		<td>是否在线</td>
		   		<td>备注</td>
		   		<td>操作</td>
		   	</tr>
				<c:forEach items="${listInteriorUser}" var="interiorUser">
		    		<tr>
			    		<td>${interiorUser.iuserId}</td>
			    		<td>${interiorUser.section.sectionName}</td>
			    		<td>${interiorUser.interiorUserDuty.interiorUserDutyName}</td>
			    		<td>${interiorUser.classT.className}</td>
			    		<td>${interiorUser.iuserLoginName}</td>
			    		<td>${interiorUser.iuserPassword}</td>
			    		<td>${interiorUser.iuserCreateTime}</td>
			    		<td>${interiorUser.iuserTel}</td>
			    		<td>${interiorUser.iuserName}</td>
			    		<td>${interiorUser.iuserSex}</td>
			    		<td>${interiorUser.iuserWork}</td>
			    		<td>${interiorUser.iuserOnline}</td>
			    		<td>${interiorUser.iuserRemark}</td>
			    		<td><a href="${pageContext.request.contextPath}/interiorUser.do?flag=goUpdateinteriorUser&iuserId=${interiorUser.iuserId}">修改</a></td>
		    		</tr>
    			</c:forEach>   	
    	</table>
  </body>
</html>
