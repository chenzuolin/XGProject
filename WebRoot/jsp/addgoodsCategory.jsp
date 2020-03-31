<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加货物品类页面</title>
	
  </head> 
  <body>
    <h1>添加功能类别</h1>
    <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <form name="goodsCategoryForm" action="/XGProject/goodsCategory.do?flag=addGoodsCategory" method="post">
    	<table>
    		<tr>
    			<td>货物品类名称：</td>
    			<td><input type="text" name="goodsCategoryName"/></td>
    		</tr>
    		<tr>
    			<td>货物有效日期：</td>
    			<td><input type="text" name="goodsCategoryPovalidity"/></td>
    		</tr>
    		<tr>
    			<td>备注：</td>
    			<td>
    				<textarea name="goodsCategoryRemark" rows="10" cols="30">
    				   				
    				</textarea>   			
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
