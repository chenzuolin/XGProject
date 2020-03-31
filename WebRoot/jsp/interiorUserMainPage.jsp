<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>首页</title>
    <style type="text/css">
    	.div1{width:800px;
    			border: 1px solid black;
    			text-align: center;
    			padding: 20px 0 20px 0;
    			margin-left: 200px;
    			}
    	.div1 a{margin: 0 10px 0 10px;}
    	span{color:blue;}
    </style>
  </head> 
  <body>
  <h1>内部人员首页</h1>
  <h3>欢迎<span>${iulist.iuserLoginName}</span>登录</h3>
  <div class="div1">
	    <a href="${pageContext.request.contextPath}/classify.do?flag=goAddClassify">添加功能分类</a>
	    <a href="${pageContext.request.contextPath}/functions.do?flag=goAddFunctions">添加功能</a>
	    <a href="${pageContext.request.contextPath}/functions.do?flag=goSelectFunctions">查询功能表</a>
	    <a href="${pageContext.request.contextPath}/goodsCategory.do?flag=goAddGoodsCategory">添加货物品类</a>
	    <a href="${pageContext.request.contextPath}/goodsYieldly.do?flag=goSelectgoodsYieldly">查询货物产地</a>
	  	<a href="${pageContext.request.contextPath}/goods.do?flag=goAddGoods">添加货物</a>
	  	<a href="${pageContext.request.contextPath}/goods.do?flag=goSelectGoods">查询货物</a><br/><br/> <hr/>		  	
	  	<a href="${pageContext.request.contextPath}/section.do?flag=goAddSection">添加部门</a>  	
	  	<a href="${pageContext.request.contextPath}/interiorUser.do?flag=goAddinteriorUser">添加内部人员</a>
	  	<a href="${pageContext.request.contextPath}/interiorUser.do?flag=selectInteriorUser">查询内部人员</a>
	  	<a href="${pageContext.request.contextPath}/client.do?flag=goAddClient">添加客户</a><hr/>
	  	<a href="${pageContext.request.contextPath}/client.do?flag=selectClient">查询客户</a>
		<a href="${pageContext.request.contextPath}/goodsProperty.do?flag=selectGoodsProperty">查询属性</a>
		<a href="${pageContext.request.contextPath}/goodsStandard.do?flag=selectGoodsStandard">查询规格</a>
		<a href="${pageContext.request.contextPath}/goodsQuality.do?flag=selectGoodsQuality">查询材质</a>
		<a href="${pageContext.request.contextPath}/goodsCategory.do?flag=selectGoodsCategory">查询品类</a>
		<a href="${pageContext.request.contextPath}/goods.do?flag=goAddGoods">货物</a><hr/>
		<a href="${pageContext.request.contextPath}/goodsUnit.do?flag=selectGoodsUnit">计件单位</a>
		<a href="${pageContext.request.contextPath}/bursary.do?flag=getBursaryAll">查询库房</a>
		<a href="${pageContext.request.contextPath}/paymentFashion.do?flag=getAll">支付方式</a>
		<a href="${pageContext.request.contextPath}/paymentFashion.do?flag=getAllTwo">票据类型</a>
		<a href="${pageContext.request.contextPath}/costType.do?flag=getCostTypeAll">费用类型</a><br/>
		<a href="${pageContext.request.contextPath}/tarehouse.do?flag=getTarehouseAll">库位</a>
		<a href="${pageContext.request.contextPath}/section.do?flag=selectSection">查看部门</a>
		<a href="${pageContext.request.contextPath}/classT.do?flag=selectclassT">查看班组</a>
		<a href="${pageContext.request.contextPath}/interiorUserDuty.do?flag=selectInteriorUserDuty">查询职责</a>
		<a href="${pageContext.request.contextPath}/input.do?flag=goInputPage">发起入库</a>
		<a href="<%=basePath%>jsp/addSetAccounts.jsp">添加滞纳金费率</a>
	</div>
</body>
</html>
