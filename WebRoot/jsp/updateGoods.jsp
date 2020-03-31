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
    <title>修改货物</title>
	
  </head> 
  <body>

    <h1>修改货物</h1>
      <a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    <form name="goodsForm" action="${pageContext.request.contextPath}/goods.do?flag=updateGoods" method="post">
    	<table style="width:500px;">
    		<tr>
    			<td>货物名称编号</td>
    			<td>
    				<input type="text" name="goodsId" disabled="disabled" value="${goods.goodsId}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>货物品类</td>
    			<td>
    				<select name="goodsCategory">
    				<c:forEach items="${goodsCategory}" var="goodsCategory">
    					<option value="${goodsCategory.goodsCategoryId}">${goodsCategory.goodsCategoryName}</option>
    				</c:forEach>    					
    				</select>
    			</td>
    		</tr>	
    		<tr>
    			<td>货物名称</td>
    			<td>
    				<input type="text" name="goodsName" value="${goods.goodsName}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>货物名称助记符</td>
    			<td>
    				<input type="text" name="goodsSign" value="${goods.goodsSign}"/>   			
    			</td>
    		</tr>
    		<tr>
    			<td>规格</td>
    			<td>
    				<input type="text" name="goodsStandard" value="${goods.goodsStandard}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>材质</td>
    			<td>
    				<input type="text" name="goodsQuality" value="${goods.goodsQuality}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>属性</td>
    			<td>
    				<input type="text" name="goodsProperty" value="${goods.goodsProperty}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>计量单位</td>
    			<td>
    				<input type="text" name="goodsUnit" value="${goods.goodsUnit}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>理算重量</td>
    			<td>
    				<input type="text" name="goodsAdjustment" value="${goods.goodsAdjustment}"/>
    			</td>
    		</tr>
    		<tr>
    			<td>产地</td>
    			<td>
	    			<select name="goodsYieldly">
	    				<c:forEach items="${goodsYieldly}" var="goodsYieldly">
	    					<option value="${goodsYieldly.goodsYieldlyId}">${goodsYieldly.goodsYieldlyName}</option>
	    				</c:forEach>    					
	    			</select>
    			</td>
    		</tr>  	
    		<tr>
    			<td>备注</td>
    			<td>
    				<textarea name="goodsRemark" rows="10" cols="30">
    				   	<c:out value="${goods.goodsRemark}" />
    				</textarea>     			
    			</td>
    		</tr>  
    		<tr>
    			<td><input type="submit" value="修改"/></td>
    		</tr>		
    	</table>   	
    </form>
  </body>
</html>
