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
  <body >
    <h1>查询客户信息</h1>
    	<a href="${pageContext.request.contextPath}/loginAll.do?flag=goInteriorUserMainPage">返回到主界面</a>
    	<form name="clientForm" action="${pageContext.request.contextPath}/client.do?flag=goSelectClientTwo" method="post">
    		<table>
    			<tr>
	    			<td>单位名称：</td>
	    			<td><input type="text" name="clientFirmName" /></td>
	    			<td>简称：</td>
	    			<td><input type="text" name="clientAbbreviation" /></td>
	    			<td>助记符：</td>
	    			<td><input type="text" name="clientSign" /></td>	    			    			
    				<td>合同号：</td>
	    			<td><input type="text" name="clientContract" /></td>
	    			<td><input type="submit" value="查询"/></td>
    			</tr>
    		</table>
    	</form>
    	<table class="tab1" border="1">   		
		   	<tr class="tr">
		   		<td>客户编号</td>
		   		<td>客户登录名</td>
		   		<td>密码</td>
		   		<td>注册时间</td>
		   		<td>联系电话</td>
		   		<td>负责人</td>
		   		<td>负责人邮箱</td>
		   		<td>身份证照片</td>
		   		<td>单位名称</td>
		   		<td>简称</td>
		   		<td>名称助记符</td>
		   		<td>单位地址</td>
		   		<td>合同号</td>
		   		<td>合同起始日期</td>
		   		<td>合同结束日期</td>
		   		<td>是否停用</td>
		   		<td>结算方式</td>
		   		<td>折扣</td>
		   		<td>备注</td>
		   		<td>操作</td>
		   	</tr>		
			<c:choose>
			    <c:when test="${clientlistTwo!=null}">
				<c:forEach items="${clientlistTwo}" var="client">
		    		<tr>
			    		<td>${client.clientId}</td>
				    		<td>${client.clientLoginName}</td>
				    		<td>${client.clientPassword}</td>
				    		<td>${client.clientCreateTime}</td>
				    		<td>${client.clientTel}</td>
				    		<td>${client.clientHuman}</td>
				    		<td>${client.clientEmail}</td>
				    		<td>
				    			<img src="<%=basePath%>images/${client.clientStatusImage}" width="300px" height="150px"/>
				    		</td>
				    		<td>${client.clientFirmName}</td>
				    		<td>${client.clientAbbreviation}</td>
				    		<td>${client.clientSign}</td>
				    		<td>${client.clientAddress}</td>
				    		<td>${client.clientContract}</td>
				    		<td>${client.clientStartTime}</td>
				    		<td>${client.clientFinishTime}</td>
				    		<td>
				    			<c:choose>
				    				<c:when test="${client.clientCease==1}">
				    					<c:out value="否"/>
				    				</c:when>
				    				<c:otherwise>
				    					<c:out value="是"/>
				    				</c:otherwise>				    				
				    			</c:choose>				    		
				    		</td>
				    		<td>${client.clientAccounts}</td>
				    		<td>${client.clientAgio}</td>
				    		<td>${client.clientRemark}</td>				    		
				    		<td><a href="${pageContext.request.contextPath}/client.do?flag=goUpdateClient&clientId=${client.clientId}">修改</a></td>
		    		</tr>
    			</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach items="${clientlist}" var="client">
			    		<tr>
				    		<td>${client.clientId}</td>
				    		<td>${client.clientLoginName}</td>
				    		<td>${client.clientPassword}</td>
				    		<td>${client.clientCreateTime}</td>
				    		<td>${client.clientTel}</td>
				    		<td>${client.clientHuman}</td>
				    		<td>${client.clientEmail}</td>
				    		<td>
				    			<img src="<%=basePath%>images/${client.clientStatusImage}" width="300px" height="150px"/>
				    		</td>
				    		<td>${client.clientFirmName}</td>
				    		<td>${client.clientAbbreviation}</td>
				    		<td>${client.clientSign}</td>
				    		<td>${client.clientAddress}</td>
				    		<td>${client.clientContract}</td>
				    		<td>${client.clientStartTime}</td>
				    		<td>${client.clientFinishTime}</td>
				    		<td>
				    			<c:choose>
				    				<c:when test="${client.clientCease==1}">
				    					<c:out value="否"/>
				    				</c:when>
				    				<c:otherwise>
				    					<c:out value="是"/>
				    				</c:otherwise>				    				
				    			</c:choose>				    						    		
				    		</td>
				    		<td>${client.clientAccounts}</td>
				    		<td>${client.clientAgio}</td>
				    		<td>${client.clientRemark}</td>				    		
				    		<td><a href="${pageContext.request.contextPath}/client.do?flag=goUpdateClient&clientId=${client.clientId}">修改</a></td>
			    		</tr>
    				</c:forEach>
				</c:otherwise>
			</c:choose>   	
    	</table>
    	<div class="div2">
    	<c:choose>
    		<c:when test="${clientlistTwo!=null}">
  				<a href="${pageContext.request.contextPath}/client.do?flag=goSelectClientTwo&pageNow=${pageNow2-1}">上一页</a>
  				<a href="${pageContext.request.contextPath}/client.do?flag=goSelectClientTwo&pageNow=${pageNow2+1}">下一页</a>	 
   				<form name="clientForm" action="${pageContext.request.contextPath}/client.do?flag=goSelectClientTwo" method="post">
 						跳转到<input type="text" name="yeshu"/>页<input type="submit" value="确定"/>
 				</form> 
    		</c:when>
    		<c:otherwise>
   				<a href="${pageContext.request.contextPath}/client.do?flag=selectClient&pageNow=${pageNow2-1}">上一页</a>  				
   				<a href="${pageContext.request.contextPath}/client.do?flag=selectClient&pageNow=${pageNow2+1}">下一页</a>	
   				<form name="clientForm" action="${pageContext.request.contextPath}/client.do?flag=selectClient" method="post">
   					跳转到<input type="text" name="yeshu"/>页<input type="submit" value="确定"/>
   				</form>    			
    		</c:otherwise>
    	</c:choose>
	    </div>
  </body>
</html>
