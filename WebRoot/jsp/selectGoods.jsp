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
	<style type="text/css">
		.div2{
  	 		margin-top: 20px;
  	 	}
  		.div2 a{border: 1px solid black;
  			padding-left: 5px;
  			padding-right: 5px;
  			
  		}
	</style>
  </head> 
  <body>
    <h1>查询货物表</h1>
    	<a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    	<form name="goodsForm" action="${pageContext.request.contextPath}/goods.do?flag=goSelectGoodsTwo" method="post">
    		<table>
    			<tr>
	    			<td>输入货物的名称：</td>
	    			<td><input type="text" name="goodsName" /></td>
	    			<td>助记符：</td>
	    			<td><input type="text" name="goodsSign" /></td>
	    			<td>规格：</td>
	    			<td><input type="text" name="goodsStandard" /></td>	    			
    			</tr>
    			<tr>
    				<td>材质：</td>
	    			<td><input type="text" name="goodsQuality" /></td>
	    			<td>属性：</td>
	    			<td><input type="text" name="goodsProperty"/></td>
	    			<td><input type="submit" value="查询"/></td>
    			</tr>
    		</table>
    	</form>
    	<table style="width:1200px;" border="1">   		
		   	<tr class="tr">
		   		<td>货物名称编号</td>
		   		<td>货物品类</td>
		   		<td>货物名称</td>
		   		<td>货物名称助记符</td>
		   		<td>规格</td>
		   		<td>材质</td>
		   		<td>属性</td>
		   		<td>计量单位</td>
		   		<td>理算重量</td>
		   		<td>产地</td>
		   		<td>备注</td>
		   		<td>操作</td>
		   	</tr>
		
			<c:choose>
			    <c:when test="${goodslistTwo!=null}">
				<c:forEach items="${goodslistTwo}" var="goods">
		    		<tr>
			    		<td>${goods.goodsId}</td>
			    		<td>${goods.goodsCategory.goodsCategoryName}</td>
			    		<td>${goods.goodsName}</td>
			    		<td>${goods.goodsSign}</td>
			    		<td>${goods.goodsStandard}</td>
			    		<td>${goods.goodsQuality}</td>
			    		<td>${goods.goodsProperty}</td>
			    		<td>${goods.goodsUnit}</td>
			    		<td>${goods.goodsAdjustment}</td>
			    		<td>${goods.goodsYieldly.goodsYieldlyName}</td>
			    		<td>${goods.goodsRemark}</td>
			    		<td><a href="${pageContext.request.contextPath}/goods.do?flag=goUpdateGoodsTwo&goodsId=${goods.goodsId}">修改</a></td>
		    		</tr>
    			</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${goodslist}" var="goods">
			    		<tr>
				    		<td>${goods.goodsId}</td>
				    		<td>${goods.goodsCategory.goodsCategoryName}</td>
				    		<td>${goods.goodsName}</td>
				    		<td>${goods.goodsSign}</td>
				    		<td>${goods.goodsStandard}</td>
				    		<td>${goods.goodsQuality}</td>
				    		<td>${goods.goodsProperty}</td>
				    		<td>${goods.goodsUnit}</td>
				    		<td>${goods.goodsAdjustment}</td>
				    		<td>${goods.goodsYieldly.goodsYieldlyName}</td>
				    		<td>${goods.goodsRemark}</td>
				    		<td><a href="${pageContext.request.contextPath}/goods.do?flag=goUpdateGoods&goodsId=${goods.goodsId}">修改</a></td>
			    		</tr>
    				</c:forEach>
				</c:otherwise>
			</c:choose>
		
    	
    	</table>
    	<div class="div2">
    	<c:choose>
    		<c:when test="${goodslistTwo!=null}">
    			<c:forEach var="i" begin="1" end="${pageCountTwo}">
	    			<a href="${pageContext.request.contextPath}/goods.do?flag=goSelectGoodsTwo&pageNow=${i}">${i}</a>
	    		</c:forEach>
    		</c:when>
    		<c:otherwise>
    			<c:forEach var="i" begin="1" end="${pageCount}">
	    			<a href="${pageContext.request.contextPath}/goods.do?flag=goSelectGoods&pageNow=${i}">${i}</a>
	    		</c:forEach>
    		</c:otherwise>
    	</c:choose>
	    </div>
  </body>
</html>
