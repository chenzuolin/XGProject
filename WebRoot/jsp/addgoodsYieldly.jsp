<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加货物产地</title>
	
  </head> 
  <body>
    <h1>添加货物产地</h1>
   <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <form name="goodsYieldlyForm" action="/XGProject/goodsYieldly.do?flag=addgoodsYieldly" method="post">
    	<table>
    		<tr>
    			<td>货物产地名称：</td>
    			<td><input type="text" name="goodsYieldlyName"/></td>
    		</tr>
    		<tr>
    			<td>备注：</td>
    			<td>
    				<textarea name="goodsYieldlyRemark" rows="20" cols="50"></textarea>  			
    			</td>
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
