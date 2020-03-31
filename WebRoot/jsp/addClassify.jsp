<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <h1>添加功能类别</h1>
    <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <form name="classifyForm" action="/XGProject/classify.do?flag=addClassify" method="post">
    	<table>
    		<tr>
    			<td>功能的类别：</td>
    			<td><input type="text" name="classifyName"/></td>
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
